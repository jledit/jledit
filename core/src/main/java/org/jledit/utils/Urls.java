package org.jledit.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public final class Urls {

    private Urls() {
        //Utility Class
    }

    public static URL create(final String input) {
        if (input == null) {
            return null;
        }
        try {
            return new URL(input);
        } catch (MalformedURLException e) {
            return create(new File(input));
        }
    }

    public static URL create(final File file) {
        try {
            return file != null ? file.toURI().toURL() : null;
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }
}
