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

package org.vial.command.editor;


public final class FindContext {

    private FindContext() {
        //Utility Class
    }

    private static String lastSearch = null;

    public static String getLastSearch() {
        return lastSearch;
    }

    public static void setLastSearch(String lastSearch) {
        FindContext.lastSearch = lastSearch;
    }

    public static void clear() {
        FindContext.lastSearch = null;
    }

    public static boolean isAvailable() {
        return FindContext.lastSearch != null && !FindContext.lastSearch.isEmpty();
    }
}
