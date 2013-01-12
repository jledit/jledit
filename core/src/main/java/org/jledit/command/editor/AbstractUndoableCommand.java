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

package org.jledit.command.editor;

import org.jledit.command.undo.UndoableCommand;
import org.jledit.ConsoleEditor;

/**
 *
 */
public abstract class AbstractUndoableCommand implements UndoableCommand {

    private final ConsoleEditor editor;
    private int beforeLine;
    private int beforeColumn;
    private int afterLine;
    private int afterColumn;

    public AbstractUndoableCommand(ConsoleEditor editor) {
        this.editor = editor;
    }


    /**
     * Stores cursor coordinates.
     */
    @Override
    public final void execute() {
        beforeLine = editor.getLine();
        beforeColumn = editor.getColumn();
        doExecute();
        afterLine = editor.getLine();
        afterColumn = editor.getColumn();
    }

    /**
     * Executes the {@link org.jledit.command.Command} again.
     */
    @Override
    public final void redo() {
        if (!getEditor().isReadOnly()) {
            getEditor().move(getBeforeLine(), getBeforeColumn());
            doExecute();
        }
    }

    public ConsoleEditor getEditor() {
        return editor;
    }

    public int getBeforeLine() {
        return beforeLine;
    }

    public void setBeforeLine(int line) {
        this.beforeLine = line;
    }

    public int getBeforeColumn() {
        return beforeColumn;
    }

    public void setBeforeColumn(int column) {
        this.beforeColumn = column;
    }

    public int getAfterLine() {
        return afterLine;
    }

    public void setAfterLine(int afterLine) {
        this.afterLine = afterLine;
    }

    public int getAfterColumn() {
        return afterColumn;
    }

    public void setAfterColumn(int afterColumn) {
        this.afterColumn = afterColumn;
    }
}
