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

package org.vial.utils.internal;

import jline.console.KeyMap;
import org.vial.editor.EditorOperationType;

public final class KeyMaps {

    private KeyMaps() {
        //Utility Class
    }

    public static void bindArrowKeys(KeyMap map) {
        // MS-DOS
        map.bind("\033[0A", EditorOperationType.UP);
        map.bind("\033[0B", EditorOperationType.RIGHT);
        map.bind("\033[0C", EditorOperationType.RIGHT);
        map.bind("\033[0D", EditorOperationType.DOWN);

        // Windows
        map.bind("\340\107", EditorOperationType.HOME);
        map.bind("\340\110", EditorOperationType.UP);
        map.bind("\340\113", EditorOperationType.LEFT);
        map.bind("\340\115", EditorOperationType.RIGHT);
        map.bind("\340\117", EditorOperationType.END);
        map.bind("\340\120", EditorOperationType.DOWN);

        map.bind("\340\123", EditorOperationType.DELETE);
        map.bind("\000\110", EditorOperationType.UP);
        map.bind("\000\113", EditorOperationType.LEFT);
        map.bind("\000\115", EditorOperationType.RIGHT);
        map.bind("\000\120", EditorOperationType.DOWN);
        map.bind("\000\123", EditorOperationType.DELETE);

        map.bind("\033[A", EditorOperationType.UP);
        map.bind("\033[B", EditorOperationType.DOWN);
        map.bind("\033[C", EditorOperationType.RIGHT);
        map.bind("\033[D", EditorOperationType.LEFT);
        map.bind("\033[H", EditorOperationType.HOME);
        map.bind("\033[F", EditorOperationType.END);

        map.bind("\033[OA", EditorOperationType.UP);
        map.bind("\033[OB", EditorOperationType.DOWN);
        map.bind("\033[OC", EditorOperationType.RIGHT);
        map.bind("\033[OD", EditorOperationType.LEFT);
        map.bind("\033[OH", EditorOperationType.HOME);
        map.bind("\033[OF", EditorOperationType.END);

        map.bind("\033[3~", EditorOperationType.DELETE);

        // MINGW32
        map.bind("\0340H", EditorOperationType.UP);
        map.bind("\0340P", EditorOperationType.DOWN);
        map.bind("\0340M", EditorOperationType.RIGHT);
        map.bind("\0340K", EditorOperationType.LEFT);
    }
}
