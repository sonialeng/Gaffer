/*
 * Copyright 2017 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.gchq.gaffer.store.serialiser.lengthvalue;

import org.junit.Before;
import org.junit.Test;
import uk.gov.gchq.gaffer.commonutil.TestGroups;
import uk.gov.gchq.gaffer.data.element.Edge;
import uk.gov.gchq.gaffer.data.element.id.EdgeId;
import uk.gov.gchq.gaffer.exception.SerialisationException;
import uk.gov.gchq.gaffer.operation.data.generator.EdgeIdExtractor;
import uk.gov.gchq.gaffer.serialisation.implementation.StringSerialiser;
import uk.gov.gchq.gaffer.store.schema.Schema;
import uk.gov.gchq.gaffer.store.serialiser.lengthvalue.EdgeIdSerialiser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EdgeIdSerialiserTest {

    private Schema schema;
    private EdgeIdSerialiser edgeIdSerialiser;

    @Before
    public void setUp() {
        schema = new Schema.Builder()
                .vertexSerialiser(new StringSerialiser())
                .build();
        edgeIdSerialiser = new EdgeIdSerialiser(schema);
    }

    @Test
    public void testNullSerialiser() {
        // Given
        schema = new Schema.Builder()
                .build();

        // When / Then
        try {
            edgeIdSerialiser = new EdgeIdSerialiser(schema);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Vertex serialiser is required"));
        }
    }

    @Test
    public void testCanSeraliseEdgeId() throws SerialisationException {
        // Given
        final EdgeIdExtractor extractor = new EdgeIdExtractor();
        final Edge edge = new Edge(TestGroups.EDGE, "source", "destination", true);
        final EdgeId edgeId = extractor._apply(edge);

        // When
        final byte[] serialisedEdgeId = edgeIdSerialiser.serialise(edgeId);
        final EdgeId deserialisedEdgeId = edgeIdSerialiser.deserialise(serialisedEdgeId);

        // Then
        assertEquals(edgeId, deserialisedEdgeId);
    }

    @Test
    public void testCantSerialiseIntegerClass() throws SerialisationException {
        assertFalse(edgeIdSerialiser.canHandle(Integer.class));
    }

    @Test
    public void testCanSerialiseEdgeIdClass() throws SerialisationException {
        assertTrue(edgeIdSerialiser.canHandle(EdgeId.class));
    }

    @Test
    public void testDeserialiseEmpty() throws SerialisationException {
        assertEquals(null, edgeIdSerialiser.deserialiseEmpty());
    }

    @Test
    public void testPreserveObjectOrdering() throws SerialisationException {
        assertEquals(false, edgeIdSerialiser.preservesObjectOrdering());
    }
}
