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


import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/**
 * The Editor interface describes all the text manipulation methods.
 * It includes methods for File manipulation, navigation and text modification.
 *
 * @param <C>
 */
public interface Editor<C> extends Closeable {

    String NEW_LINE = "\n";
    String CARRIEGE_RETURN = "\r";


    /**
     * Opens a file for editing.
     *
     * @param source
     * @throws IOException
     */
    void open(File source) throws IOException;

    /**
     * Saves the file.
     *
     * @param target
     * @throws IOException
     */
    void save(File target) throws IOException;

    /**
     * Returns the number of the current line.
     * This method refers to the actual line in the file.
     * Line numbering starts with 1.
     *
     * @return
     */
    int getLine();


    /**
     * Returns the current column.
     * This method refers to the actual line in the file.
     * Column numbering starts with 1.
     *
     * @return
     */
    int getColumn();

    /**
     * Moves the cursor to the specified line and column.
     *
     * @param line
     * @param column
     */
    void move(int line, int column);

    /**
     * Moves the cursors to the start of the line.
     */
    void home();

    /**
     * Moves cursor to the end of the line.
     */
    void end();


    /**
     * Puts the {@code link} to the current position.
     *
     * @param str
     */
    void put(String str);

    /**
     * Deletes the current character, shifting remaining characters one position left.
     */
    String delete();


    /**
     * Deletes the previous character if exists.
     */
    String backspace();

    /**
     * Adds a new line to the current position.
     */
    void newLine();

    /**
     * Removes the new line character at the end of the line.
     */
    void mergeLine();


    /**
     * Finds the next appearance of the String.
     *
     * @param str
     */
    void findNext(String str);


    /**
     * Returns the number of lines.
     *
     * @return
     */
    int lines();

    /**
     * Finds the next appearance of the String.
     *
     * @param str
     */
    void findPrevious(String str);

    /**
     * Marks that the editor has unsaved changes.
     *
     * @param dirty
     */
    void setDirty(Boolean dirty);

    /**
     * Returns if editor contains 'dirty' data.
     *
     * @return
     */
    Boolean isDirty();


    /**
     * Returns the content of the file.
     *
     * @return
     */
    C getContent();

    /**
     * Returns the content of the file.
     *
     * @return
     */
    C getContent(int line);

    /**
     * Returns the {@link File} being edited.
     *
     * @return
     */
    File getFile();

}
