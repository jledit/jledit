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

public class DeleteCommand extends AbstractUndoableCommand {

    private final DeleteType type;
    private String deleted;

    public DeleteCommand(ConsoleEditor editor) {
        this(editor, DeleteType.CHARACTER);
    }

    public DeleteCommand(ConsoleEditor editor, DeleteType type) {
        super(editor);
        this.type = type;
    }

    @Override
    public void doExecute() {
        if (!getEditor().isReadOnly()) {
            StringBuilder deletedBuilder = new StringBuilder();
            String currentLine = getEditor().getContent(getEditor().getLine());
            //We want to know how many chars to delete, before we actually delete them.
            int charsToDelete = 1;
            switch (type) {
                case CHARACTER:
                    charsToDelete = 1;
                    break;
                case LINE:
                    getEditor().moveToStartOfLine();
                case TO_END_OF_LINE:
                    charsToDelete = currentLine.length() - getEditor().getColumn() - 1;
            }
            for (int c = 0; c < charsToDelete; c++) {
                deletedBuilder.append(getEditor().delete());
            }
            deleted = deletedBuilder.toString();
            if (deleted != null && !deleted.isEmpty()) {
                getEditor().setDirty(true);
            }
        }
    }

    @Override
    public void undo() {
        if (!getEditor().isReadOnly()) {
            switch (type) {
                case CHARACTER:
                case TO_END_OF_LINE:
                    getEditor().move(getBeforeLine(), getBeforeColumn());
                    break;
                case LINE:
                    getEditor().move(getBeforeLine(), 1);
            }
            getEditor().put(deleted);
        }
    }
}
