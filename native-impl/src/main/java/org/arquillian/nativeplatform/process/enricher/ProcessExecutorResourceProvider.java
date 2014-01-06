/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.arquillian.nativeplatform.process.enricher;

import java.lang.annotation.Annotation;

import org.arquillian.nativeplatform.process.ProcessExecutor;
import org.jboss.arquillian.core.api.Instance;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.test.spi.enricher.resource.ResourceProvider;

/**
 * Injects {@link ProcessExecutor} into the test class as {@link ArquillianResource}.
 *
 * @author <a href="smikloso@redhat.com">Stefan Miklosovic</a>
 *
 */
public class ProcessExecutorResourceProvider implements ResourceProvider {

    @Inject
    Instance<ProcessExecutor> processExecutor;

    @Override
    public boolean canProvide(Class<?> type) {
        return ProcessExecutor.class.isAssignableFrom(type);
    }

    @Override
    public Object lookup(ArquillianResource resource, Annotation... qualifiers) {
        ProcessExecutor executor = processExecutor.get();

        if (executor == null) {
            throw new IllegalStateException("Unable to inject ProcessExecutor instance into the test.");
        }

        return executor;
    }

}