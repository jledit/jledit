/*
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

public class ChangeCaseCommand extends AbstractUndoableCommand {

    private Character deleted;

    public ChangeCaseCommand(ConsoleEditor editor) {
        super(editor);
    }

    /**
     * Executes the {@link org.jledit.command.Command}.
     */
    @Override
    public void doExecute() {
        if (!getEditor().isReadOnly()) {
            deleted = getEditor().delete().charAt(0);
            if (Character.isLetter(deleted) && Character.isLowerCase(deleted)) {
                getEditor().put(deleted.toString().toUpperCase());
                getEditor().moveLeft(1);
            } else {
                if (Character.isLetter(deleted) && Character.isUpperCase(deleted)) {
                    getEditor().put(deleted.toString().toLowerCase());
                    getEditor().moveLeft(1);
                }
            }
        }
    }

    /**
     * Undo the {@link org.jledit.command.Command}.
     */
    @Override
    public void undo() {
        if (!getEditor().isReadOnly() && deleted != null && Character.isLetter(deleted)) {
            getEditor().move(getBeforeLine(), getBeforeColumn());
            getEditor().delete();
            getEditor().put(deleted.toString());
            getEditor().moveLeft(1);
        }
    }
}
