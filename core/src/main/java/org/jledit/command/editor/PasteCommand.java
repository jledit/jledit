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
import org.jledit.utils.ClipboardUtils;

public class PasteCommand extends AbstractUndoableCommand {

    private final String clipboardContent;
    private final Position position;

    public PasteCommand(ConsoleEditor editor) {
        this(editor, Position.CURRENT);
    }

    public PasteCommand(ConsoleEditor editor, Position position) {
        super(editor);
        this.position = position;
        this.clipboardContent = ClipboardUtils.getContnet();
    }

    @Override
    public void doExecute() {
        if (!getEditor().isReadOnly()) {
            getEditor().setDirty(true);
            if (!clipboardContent.isEmpty()) {
                switch (position) {
                    case CURRENT:
                        getEditor().put(clipboardContent);
                        break;
                    case NEXT_LINE:
                        getEditor().moveToEndOfLine();
                        getEditor().newLine();
                        getEditor().put(clipboardContent);
                        break;
                    case PREVIOUS_LINE:
                        getEditor().moveToStartOfLine();
                        getEditor().newLine();
                        getEditor().moveUp(1);
                        getEditor().put(clipboardContent);
                        break;
                }
            }
        }
    }

    @Override
    public void undo() {
        if (!getEditor().isReadOnly()) {
            switch (position) {
                case NEXT_LINE:
                    getEditor().move(getBeforeLine(), getBeforeColumn());
                    getEditor().mergeLine();
                case CURRENT:
                    getEditor().move(getBeforeLine(), getBeforeColumn());
                    for (int i = 0; i < clipboardContent.length(); i++) {
                        getEditor().delete();
                    }
                    break;
                case PREVIOUS_LINE:
                    getEditor().move(getBeforeLine() - 1, getBeforeColumn());
                    getEditor().mergeLine();
                    getEditor().move(getBeforeLine(), getBeforeColumn());
                    for (int i = 0; i < clipboardContent.length(); i++) {
                        getEditor().delete();
                    }
                    break;
            }
        }
    }
}
