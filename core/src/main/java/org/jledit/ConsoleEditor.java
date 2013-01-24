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
import org.jledit.theme.Theme;

import java.io.IOException;

public interface ConsoleEditor extends Editor<String>, InputReader, LifeCycle {

    /**
     * Opens a file for editing.
     *
     * @param source
     * @param displayAs The display String (if flavor supports it).
     * @throws IOException
     */
    void open(String source, String displayAs) throws IOException;


    /**
     * Shows the editor.
     */
    void show();

    /**
     * Hides the editor.
     */
    void hide();

    /**
     * Moves cursors up.
     * If the beggining of the files is reached it does nothing.
     *
     * @param offset The number of lines to move upwards.
     */
    void moveUp(int offset);

    /**
     * Moves cursor down.
     * If the end of file is reached it does nothing.
     *
     * @param offset The number of lines to move downwards.
     */
    void moveDown(int offset);

    /**
     * Moves cursor left.
     * If the beggining of the line is reached. It move the cursor to the end of the previous line.
     *
     * @param offset The number of lines to move.
     */
    void moveLeft(int offset);

    /**
     * Moves cursor left.
     * If the end of line is reached it moves the cursor to the beggining of the next line.
     *
     * @param offset The number of lines to move.
     */
    void moveRight(int offset);

    /**
     * Redraws the text that fits inside the current frame.
     */
    void redrawText();


    /**
     * Redraws the header of the editor (if exists).
     */
    void redrawHeader();

    /**
     * Redraws the footer of the editor (if exists).
     */
    void redrawFooter();

    /**
     * Redraws the cursor coordinates if the flavor supports that.
     */
    void redrawCoords();

    /**
     * Saves the cursor position.
     */
    void saveCursorPosition();

    /**
     * Moves the cursor to the last saved position.
     */
    void restoreCursorPosition();

    /**
     * Returns the size in lines of the header.
     *
     * @return The size of the header or 0 if the flavor doesn't use a header.
     */
    int getHeaderSize();

    /**
     * Returns the size in lines of the footer.
     *
     * @return The size of the footer or 0 if the flavor doesn't use a footer.
     */
    int getFooterSize();

    /**
     * Returns the {@link Terminal} used by the {@link ConsoleEditor}.
     *
     * @return
     */
    Terminal getTerminal();

    /**
     * Sets the {@link Theme} of the editor.
     *
     * @param theme
     */
    void setTheme(Theme theme);

    /**
     * Returns the {@link Theme}.
     *
     * @return
     */
    Theme getTheme();

    /**
     * Returns the {@link ConsoleEditor} title.
     *
     * @return
     */
    String getTitle();

    /**
     * Sets the {@link ConsoleEditor} title.
     *
     * @param title
     */
    void setTitle(String title);

    /**
     * Checks if the {@link ConsoleEditor} is set to be read only.
     *
     * @return
     */
    boolean isReadOnly();

    /**
     * Sets/Unsets the {@link ConsoleEditor} to read only mode.
     *
     * @param readOnly
     */
    void setReadOnly(boolean readOnly);


    /**
     * Checks if the {@link ConsoleEditor} can open new file.
     *
     * @return
     */
    boolean isOpenEnabled();


    /**
     * Allows/disallows the {@link ConsoleEditor} to open files.
     *
     * @param openEnabled
     */
    void setOpenEnabled(boolean openEnabled);
}
