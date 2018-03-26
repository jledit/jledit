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

package org.jledit;


import jline.Terminal;
import org.jledit.utils.Resources;

import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ConcreteEditorFactory implements EditorFactory {

    private static final String DEFAULT_FLAVOR = "simple";
    private static final String RESOURCE_PATH = "META-INF/services/org/jledit/";

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

    /**
     * Creates a {@link ConsoleEditor} using the specified {@link jline.Terminal}.
     *
     * @return
     * @throws org.jledit.EditorInitializationException
     *
     */
    @Override
    public ConsoleEditor create(Terminal terminal) throws EditorInitializationException {
        return create(DEFAULT_FLAVOR, terminal);
    }

    /**
     * Creates a {@link ConsoleEditor} based on the specified flavor.
     *
     * @param flavor
     * @return
     */
    @Override
    public ConsoleEditor create(String flavor) throws EditorInitializationException {
        return create(flavor, null);
    }

    /**
     * Creates a {@link ConsoleEditor} based on the specified flavor & {@link jline.Terminal}.
     *
     * @param flavor
     * @param terminal
     * @return
     * @throws org.jledit.EditorInitializationException
     *
     */
    @Override
    public ConsoleEditor create(String flavor, Terminal terminal) throws EditorInitializationException {
        return create(flavor, terminal, System.in, System.out);
    }


    /**
     * Creates a {@link ConsoleEditor} based on the specified flavor & {@link jline.Terminal}.
     *
     * @param flavor
     * @param terminal
     * @return
     * @throws org.jledit.EditorInitializationException
     *
     */
    @Override
    public ConsoleEditor create(String flavor, Terminal terminal, InputStream in, PrintStream out) throws EditorInitializationException {
        if (flavorMap.containsKey(flavor)) {
            Class<? extends ConsoleEditor> editorClass = flavorMap.get(flavor);
            try {
                return instantiate(editorClass, terminal, in, out);
            } catch (Exception e) {
                throw new EditorInitializationException("Failed to create Editor instance of class:" + editorClass.getName(), e);
            }
        } else {
            Class<? extends ConsoleEditor> editorClass = resolve(flavor);
            if (editorClass != null) {
                flavorMap.put(flavor, editorClass);
                return create(flavor, terminal);
            } else {
                throw new EditorInitializationException("Unknown flavor:" + flavor);
            }
        }
    }

    /**
     * Resolves the {@link ConsoleEditor} class for the specified flavor.
     * @param flavor
     * @return
     * @throws EditorInitializationException
     */
    private Class<? extends ConsoleEditor> resolve(String flavor) throws EditorInitializationException {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            Enumeration<URL> urls = classLoader.getResources(RESOURCE_PATH + flavor);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String txt = Resources.toString(url);
                String[] jleditClasses = txt.split("\n");
                for (String jleditClass : jleditClasses) {
                    if (!jleditClass.isEmpty()) {
                        Class clazz = classLoader.loadClass(jleditClass);
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
     * Instantiates the {@link ConsoleEditor}.
     * @param editorClass
     * @param terminal
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     */
    private ConsoleEditor instantiate(Class<? extends ConsoleEditor> editorClass, Terminal terminal, InputStream in , PrintStream out) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor constructor = editorClass.getConstructor(Terminal.class, InputStream.class, PrintStream.class);
        return (ConsoleEditor) constructor.newInstance(terminal, in , out);
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

