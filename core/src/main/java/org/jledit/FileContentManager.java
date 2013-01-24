/*
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

package org.jledit;

import org.jledit.utils.Files;
import org.jledit.utils.internal.Charsets;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * A {@link ContentManager} implementation for saving and loading from {@link File}.
 */
public class FileContentManager implements ContentManager {

    /**
     * Loads content from the specified location.
     *
     * @param location
     * @return
     */
    @Override
    public String load(String location) throws IOException {
        File file = new File(location);
        return Files.toString(file, detectCharset(location));
    }

    /**
     * Saves the {@link String} content to the specified location using the specified {@link java.nio.charset.Charset}.
     *
     * @param content
     * @param charset
     * @param location
     * @return
     */
    @Override
    public boolean save(String content, Charset charset, String location) {
        File file = new File(location);
        try {
            Files.writeToFile(file, content, charset);
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    /**
     * Saves content to the specified location.
     *
     * @param content
     * @param location
     * @return
     */
    @Override
    public boolean save(String content, String location) {
        return save(content, Charsets.UTF_8, location);
    }

    @Override
    public Charset detectCharset(String location) {
        return Charsets.detect(new File(location));
    }


}
