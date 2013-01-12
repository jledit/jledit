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

package org.jledit.command.undo;


import org.jledit.ConsoleEditor;
import org.jledit.command.Command;

public class RedoCommand implements UndoContextAware, Command {

    private final ConsoleEditor editor;
    private UndoContext context;

    public RedoCommand(ConsoleEditor editor) {
        this.editor = editor;
        this.context = new UndoContext();
    }

    public RedoCommand(ConsoleEditor editor, UndoContext context) {
        this.editor = editor;
        this.context = context;
    }

    public void setUndoContext(UndoContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        UndoableCommand undoableCommand = context.redoPop();
        if (undoableCommand != null) {
            undoableCommand.redo();
            context.undoPush(undoableCommand);
        }
    }
}
