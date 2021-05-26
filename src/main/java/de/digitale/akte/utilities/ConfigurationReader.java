package de.digitale.akte.utilities;

import java.io.FileInputStream;
import java.util.Properties;

/** reads the properties file configuration.properties */
public class ConfigurationReader {

  private static Properties properties;

  static {
    try {
      String path = "src/main/resources/configuration.properties";
      FileInputStream input = new FileInputStream(path);
      properties = new Properties();
      properties.load(input);

      input.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String getProperty(String keyName) {
    return properties.getProperty(keyName);
  }
}
