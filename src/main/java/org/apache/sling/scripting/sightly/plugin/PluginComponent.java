/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.plugin;

import java.util.Dictionary;

import org.apache.sling.commons.osgi.PropertiesUtil;
import org.apache.sling.scripting.sightly.compiler.api.plugin.Plugin;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.ComponentException;

import org.apache.sling.scripting.sightly.compiler.api.plugin.Plugin;

/**
 * Component plugin implementation
 */
public abstract class PluginComponent implements Plugin {

    public static final int DEFAULT_PRIORITY = 100;

    private int priority;
    private String name;

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int compareTo(Plugin o) {
        return this.priority() - o.priority();
    }

    @SuppressWarnings("UnusedDeclaration")
    protected void activate(ComponentContext componentContext) {
        Dictionary properties = componentContext.getProperties();
        priority = PropertiesUtil.toInteger(properties.get(SCR_PROP_NAME_PRIORITY), DEFAULT_PRIORITY);
        name = PropertiesUtil.toString(properties.get(SCR_PROP_NAME_BLOCK_NAME), null);
        if (name == null) {
            throw new ComponentException("The plugin hasn't a valid name specified");
        }
    }
}
