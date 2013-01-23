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

public class ReplaceCharcterCommand extends AbstractUndoableCommand {

    private Character deleted;
    private final String replace;

    public ReplaceCharcterCommand(ConsoleEditor editor, String replace) {
        super(editor);
        this.replace = replace;
    }

    /**
     * Executes the {@link org.jledit.command.Command}.
     */
    @Override
    public void doExecute() {
        if (!getEditor().isReadOnly()) {
            deleted = getEditor().delete().charAt(0);
            getEditor().put(replace);
            getEditor().moveLeft(replace.length());
        }
    }

    /**
     * Undo the {@link org.jledit.command.Command}.
     */
    @Override
    public void undo() {
        if (!getEditor().isReadOnly() && deleted != null && Character.isLetter(deleted)) {
            getEditor().move(getBeforeLine(), getBeforeColumn());
            for (int c = 0; c < replace.length(); c++) {
                getEditor().delete();
            }
            getEditor().put(deleted.toString());
            getEditor().moveLeft(1);
        }
    }
}
