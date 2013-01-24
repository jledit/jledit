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

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * A content manager is responsible for loading and saving content.
 */
public interface ContentManager {

    /**
     * Loads content from the specified location.
     * @param location
     * @return
     */
    String load(String location) throws IOException;

    /**
     * Saves content to the specified location.
     * @param content
     * @param location
     * @return
     */
    boolean save(String content, String location);

    /**
     * Saves the {@link String} content to the specified location using the specified {@link Charset}.
     * @param content
     * @param charset
     * @param location
     * @return
     */
    boolean save(String content, Charset charset, String location);

    /**
     * Detect the Charset of the content in the specified location.
     *
     * @param location
     * @return
     */
    Charset detectCharset(String location);
}
