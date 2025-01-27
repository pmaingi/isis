/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.isis.core.metamodel.facets.collections.collection.defaultview;

import java.util.Properties;

import com.google.common.base.Strings;

import org.apache.isis.core.metamodel.facetapi.FacetHolder;

public class DefaultViewFacetOnCollectionFromLayoutProperties extends DefaultViewFacetAbstract {

    private DefaultViewFacetOnCollectionFromLayoutProperties(String defaultView, FacetHolder holder) {
        super(defaultView, holder);
    }

    public static DefaultViewFacet create(Properties properties, FacetHolder holder) {
        final String defaultView = defaultView(properties);
        return defaultView != null ? new DefaultViewFacetOnCollectionFromLayoutProperties(defaultView, holder) : null;
    }

    public static String defaultView(Properties properties) {
        if (properties == null) {
            return null;
        }

        return Strings.emptyToNull(properties.getProperty("defaultView"));
    }
}
