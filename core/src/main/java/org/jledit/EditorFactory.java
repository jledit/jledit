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

public interface EditorFactory {

    /**
     * Creates a {@link ConsoleEditor}.
     *
     * @return
     */
    ConsoleEditor create() throws EditorInitializationException;

    /**
     * Creates a {@link ConsoleEditor} using the specified {@link Terminal}.
     *
     * @return
     * @throws EditorInitializationException
     */
    ConsoleEditor create(Terminal terminal) throws EditorInitializationException;


    /**
     * Creates a {@link ConsoleEditor} based on the specified flavor.
     *
     * @param flavor
     * @return
     */
    ConsoleEditor create(String flavor) throws EditorInitializationException;

    /**
     * Creates a {@link ConsoleEditor} based on the specified flavor & {@link Terminal}.
     *
     * @param flavor
     * @param terminal
     * @return
     * @throws EditorInitializationException
     */
    ConsoleEditor create(String flavor, Terminal terminal) throws EditorInitializationException;

    /**
     * Binds the specified flavor to the specified class.
     *
     * @param flavor
     * @param editorClass
     */
    void bind(String flavor, Class<? extends ConsoleEditor> editorClass);

    /**
     * Unbinds flavor.
     *
     * @param flavor
     */
    void unbind(String flavor);

}
