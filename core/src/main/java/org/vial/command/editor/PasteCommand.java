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

package org.vial.command.editor;

import org.vial.editor.Editor;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;

public class PasteCommand extends AbstractUndoableCommand {

    private final String clipboardContent;

    public PasteCommand(Editor editor) {
        super(editor);
        this.clipboardContent = getClipboardContent();
    }

    @Override
    public void execute() {
        if (!clipboardContent.isEmpty()) {
            editor.put(clipboardContent);
            editor.setDirty(true);
        }
        super.execute();
    }

    @Override
    public void undo() {
        super.undo();
        for (int i = 0; i < clipboardContent.length(); i++) {
            editor.backspace();
        }
    }

    public final String getClipboardContent() {
        String result = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            result = (String) clipboard.getData(DataFlavor.stringFlavor);
        } catch (Exception ex) {
            //noop
        }
        return result;
    }
}
