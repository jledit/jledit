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


import jline.TerminalSupport;
import jline.UnixTerminal;
import org.jledit.jline.TerminalLineSettings;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JlEditUnixTerminal extends TerminalSupport {

    private final UnixTerminal delegate;
    private final boolean preExists;
    private final TerminalLineSettings settings = new TerminalLineSettings();

    JlEditUnixTerminal(UnixTerminal delegate, boolean preExists) throws Exception {
        super(true);
        this.delegate = delegate;
        this.preExists = preExists;
        this.setUpControlKeys();
    }

    public final void setUpControlKeys() throws Exception {
        settings.set("intr undef");
        //We want to be able to use CTRL-Z for undo
        settings.set("susp undef");
        //We want to be able to use CTRL-S for save
        settings.set("stop undef");
        //We want to be able to use CTRL-O for open
        settings.set("discard undef");
        //We want to be able to use CTRL-V for open
        settings.set("lnext undef");
    }


    @Override
    public void restore() throws Exception {
        if (preExists) {
            delegate.reset();
        } else {
            delegate.restore();
        }
    }

    /**
     * Returns the value of <tt>stty columns</tt> param.
     */
    @Override
    public int getWidth() {
        int w = settings.getProperty("columns");
        return w < 1 ? DEFAULT_WIDTH : w;
    }

    /**
     * Returns the value of <tt>stty rows>/tt> param.
     */
    @Override
    public int getHeight() {
        int h = settings.getProperty("rows");
        return h < 1 ? DEFAULT_HEIGHT : h;
    }

    /**
     * Subclass to change behavior if needed.
     * @return the passed out
     */
    @Override
    public OutputStream wrapOutIfNeeded(OutputStream out) {
        return delegate.wrapOutIfNeeded(out);
    }

    @Override
    public InputStream wrapInIfNeeded(InputStream in) throws IOException {
        return delegate.wrapInIfNeeded(in);
    }
}