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

package org.jledit.utils.internal;


import org.mozilla.universalchardet.UniversalDetector;
import org.jledit.utils.Closeables;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;

public final class Charsets {

    public static final Charset UTF_8 = Charset.forName("UTF-8");
    static final int BUFFER_SIZE = 4096;

    private Charsets() {
        //Utility Class
    }

    public static Charset detect(File file) {
        FileInputStream fis = null;
        UniversalDetector detector = new UniversalDetector(null);
        try {
            byte[] buf = new byte[BUFFER_SIZE];
            fis = new FileInputStream(file);
            int nread;
            while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }
            detector.dataEnd();
            return Charset.forName(detector.getDetectedCharset());
        } catch (Exception e) {
            return Charset.defaultCharset();
        } finally {
            Closeables.closeQuitely(fis);
        }
    }
}
