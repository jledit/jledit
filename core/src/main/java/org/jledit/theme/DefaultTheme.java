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

package org.jledit.theme;

import org.fusesource.jansi.Ansi;


public class DefaultTheme implements Theme {

    @Override
    public Ansi.Color getHeaderBackground() {
        return null;
    }

    @Override
    public Ansi.Color getHeaderForeground() {
        return Ansi.Color.CYAN;
    }

    @Override
    public Ansi.Color getFooterBackground() {
        return null;
    }

    @Override
    public Ansi.Color getFooterForeground() {
        return Ansi.Color.CYAN;
    }

    @Override
    public Ansi.Color getPromptBackground() {
        return null;
    }

    @Override
    public Ansi.Color getPromptForeground() {
        return Ansi.Color.BLUE;
    }

    @Override
    public Ansi.Color getHighLightBackground() {
        return Ansi.Color.YELLOW;
    }

    @Override
    public Ansi.Color getHighLightForeground() {
        return Ansi.Color.BLACK;
    }
}
