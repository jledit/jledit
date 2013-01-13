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

package org.jledit.terminal;

import jline.AnsiWindowsTerminal;
import jline.Terminal;
import jline.TerminalFactory;
import jline.UnixTerminal;
import jline.WindowsTerminal;


public final class JlEditTerminalFactory {

    private JlEditTerminalFactory() {
        //Utility Class
    }

    /**
     * Returns a wrapped {@link Terminal}.
     *
     * @return
     * @throws Exception
     */
    public static Terminal get() throws Exception {
        return get(TerminalFactory.get(), false);
    }

    /**
     * Wraps the existing {@link Terminal}.
     * If {@link Terminal} is null, the terminal will get restored, when editor quites.
     * If {@link Terminal} is not null, a rest will be called instead.
     *
     * @param terminal
     * @return
     * @throws Exception
     */
    public static Terminal get(Terminal terminal) throws Exception {
        if (terminal == null) {
            return get(TerminalFactory.get(), false);
        } else {
            return get(terminal, true);
        }
    }

    /**
     * Wraps a {@link Terminal}.
     * @param terminal
     * @param preExists
     * @return
     * @throws Exception
     */
    public static Terminal get(Terminal terminal, boolean preExists) throws Exception {
        if (UnixTerminal.class.isAssignableFrom(terminal.getClass())) {
            return new JlEditUnixTerminal((UnixTerminal) terminal, preExists);
        } else if (WindowsTerminal.class.isAssignableFrom(terminal.getClass())) {
            return new JlEditWindowsTerminal((AnsiWindowsTerminal) terminal, preExists);
        } else {
            return terminal;
        }
    }
}