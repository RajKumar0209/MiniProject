

package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {

    private Properties prop;                                         // this utility class locates the path of config properties from that it takes data which i used in my testcases
                                
    public configReader() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(
            System.getProperty("user.dir") + "\\src\\test\\resources\\Config\\config.properties"
        );
        prop.load(fis);
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}

