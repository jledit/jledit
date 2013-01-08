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

package org.vial.command.undo;

import org.vial.command.Command;
import org.vial.editor.Editor;

public class UndoCommand implements UndoContextAware, Command {

    private UndoContext context;
    private final Editor editor;

    public UndoCommand(Editor editor) {
        this.context = new UndoContext();
        this.editor = editor;
    }

    public UndoCommand(Editor editor, UndoContext context) {
        this.editor = editor;
        this.context = context;
    }

    public void setUndoContext(UndoContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        if (context != null) {
            UndoableCommand undoableCommand = context.undoPop();
            if (undoableCommand != null) {
                undoableCommand.undo();
            }
            context.redoPush(undoableCommand);
            editor.setDirty(context.isDirty());
        }
    }
}
