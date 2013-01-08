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


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

public final class Resources {

    static final int BUFFER_SIZE = 4096;

    private Resources() {
        //Utility Class
    }

    /**
     * Reads a {@link java.net.URL} and returns a {@String}.
     *
     * @param url
     * @param charset
     * @return
     * @throws java.io.IOException
     */
    public static String toString(URL url, Charset charset) throws IOException {
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = url.openStream();
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int remaining;
            while ((remaining = is.read(buffer)) > 0) {
                bos.write(buffer, 0, remaining);
            }
            if (charset != null) {
                return new String(bos.toByteArray(), charset);
            } else {
                return new String(bos.toByteArray());
            }
        } finally {
            Closeables.closeQuitely(is);
            Closeables.closeQuitely(bos);
        }
    }

    /**
     * Reads a {@link java.net.URL} and returns a {@String}.
     *
     * @param url
     * @return
     * @throws java.io.IOException
     */
    public static String toString(URL url) throws IOException {
        return toString(url, null);
    }

}
