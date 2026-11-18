package com.testtask.ui.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public final class PropertyUtils {

    private PropertyUtils() {
    }
    
    public static Properties loadPagePaths() {
        Properties properties = new Properties();
        try (InputStream input = PropertyUtils.class.getClassLoader().getResourceAsStream("pages.properties")) {
            if (input == null) {
                throw new IllegalStateException("pages.properties not found");
            }
            properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load pages.properties", e);
        }
        return properties;
    }
}
