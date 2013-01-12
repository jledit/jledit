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


import org.jledit.command.Command;
import org.jledit.ConsoleEditor;

import java.io.IOException;

public class FindPreviousCommand implements Command {

    private final ConsoleEditor editor;
    private String str;

    public FindPreviousCommand(ConsoleEditor editor, String str) {
        this.editor = editor;
        this.str = str;
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute() {
        if ((str == null || str.isEmpty()) && !FindContext.isAvailable()) {
            try {
                str = editor.readLine("Find previous:");
                editor.findPrevious(str);
                FindContext.setLastSearch(str);
            } catch (IOException e) {
                //noop
            }
        } else if (FindContext.isAvailable()) {
            editor.findPrevious(FindContext.getLastSearch());
        } else {
            editor.findPrevious(str);
            FindContext.setLastSearch(str);
        }
    }
}
