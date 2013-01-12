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

import org.jledit.ConsoleEditor;

public class BackspaceCommand extends AbstractUndoableCommand {


    private String deleted;

    public BackspaceCommand(ConsoleEditor editor) {
        super(editor);
    }

    @Override
    public void execute() {
        if (!getEditor().isReadOnly()) {
            deleted = getEditor().backspace();
            if (deleted != null && !deleted.isEmpty()) {
                getEditor().setDirty(true);
            }
            super.execute();
        }
    }

    @Override
    public void undo() {
        super.undo();
        getEditor().put(deleted);
    }

}
