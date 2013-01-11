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

package org.vial.editor;

import jline.Terminal;
import org.vial.theme.Theme;

public interface ConsoleEditor extends Editor<String>, InputReader, LifeCycle {
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
     * @param offset The number of lines to move upwards.
     */
    void moveUp(int offset);

    /**
     * Moves cursor down.
     * If the end of file is reached it does nothing.
     * @param offset The number of lines to move downwards.
     */
    void moveDown(int offset);

    /**
     * Moves cursor left.
     * If the beggining of the line is reached. It move the cursor to the end of the previous line.
     * @param offset The number of lines to move.
     */
    void moveLeft(int offset);

    /**
     * Moves cursor left.
     * If the end of line is reached it moves the cursor to the beggining of the next line.
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
     * @return The size of the header or 0 if the flavor doesn't use a header.
     */
    int getHeaderSize();

    /**
     * Returns the size in lines of the footer.
     * @return The size of the footer or 0 if the flavor doesn't use a footer.
     */
    int getFooterSize();

    /**
     * Returns the {@link Terminal} used by the {@link ConsoleEditor}.
     * @return
     */
    Terminal getTerminal();

    /**
     * Sets the {@link Theme} of the editor.
     * @param theme
     */
    void setTheme(Theme theme);

    /**
     * Returns the {@link Theme}.
     * @return
     */
    Theme getTheme();
}
