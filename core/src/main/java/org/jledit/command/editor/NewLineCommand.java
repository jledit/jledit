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

public class NewLineCommand extends AbstractUndoableCommand {

    private final Position position;

    public NewLineCommand(ConsoleEditor editor) {
        this(editor, Position.CURRENT);
    }

    public NewLineCommand(ConsoleEditor editor, Position position) {
        super(editor);
        this.position = position;
    }

    public void doExecute() {

        if (!getEditor().isReadOnly()) {
            getEditor().setDirty(true);
            switch (position) {
                case CURRENT:
                    getEditor().newLine();
                    break;
                case NEXT_LINE:
                    getEditor().moveToEndOfLine();
                    getEditor().newLine();
                    break;
                case PREVIOUS_LINE:
                    getEditor().moveToStartOfLine();
                    getEditor().newLine();
                    getEditor().moveUp(1);
                    break;
            }
        }
    }


    @Override
    public void undo() {
        if (!getEditor().isReadOnly()) {
            switch (position) {
                case CURRENT:
                case NEXT_LINE:
                    getEditor().move(getBeforeLine(), getBeforeColumn());
                    getEditor().mergeLine();
                    break;
                case PREVIOUS_LINE:
                    getEditor().move(getBeforeLine() - 1, getBeforeColumn());
                    getEditor().mergeLine();
                    break;
            }
        }
    }
}
