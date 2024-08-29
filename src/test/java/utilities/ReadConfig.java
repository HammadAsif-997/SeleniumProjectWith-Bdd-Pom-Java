package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

//    ResourceBundle routes;
//
//
//    public ReadConfig(){
//        routes = ResourceBundle.getBundle("config");
//    }

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("Configrations/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getSauceDemoURL(){
        return properties.getProperty("sauceDemoBaseUrl");
    }

    public static String getAutomationTestStoreUrl(){
        return properties.getProperty("automationteststore");
    }



    public static String getChromePath(){
        return properties.getProperty("chromePath");
    }

    public static String getFirefoxPath(){
        return properties.getProperty("firefoxPath");
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }
}
