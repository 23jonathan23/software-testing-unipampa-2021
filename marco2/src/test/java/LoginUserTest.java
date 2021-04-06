import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.concurrent.TimeUnit;

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
    private static String _email;
    private static String _password;
    private final String _uriLogin = "http://lesse.com.br/tools/pmst_rp2/";
    private final String _uriProjects = "http://lesse.com.br/tools/pmst_rp2/projects";
    private final String _expectedMessage = "the email or password is incorrect!";
    private final int _timeOutInSeconds = 10;
    private static WebDriver _driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "resources/windows/chromedriver.exe");
        _driver = new ChromeDriver();
        var config = Configuration.getConfiguration(new File("config/credentials.csv"));
        _email = config.getProperty("email");
        _password = config.getProperty("password");
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

    @Test
    public void When_navigating_to_silver_bullet_website_and_inputting_wrong_email_then_the_page_must_be_different_of_the_ptojects_page(){
        
        var nonExpectedUrl = _uriProjects;
        
        _driver.navigate().to(_uriLogin);
        _driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        var config = Configuration.getConfiguration(new File("config/credentials2.csv"));
        _email = config.getProperty("email");
        _password = config.getProperty("password");

        signin(_driver, _email, _password);

        var actualUrl = _driver.getCurrentUrl();

        assertNotEquals(nonExpectedUrl, actualUrl);
    }

    @Test
    public void When_navigating_to_silver_bullet_website_and_inputting_wrong_password_then_the_page_must_be_different_of_the_ptojects_page(){
        
        var nonExpectedUrl = _uriProjects;
        
        _driver.navigate().to(_uriLogin);
        _driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        var config = Configuration.getConfiguration(new File("config/credentials3.csv"));
        _email = config.getProperty("email");
        _password = config.getProperty("password");

        signin(_driver, _email, _password);

        var actualUrl = _driver.getCurrentUrl();

        assertNotEquals(nonExpectedUrl, actualUrl);
    }

    @Test
    public void When_navigating_to_silver_bullet_website_and_inputting_wrong_credencials_then_it_must_not_store_the_data(){
        _driver.navigate().to(_uriLogin);

        var config = Configuration.getConfiguration(new File("config/credentials4.csv"));
        _email = config.getProperty("email");
        _password = config.getProperty("password");

        signin(_driver, _email, _password);

        WebElement we = _driver.findElement(By.id("email"));
        String email = we.getAttribute("value");

        we = _driver.findElement(By.id("password"));
        String password = we.getAttribute("value");

        assertTrue(email.isEmpty() && password.isEmpty());
    }

    @Test
    public void When_navigating_to_silver_bullet_website_and_inputting_a_wrong_credencials_then_a_warning_message_must_be_shown(){
        _driver.navigate().to(_uriLogin);

        var config = Configuration.getConfiguration(new File("config/credentials5.csv"));
        _email = config.getProperty("email");
        _password = config.getProperty("password");

        signin(_driver, _email, _password);

        String mensagem = getText(_driver);
        System.out.println(mensagem);

        assertEquals(_expectedMessage, mensagem);
    }

    private String getText(WebDriver driver){
        WebElement inputEmailElementByXPath = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div/form/div[4]/strong[4]/p")));
        
            return inputEmailElementByXPath.getText();
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
