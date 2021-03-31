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

public class LoginUserTest {
    private final String _email = "grupo6@unipampa.edu.br";
    private final String _password = "87654321";
    private final String _uriLogin = "http://lesse.com.br/tools/pmst_rp2/";
    private final String _uriProjects = "http://lesse.com.br/tools/pmst_rp2/projects";
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
    public void When_to_navigate_to_silver_bullet_website_and_to_input_correctly_email_and_password_then_must_be_show_projects_page() {
        //Arrange
        var expectedUrl = _uriProjects;
        
        _driver.navigate().to(_uriLogin);
        
        //Act
        signin(_driver, _email, _password);
        
        var actualUrl = getCurrentUrl(_driver);

        //Assert
        assertEquals(expectedUrl, actualUrl);
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

    private String getCurrentUrl(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, _timeOutInSeconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("main-sidebar")));
        }catch(Exception e) { }

        return _driver.getCurrentUrl();
    }
}
