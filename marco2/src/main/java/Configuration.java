import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class Configuration {
  private static final String COMMA_DELIMITER = ",";
  
  public static Properties getConfiguration() {
    try {
      BufferedReader br = new BufferedReader(new FileReader("config/credentials.csv"));
      var credentials = br.readLine().split(COMMA_DELIMITER);
  
      Properties props = new Properties();
      props.setProperty("email", credentials[0]);
      props.setProperty("password", credentials[1]);

      return props;
    } catch(Exception err) {
      return null;
    }
  }
}
