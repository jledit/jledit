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

import org.vial.command.Command;
import org.vial.editor.ConsoleEditor;

public class MoveCursorRightCommand implements Command {

    private final ConsoleEditor editor;
    private final int number;

    public MoveCursorRightCommand(ConsoleEditor editor) {
        this(editor, 1);
    }

    public MoveCursorRightCommand(ConsoleEditor editor, int number) {
        this.editor = editor;
        this.number = number;
    }

    @Override
    public void execute() {
        editor.moveRight(number);
        editor.redrawHeader();
        editor.redrawFooter();
    }
}
