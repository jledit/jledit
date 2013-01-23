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
import org.jledit.command.Command;
import org.jledit.utils.ClipboardUtils;

public class YankCommand implements Command {

    private final ConsoleEditor editor;
    private final int lines;

    public YankCommand(ConsoleEditor editor) {
        this(editor, 1);
    }

    public YankCommand(ConsoleEditor editor, int lines) {
        this.editor = editor;
        this.lines = lines;
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute() {
        StringBuilder yankBuilder = new StringBuilder();
        for (int l = 0; l < lines; l++) {
            try {
                yankBuilder.append(editor.getContent(editor.getLine() + l)).append("\n");
            } catch (Exception ex) {
                //noop
            }
            ClipboardUtils.setContnent(yankBuilder.toString());
        }
    }
}
