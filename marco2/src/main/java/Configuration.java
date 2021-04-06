import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Configuration {
  private static final String COMMA_DELIMITER = ",";
  
  public static Properties getConfiguration(File file) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      var credentials = br.readLine().split(COMMA_DELIMITER);
  
      Properties props = new Properties();
      props.setProperty("email", credentials[0]);
      props.setProperty("password", credentials[1]);

      br.close();

      return props;
    } catch(Exception err) {
      return null;
    }
  }
   
  public static Properties getConfigurationRegisterUser(String testCase) {
    try {
        BufferedReader br = new BufferedReader(new FileReader("config/RegisterUser/" + testCase + ".csv"));
        var credentials = br.readLine().split(COMMA_DELIMITER);

        Properties props = new Properties();
        props.setProperty("name", credentials[0]);
        props.setProperty("email", credentials[1]);
        props.setProperty("institution", credentials[2]);
        props.setProperty("password", credentials[3]);

        br.close();

        return props;
    } catch (Exception err) {
        return null;
    }
  }
}
