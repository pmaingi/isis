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

package org.apache.isis.core.runtime.system;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.apache.isis.applib.Identifier;
import org.apache.isis.core.commons.authentication.AuthenticationSessionProvider;
import org.apache.isis.core.metamodel.runtimecontext.PersistenceSessionService;
import org.apache.isis.core.metamodel.runtimecontext.MessageBrokerService;
import org.apache.isis.core.metamodel.adapter.mgr.AdapterManager;
import org.apache.isis.core.metamodel.facets.FacetedMethod;
import org.apache.isis.core.metamodel.facets.all.named.NamedFacet;
import org.apache.isis.core.metamodel.facets.all.named.NamedFacetAbstract;
import org.apache.isis.core.metamodel.runtimecontext.ServicesInjector;
import org.apache.isis.core.metamodel.spec.SpecificationLoader;
import org.apache.isis.core.metamodel.spec.feature.ObjectMemberDependencies;
import org.apache.isis.core.metamodel.specloader.specimpl.ObjectActionDefault;
import org.apache.isis.core.unittestsupport.jmocking.JUnitRuleMockery2;
import org.apache.isis.core.unittestsupport.jmocking.JUnitRuleMockery2.Mode;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ObjectActionDefaultTest {

    @Rule
    public JUnitRuleMockery2 context = JUnitRuleMockery2.createFor(Mode.INTERFACES_AND_CLASSES);
    
    
    private ObjectActionDefault action;
    
    @Mock
    private FacetedMethod mockFacetedMethod;

    @Mock
    private AuthenticationSessionProvider mockAuthenticationSessionProvider;
    @Mock
    private SpecificationLoader mockSpecificationLookup;
    @Mock
    private AdapterManager mockAdapterManager;
    @Mock
    private ServicesInjector mockServicesInjector;
    @Mock
    private MessageBrokerService mockMessageBrokerService;
    @Mock
    private PersistenceSessionService mockPersistenceSessionService;

    @Before
    public void setUp() throws Exception {

        context.checking(new Expectations() {
            {
                one(mockFacetedMethod).getIdentifier();
                will(returnValue(Identifier.actionIdentifier("Customer", "reduceheadcount")));
            }
        });

        action = new ObjectActionDefault(mockFacetedMethod, new ObjectMemberDependencies(
                mockSpecificationLookup, mockServicesInjector,
                mockPersistenceSessionService));
    }


    @Test
    public void testNameDefaultsToActionsMethodName() {
        final String name = "Reduceheadcount";
        final NamedFacet facet = new NamedFacetAbstract(name, true, mockFacetedMethod) {
        };
        context.checking(new Expectations() {
            {
                one(mockFacetedMethod).getFacet(NamedFacet.class);
                will(returnValue(facet));
            }
        });
        assertThat(action.getName(), is(equalTo(name)));
    }

    @Test
    public void testId() {
        assertEquals("reduceheadcount", action.getId());
    }

}
