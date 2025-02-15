package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();

    static {
        try {
            try (FileInputStream input = new FileInputStream("src/test/resources/appconfig/config.properties")) {
                props.load(input);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }


}
