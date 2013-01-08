/**
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

package org.vial;


import org.vial.editor.ConsoleEditor;
import org.vial.utils.Resources;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ConcreteEditorFactory implements EditorFactory {

    private static final String DEFAULT_FLAVOR = "simple";
    private static final String RESOURCE_PATH = "META-INF/services/org/vial/";

    private final Map<String, Class<? extends ConsoleEditor>> flavorMap = new HashMap<String, Class<? extends ConsoleEditor>>();


    /**
     * Creates a {@link ConsoleEditor}.
     *
     * @return
     */
    @Override
    public ConsoleEditor create() throws EditorInitializationException {
        return create(DEFAULT_FLAVOR);
    }

    private Class<? extends ConsoleEditor> resolve(String flavor) throws EditorInitializationException {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            Enumeration<URL> urls = classLoader.getResources(RESOURCE_PATH + flavor);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String txt = Resources.toString(url);
                String[] vialClasses = txt.split("\n");
                for (String vialClass : vialClasses) {
                    if (!vialClass.isEmpty()) {
                        Class clazz = classLoader.loadClass(vialClass);
                        if (ConsoleEditor.class.isAssignableFrom(clazz)) {
                            return clazz;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new EditorInitializationException(e);
        }
        throw new EditorInitializationException("No Editor found for flavor:" + flavor);
    }

    /**
     * Creates a {@link ConsoleEditor} based on the specified flavor.
     *
     * @param flavor
     * @return
     */
    @Override
    public ConsoleEditor create(String flavor) throws EditorInitializationException {
        if (flavorMap.containsKey(flavor)) {
            Class<? extends ConsoleEditor> editorClass = flavorMap.get(flavor);
            try {
                return editorClass.newInstance();
            } catch (Exception e) {
                throw new EditorInitializationException("Failed to create Editor instance of class:" + editorClass.getName(), e);
            }
        } else {
            Class<? extends ConsoleEditor> editorClass = resolve(flavor);
            if (editorClass != null) {
                flavorMap.put(flavor, editorClass);
                return create(flavor);
            } else {
                throw new EditorInitializationException("Unknown flavor:" + flavor);
            }
        }
    }

    /**
     * Binds the specified flavor to the specified class.
     *
     * @param flavor
     * @param editorClass
     */
    @Override
    public void bind(String flavor, Class<? extends ConsoleEditor> editorClass) {
        flavorMap.put(flavor, editorClass);
    }

    /**
     * Unbinds flavor.
     *
     * @param flavor
     */
    @Override
    public void unbind(String flavor) {
        flavorMap.remove(flavor);
    }
}

