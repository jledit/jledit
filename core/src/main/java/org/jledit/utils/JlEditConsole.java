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

package org.jledit.utils;


import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.fusesource.jansi.AnsiConsole.wrapOutputStream;

/**
 * A wrapper of {@link org.fusesource.jansi.AnsiConsole} which uses non-autoflushing Streams.
 */
public final class JlEditConsole {

    private final InputStream in;
    private final PrintStream out;
    private final PrintStream err;

    public JlEditConsole() {
        this.in = System.in;
        this.out  = new PrintStream(new BufferedOutputStream(wrapOutputStream(System.out), 1024), false);
        this.err = new PrintStream(new BufferedOutputStream(wrapOutputStream(System.err), 1024), false);
    }

    public JlEditConsole(InputStream in, PrintStream out, PrintStream err) {
        this.in = in;
        this.out = out;
        this.err = err;
    }

    private static AtomicInteger installed = new AtomicInteger();


    /**
     * If the standard out natively supports ANSI escape codes, then this just
     * returns System.out, otherwise it will provide an ANSI aware PrintStream
     * which strips out the ANSI escape sequences or which implement the escape
     * sequences.
     *
     * @return a PrintStream which is ANSI aware.
     */
    public PrintStream out() {
        return out;
    }

    /**
     * If the standard out natively supports ANSI escape codes, then this just
     * returns System.err, otherwise it will provide an ANSI aware PrintStream
     * which strips out the ANSI escape sequences or which implement the escape
     * sequences.
     *
     * @return a PrintStream which is ANSI aware.
     */
    public PrintStream err() {
        return err;
    }
}
