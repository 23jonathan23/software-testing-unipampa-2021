import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddMemberTest {
    
    private String _email;
    private String _password;    
    private String _emailNewMember;    
    private static List<String> _propsList = new ArrayList<String>(); 
    private final String _uriLogin = "http://lesse.com.br/tools/pmst_rp2/";
    private final String _pathAddMember = "/html/body/div[1]/div/section/div/div/div/div[2]/table/tbody/tr/td[3]/a[3]";
    private final String _pathInputEmail = "/html/body/div[1]/div/section/div/div/div/form/div[1]/div/input";
    private final int _timeOutInSeconds = 10;
    private static WebDriver _driver;
    
    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "resources/windows/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        _driver = new ChromeDriver(options);
        _propsList.add("email");
        _propsList.add("password");
        _propsList.add("emailNewMember");
    }

    @AfterClass
    public static void teardown() {
        _driver.quit();
    }

    @Test
    public void test1(){
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-29", _propsList);
        
        _email = config.getProperty("email");
        _password = config.getProperty("password");
        _emailNewMember = config.getProperty("emailNewMember");
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        
        WebElement submitLoginElementById = (new WebDriverWait(_driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(_pathAddMember)));
        submitLoginElementById.click();
      
        //Act
        addMember(_driver, _emailNewMember);
        String notExpected = "User update!";
        
        //Assert
        assertNotEquals(notExpected, msgReturn(_driver));
    }
    
    private void signin(WebDriver driver, String email, String password) { 
        WebElement inputEmailElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        inputEmailElementById.sendKeys(email);

        WebElement inputPasswordElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        inputPasswordElementById.sendKeys(password);

        WebElement submitLoginElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.id("login-submit")));
        submitLoginElementById.click();
    }
    
    public void addMember(WebDriver driver, String emailNewMember){
        WebElement inputEmailElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(_pathInputEmail)));
        inputEmailElementById.sendKeys(emailNewMember);        
        
        WebElement submitLoginElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("add_researcher_submit")));
        submitLoginElementById.click();
    }
    
    private String getMessageError(WebDriver driver) {
        String errorMessage = driver.findElement(By.id("title")).getAttribute("validationMessage");

        return errorMessage;
    }
    
    private String msgReturn(WebDriver driver) {
        String mensagem = null;
        
        try {
            WebDriverWait wait = new WebDriverWait(driver, _timeOutInSeconds);
            mensagem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div[1]/strong"))).getText();
        } catch (Exception e) {
        }

        return mensagem;
    }
}
