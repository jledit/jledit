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

package org.vial.command.editor;

import org.vial.command.undo.UndoableCommand;
import org.vial.editor.Editor;

/**
 *
 */
public abstract class AbstractUndoableCommand implements UndoableCommand {

    final Editor editor;
    int line;
    int column;

    public AbstractUndoableCommand(Editor editor) {
        this.editor = editor;
    }

    /**
     * Moves cursor to the right place for the undo operation.
     */
    @Override
    public void undo() {
        editor.move(line, column);
    }

    /**
     * Stores cursor coordinates.
     */
    @Override
    public void execute() {
        line = editor.getLine();
        column = editor.getColumn();
    }
}
