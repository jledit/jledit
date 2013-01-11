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

package org.jledit.command.file;

import org.jledit.command.Command;
import org.jledit.command.undo.UndoContext;
import org.jledit.command.undo.UndoContextAware;
import org.jledit.editor.ConsoleEditor;

import java.io.File;
import java.io.IOException;

public class FileSaveCommand implements Command, UndoContextAware {

    private final ConsoleEditor editor;
    private UndoContext undoContext;

    public FileSaveCommand(ConsoleEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        try {
            if (editor.getFile() == null) {
                String fileName = editor.readLine("Save to file:");
                editor.save(new File(fileName));
            } else {
                editor.save(null);
            }
            undoContext.clear();
            editor.setDirty(false);
        } catch (IOException e) {
            //noop
        }
    }

    public UndoContext getUndoContext() {
        return undoContext;
    }

    public void setUndoContext(UndoContext undoContext) {
        this.undoContext = undoContext;
    }
}
