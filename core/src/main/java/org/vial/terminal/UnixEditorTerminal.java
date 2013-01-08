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

package org.vial.terminal;

import jline.UnixTerminal;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This is a special {@link UnixTerminal} that allows access to special characters
 * used by the editor, such as CTRL-C, CTRL-Z & CTRL-S.
 */
public class UnixEditorTerminal extends UnixTerminal {

    private Set<String> defaults = new LinkedHashSet<String>();

    public UnixEditorTerminal() throws Exception {
        super();
    }

    @Override
    public void init() throws Exception {
        super.init();
        //Store default values so that we can reuse them when restoring.
        defaults.add(getSettings().get("intr"));
        defaults.add(getSettings().get("susp"));
        defaults.add(getSettings().get("stop"));
        defaults.add(getSettings().get("discard"));
        defaults.add(getSettings().get("lnext"));

        getSettings().set("intr undef");
        //We want to be able to use CTRL-Z for undo
        getSettings().set("susp undef");
        //We want to be able to use CTRL-S for save
        getSettings().set("stop undef");
        //We want to be able to use CTRL-O for open
        getSettings().set("discard undef");
        //We want to be able to use CTRL-V for open
        getSettings().set("lnext undef");
    }

    @Override
    public void restore() throws Exception {
        for (String setting : defaults) {
            getSettings().set(setting);
        }
        super.restore();
    }
}