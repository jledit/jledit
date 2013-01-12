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


import java.io.IOException;

public interface InputReader {

    /**
     * Reads a character from the user input.
     *
     * @return
     * @throws java.io.IOException
     */
    int read() throws IOException;

    /**
     * Reads a boolean from the user input.
     * The mapping of the user input to a boolean value is specified by the implementation.
     *
     * @return
     */
    boolean readBoolean() throws IOException;

    /**
     * Displaysa a message and reads a boolean from the user input.
     * The mapping of the user input to a boolean value is specified by the implementation.
     *
     * @param prompt
     * @param defaultValue
     * @return
     */
    boolean readBoolean(String prompt, Boolean defaultValue) throws IOException;


    /**
     * Reads a line from the user input.
     *
     * @return
     * @throws IOException
     */
    String readLine() throws IOException;

    /**
     * Displays a message and prompts the user for input.
     * Returns a single line.
     *
     * @param prompt
     * @return
     * @throws IOException
     */
    String readLine(String prompt) throws IOException;
}
