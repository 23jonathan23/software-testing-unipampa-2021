import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterUserTest {
    private String _name;
    private String _email;
    private String _password;    
    private String _institution;    
    private final String _uriLogin = "http://lesse.com.br/tools/pmst_rp2/";
    private final String _uriRegister = "http://lesse.com.br/tools/pmst_rp2/authentication/register/";
    private final int _timeOutInSeconds = 10;
    private static WebDriver _driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "resources/windows/chromedriver.exe");
        _driver = new ChromeDriver();
    }

    @AfterClass
    public static void teardown() {
        _driver.quit();
    }

    @Test
    public void When_you_navigate_to_the_silver_bullet_website_and_correctly_register_a_new_user_then_must_be_show_login_page_and_success_message() {
        //Arrange
        var config = Configuration.getConfigurationRegisterUser("RP2G6-4");

        _name = config.getProperty("name");
        _email = config.getProperty("email");
        _institution = config.getProperty("institution");
        _password = config.getProperty("password");
        
        _driver.navigate().to(_uriRegister);
       
        //Act
        register(_driver, _name, _email, _institution, _password);

        var expectedUrl = _uriLogin;
        var actualUrl = getCurrentUrl(_driver);
        var expectedMsg = "Account Created!";
        var actualMsg = msgReturn(_driver);

        //Assert
        assertEquals(expectedUrl, actualUrl);
        assertEquals(expectedMsg, actualMsg);
    }
    
    @Test
    public void When_you_navigate_to_the_silver_bullet_website_and_register_a_new_user_with_an_empty_name_field_then_must_be_to_stay_on_the_current_page() {
        //Arrange
        var config = Configuration.getConfigurationRegisterUser("RP2G6-5");

        _name = config.getProperty("name");
        _email = config.getProperty("email");
        _institution = config.getProperty("institution");
        _password = config.getProperty("password");
        
        _driver.navigate().to(_uriRegister);
       
        //Act
        register(_driver, _name, _email, _institution, _password);

        var expectedUrl = _uriRegister;
        var actualUrl = getCurrentUrl(_driver);

        //Assert
        assertEquals(expectedUrl, actualUrl);
    }
    
    @Test
    public void When_register_a_new_user_with_empty_email_field_then_the_user_must_stay_on_the_current_page() {
        //Arrange
        var config = Configuration.getConfigurationRegisterUser("RP2G6-6");

        _name = config.getProperty("name");
        _email = config.getProperty("email");
        _institution = config.getProperty("institution");
        _password = config.getProperty("password");
        
        _driver.navigate().to(_uriRegister);
       
        //Act
        register(_driver, _name, _email, _institution, _password);

        var expectedUrl = _uriRegister;
        var actualUrl = getCurrentUrl(_driver);

        //Assert
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void When_register_a_new_user_with_empty_password_field_then_the_user_must_stay_on_the_current_page() {
        //Arrange
        var config = Configuration.getConfigurationRegisterUser("RP2G6-7");

        _name = config.getProperty("name");
        _email = config.getProperty("email");
        _institution = config.getProperty("institution");
        _password = config.getProperty("password");
        
        _driver.navigate().to(_uriRegister);
       
        //Act
        register(_driver, _name, _email, _institution, _password);

        var expectedUrl = _uriRegister;
        var actualUrl = getCurrentUrl(_driver);

        //Assert
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void When_register_a_new_user_with_many_characters_in_the_name_field_then_the_user_must_stay_on_the_current_page() {
        //Arrange
        var config = Configuration.getConfigurationRegisterUser("RP2G6-8");

        _name = config.getProperty("name");
        _email = config.getProperty("email");
        _institution = config.getProperty("institution");
        _password = config.getProperty("password");
        
        _driver.navigate().to(_uriRegister);
       
        //Act
        register(_driver, _name, _email, _institution, _password);

        var expectedUrl = _uriRegister;
        var actualUrl = getCurrentUrl(_driver);

        //Assert
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void When_register_a_new_user_with_many_characters_in_the_password_field_then_the_user_must_stay_on_the_current_page() {
        //Arrange
        var config = Configuration.getConfigurationRegisterUser("RP2G6-9");

        _name = config.getProperty("name");
        _email = config.getProperty("email");
        _institution = config.getProperty("institution");
        _password = config.getProperty("password");
        
        _driver.navigate().to(_uriRegister);
       
        //Act
        register(_driver, _name, _email, _institution, _password);

        var expectedUrl = _uriRegister;
        var actualUrl = getCurrentUrl(_driver);

        //Assert
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void When_register_a_new_user_with_an_invalid_email_then_the_user_must_stay_on_the_current_page() {
        //Arrange
        var config = Configuration.getConfigurationRegisterUser("RP2G6-10");

        _name = config.getProperty("name");
        _email = config.getProperty("email");
        _institution = config.getProperty("institution");
        _password = config.getProperty("password");
        
        _driver.navigate().to(_uriRegister);
       
        //Act
        register(_driver, _name, _email, _institution, _password);

        var expectedUrl = _uriRegister;
        var actualUrl = getCurrentUrl(_driver);

        //Assert
        assertEquals(expectedUrl, actualUrl);
    }

    private void register(WebDriver driver, String name, String email, String institution, String password) {
        WebElement inputNameElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
        inputNameElementById.sendKeys(name);

        WebElement inputEmailElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        inputEmailElementById.sendKeys(email);

        WebElement inputInstitutionElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("institution")));
        inputInstitutionElementById.sendKeys(institution);

        WebElement inputPasswordElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        inputPasswordElementById.sendKeys(password);

        WebElement submitLoginElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("login-submit")));
        submitLoginElementById.click();
    }
    
    private String getCurrentUrl(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, _timeOutInSeconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("main-sidebar")));
        } catch (Exception e) {
        }

        return _driver.getCurrentUrl();
    }
    
    private String msgReturn(WebDriver driver) {
        String mensagem = null;
        
        try {
            WebDriverWait wait = new WebDriverWait(driver, _timeOutInSeconds);
            mensagem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div/form/div[4]/strong[3]/p"))).getText();
        } catch (Exception e) {
        }

        return mensagem;
    }
}
