import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Properties;

public class Configuration {
  private static final String COMMA_DELIMITER = ",";
   
  public static Properties getConfiguration(String path, List<String> propsMap) {
    try {
        BufferedReader br = new BufferedReader(new FileReader("config/" + path + ".csv"));
        var inputs = br.readLine().split(COMMA_DELIMITER);

        Properties props = new Properties();

        for(var index = 0; index < propsMap.size(); index++) {
          props.setProperty(propsMap.get(index), inputs[index]);
        }

        br.close();

        return props;
    } catch (Exception err) {
        return null;
    }
  }
}
