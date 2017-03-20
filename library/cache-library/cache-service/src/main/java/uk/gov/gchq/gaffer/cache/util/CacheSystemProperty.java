/*
 * Copyright 2016-2017 Crown Copyright
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

package uk.gov.gchq.gaffer.cache.util;


public final class CacheSystemProperty {

    private CacheSystemProperty() {
        // do not instantiate
    }

    // Keys

    public static final String CACHE_SERVICE_CLASS = "gaffer.cache.service.class";

    public static final String CACHE_CONFIG_FILE = "gaffer.cache.config";

    // Defaults

    public static final String DEFAULT_CACHE_SERVICE_CLASS = "uk.gov.gchq.gaffer.cache.impl.HashMapCacheService";
}
