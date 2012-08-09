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

package org.apache.isis.runtimes.dflt.objectstores.dflt;

import org.apache.isis.core.commons.config.IsisConfiguration;
import org.apache.isis.core.metamodel.adapter.ObjectAdapterFactory;
import org.apache.isis.runtimes.dflt.runtime.installerregistry.installerapi.PersistenceMechanismInstallerAbstract;
import org.apache.isis.runtimes.dflt.runtime.persistence.objectstore.ObjectStoreSpi;
import org.apache.isis.runtimes.dflt.runtime.system.DeploymentType;
import org.apache.isis.runtimes.dflt.runtime.system.persistence.AdapterManagerSpi;
import org.apache.isis.runtimes.dflt.runtime.system.persistence.PersistenceSessionFactory;

/**
 * Installs the in-memory object store.
 */
public class InMemoryPersistenceMechanismInstaller extends PersistenceMechanismInstallerAbstract {

    public static final String NAME = "in-memory";

    public InMemoryPersistenceMechanismInstaller() {
        super(NAME);
    }

    // ///////////////////////////////////////////////////////////////
    // createPersistenceSessionFactory
    // ///////////////////////////////////////////////////////////////

    @Override
    public PersistenceSessionFactory createPersistenceSessionFactory(final DeploymentType deploymentType) {
        return new InMemoryPersistenceSessionFactory(deploymentType, this);
    }

    // ///////////////////////////////////////////////////////////////
    // Hook methods
    // ///////////////////////////////////////////////////////////////

    /**
     * Hook method to return {@link ObjectStoreSpi}.
     */
    @Override
    protected ObjectStoreSpi createObjectStore(final IsisConfiguration configuration, final ObjectAdapterFactory adapterFactory, final AdapterManagerSpi adapterManager) {
        return new InMemoryObjectStore();
    }

}
