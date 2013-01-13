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
package org.jledit.jline;


import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class is "borrowed" from jline.
 * We cannot reuse the class from jline as its not exported and using it as private leads to issues.
 */
public final class TerminalLineSettings
{
    public static final String JLINE_STTY = "jline.stty";

    public static final String DEFAULT_STTY = "stty";

    public static final String JLINE_SH = "jline.sh";

    public static final String DEFAULT_SH = "sh";

    private String sttyCommand;

    private String shCommand;

    private String config;

    private long configLastFetched;

    public TerminalLineSettings() throws IOException, InterruptedException {
        sttyCommand = JlineConfiguration.getString(JLINE_STTY, DEFAULT_STTY);
        shCommand = JlineConfiguration.getString(JLINE_SH, DEFAULT_SH);
        config = get("-a");
        configLastFetched = System.currentTimeMillis();

        // sanity check
        if (config.length() == 0) {
            throw new IOException(MessageFormat.format("Unrecognized stty code: {0}", config));
        }
    }

    public String getConfig() {
        return config;
    }

    public void restore() throws IOException, InterruptedException {
        set("sane");
    }

    public String get(final String args) throws IOException, InterruptedException {
        return stty(args);
    }

    public void set(final String args) throws IOException, InterruptedException {
        stty(args);
    }

    /**
     * <p>
     * Get the value of a stty property, including the management of a cache.
     * </p>
     *
     * @param name the stty property.
     * @return the stty property value.
     */
    public int getProperty(String name) {
        checkNotNull(name);
        try {
            // tty properties are cached so we don't have to worry too much about getting term widht/height
            if (config == null || System.currentTimeMillis() - configLastFetched > 1000 ) {
                config = get("-a");
                configLastFetched = System.currentTimeMillis();
            }
            return this.getProperty(name, config);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * <p>
     * Parses a stty output (provided by stty -a) and return the value of a given property.
     * </p>
     *
     * @param name property name.
     * @param stty string resulting of stty -a execution.
     * @return value of the given property.
     */
    protected static int getProperty(String name, String stty) {
        // try the first kind of regex
        Pattern pattern = Pattern.compile(name + "\\s+=\\s+([^;]*)[;\\n\\r]");
        Matcher matcher = pattern.matcher(stty);
        if (!matcher.find()) {
            // try a second kind of regex
            pattern = Pattern.compile(name + "\\s+([^;]*)[;\\n\\r]");
            matcher = pattern.matcher(stty);
            if (!matcher.find()) {
                // try a second try of regex
                pattern = Pattern.compile("(\\S*)\\s+" + name);
                matcher = pattern.matcher(stty);
                if (!matcher.find()) {
                    return -1;
                }
            }
        }
        return parseControlChar(matcher.group(1));
    }

    private static int parseControlChar(String str) {
        // under
        if ("<undef>".equals(str)) {
            return -1;
        }
        // octal
        if (str.charAt(0) == '0') {
            return Integer.parseInt(str, 8);
        }
        // decimal
        if (str.charAt(0) >= '1' && str.charAt(0) <= '9') {
            return Integer.parseInt(str, 10);
        }
        // control char
        if (str.charAt(0) == '^') {
            if (str.charAt(1) == '?') {
                return 127;
            } else {
                return str.charAt(1) - 64;
            }
        } else if (str.charAt(0) == 'M' && str.charAt(1) == '-') {
            if (str.charAt(2) == '^') {
                if (str.charAt(3) == '?') {
                    return 127 + 128;
                } else {
                    return str.charAt(3) - 64 + 128;
                }
            } else {
                return str.charAt(2) + 128;
            }
        } else {
            return str.charAt(0);
        }
    }

    private String stty(final String args) throws IOException, InterruptedException {
        checkNotNull(args);
        return exec(String.format("%s %s < /dev/tty", sttyCommand, args));
    }

    private String exec(final String cmd) throws IOException, InterruptedException {
        checkNotNull(cmd);
        return exec(shCommand, "-c", cmd);
    }

    private String exec(final String... cmd) throws IOException, InterruptedException {
        checkNotNull(cmd);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        Process p = Runtime.getRuntime().exec(cmd);

        InputStream in = null;
        InputStream err = null;
        OutputStream out = null;
        try {
            int c;
            in = p.getInputStream();
            while ((c = in.read()) != -1) {
                bout.write(c);
            }
            err = p.getErrorStream();
            while ((c = err.read()) != -1) {
                bout.write(c);
            }
            out = p.getOutputStream();
            p.waitFor();
        }
        finally {
            close(in, out, err);
        }

        String result = bout.toString();


        return result;
    }

    private static void close(final Closeable... closeables) {
        for (Closeable c : closeables) {
            try {
                c.close();
            }
            catch (Exception e) {
                // Ignore
            }
        }
    }

    public static <T> T checkNotNull(final T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}