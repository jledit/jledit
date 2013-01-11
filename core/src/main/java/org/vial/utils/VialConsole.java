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

package org.vial.utils;


import java.io.BufferedOutputStream;
import java.io.PrintStream;

import static org.fusesource.jansi.AnsiConsole.wrapOutputStream;

/**
 * A wrapper of {@link org.fusesource.jansi.AnsiConsole} which uses non-autoflushing Streams.
 */
public final class VialConsole {

    private VialConsole() {
        //Utility Class
    }

    public static final PrintStream system_out = System.out;
    public static final PrintStream out = new PrintStream(new BufferedOutputStream(wrapOutputStream(system_out), 1024), false);

    public static final PrintStream system_err = System.err;
    public static final PrintStream err = new PrintStream(new BufferedOutputStream(wrapOutputStream(system_err), 1024), false);

    private static int installed;


    /**
     * If the standard out natively supports ANSI escape codes, then this just
     * returns System.out, otherwise it will provide an ANSI aware PrintStream
     * which strips out the ANSI escape sequences or which implement the escape
     * sequences.
     *
     * @return a PrintStream which is ANSI aware.
     */
    public static PrintStream out() {
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
    public static PrintStream err() {
        return err;
    }

    /**
     * Install Console.out to System.out.
     */
    synchronized static public void systemInstall() {
        installed++;
        if (installed == 1) {
            System.setOut(out);
            System.setErr(err);
        }
    }

    /**
     * undo a previous {@link #systemInstall()}.  If {@link #systemInstall()} was called
     * multiple times, it {@link #systemUninstall()} must call the same number of times before
     * it is actually uninstalled.
     */
    synchronized public static void systemUninstall() {
        installed--;
        if (installed == 0) {
            System.setOut(system_out);
            System.setErr(system_err);
        }
    }
}
