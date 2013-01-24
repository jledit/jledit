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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class JlEditWindowsTerminal extends AnsiWindowsTerminal {

    private final AnsiWindowsTerminal delegate;
    private final boolean preExists;

    public JlEditWindowsTerminal(AnsiWindowsTerminal delegate, boolean preExists) throws Exception {
        super();
        this.delegate = delegate;
        this.preExists = preExists;
    }

    @Override
    public void restore() throws Exception {
        if (preExists) {
            delegate.reset();
        } else {
            delegate.restore();
        }
    }

    @Override
    public void setEchoEnabled(boolean enabled) {
        delegate.setEchoEnabled(enabled);
    }

    /**
     * Whether or not to allow the use of the JNI console interaction.
     */
    @Override
    public void setDirectConsole(boolean flag) {
        delegate.setDirectConsole(flag);
    }

    /**
     * Whether or not to allow the use of the JNI console interaction.
     */
    @Override
    public Boolean getDirectConsole() {
        return delegate.getDirectConsole();
    }

    @Override
    public boolean isAnsiSupported() {
        return delegate.isAnsiSupported();
    }

    @Override
    public boolean hasWeirdWrap() {
        return delegate.hasWeirdWrap();
    }

    /**
     * Subclass to change behavior if needed.
     *
     * @return the passed out
     */
    @Override
    public OutputStream wrapOutIfNeeded(OutputStream out) {
        return delegate.wrapOutIfNeeded(out);
    }

    @Override
    public InputStream wrapInIfNeeded(InputStream in) throws IOException {
        if (preExists) {
            return in;
        }
        return delegate.wrapInIfNeeded(in);
    }
}
