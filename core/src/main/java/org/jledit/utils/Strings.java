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


import java.io.File;

public final class Strings {

    private Strings() {
        //Utility Class
    }

    /**
     * Try to trim a String to the given size.
     *
     * @param s         The String to trim.
     * @param length    The trim length.
     * @return
     */
    public static final String tryToTrimToSize(String s, int length) {
        return tryToTrimToSize(s, length, false);
    }

    /**
     * Try to trim a String to the given size.
     *
     * @param s             The String to trim.
     * @param length        The trim length.
     * @param trimSuffix    Flag the specifies if trimming should be applied to the suffix of the String.
     * @return
     */
    public static final String tryToTrimToSize(String s, int length, boolean trimSuffix) {
        if (s == null || s.isEmpty()) {
            return s;
        } else if (s.length() <= length) {
            return s;
        } else if (s.contains(File.separator)) {
            String before = s.substring(0, s.lastIndexOf(File.separator));
            String after = s.substring(s.lastIndexOf(File.separator));
            if (!trimSuffix && length - after.length() > 4) {
                return Strings.tryToTrimToSize(before, length - after.length(), true) + after;
            } else {
                return Strings.tryToTrimToSize(before, length - 3, true) + File.separator + "..";
            }
        } else {
            return "..";
        }
    }
}
