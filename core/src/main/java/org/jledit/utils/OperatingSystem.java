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


public final class OperatingSystem {

    private OperatingSystem() {
        //Utility Class
    }

    private static final String MAC = "mac";
    private static final String OPERATING_SYSTEM = System.getProperty("os.name").toLowerCase();

    public static boolean isOsx() {
        return (OPERATING_SYSTEM.indexOf(MAC) >= 0);
    }

}
