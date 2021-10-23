package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public Properties readPropertiesFromFile (String filePath) throws IOException {
        Properties props = new Properties();
        props.load(getClass().getClassLoader().getResourceAsStream(filePath));
        return props;
    }
}
