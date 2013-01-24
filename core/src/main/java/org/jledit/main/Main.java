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

package org.jledit.main;

import org.jledit.ConcreteEditorFactory;
import org.jledit.EditorFactory;
import org.jledit.ConsoleEditor;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        ConsoleEditor editor = null;
        try {
            String fileName = args.length > 0 ? args[0] : null;
            EditorFactory factory = new ConcreteEditorFactory();
            editor = factory.create();
            if (fileName != null) {
                editor.open(fileName);
            }
            editor.start();
        } catch (Exception e) {
            if (editor != null) {
                editor.stop();
            }
        }
    }
}
