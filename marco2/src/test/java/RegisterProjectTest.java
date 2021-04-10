import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterProjectTest {
    private static String _email;
    private static String _password;
    private static String _title;
    private static String _description;
    private static String _objectives;
    private static List<String> _propsList = new ArrayList<String>();
    private final String _uriLogin = "http://lesse.com.br/tools/pmst_rp2/";
    private final int _timeOutInSeconds = 10;
    private static WebDriver _driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "resources/windows/chromedriver.exe");
        _driver = new ChromeDriver();
        _propsList.add("title");
        _propsList.add("description");
        _propsList.add("objectives");

        var config = Configuration.getConfiguration("LoginUser/credentials", Arrays.asList("email", "password"));
        _email = config.getProperty("email");
        _password = config.getProperty("password");
    }

    @AfterClass
    public static void teardown() {
        _driver.quit();
    }

    @Test
    public void When_to_try_create_a_new_project_with_all_fields_filled_in_correctly_then_should_create_a_project() {
        //Arrange
        var config = Configuration.getConfiguration("RegisterProject/inputsTest1", _propsList);
        _title = config.getProperty("title");
        _description = config.getProperty("description");
        _objectives = config.getProperty("objectives");
        
        var expectedUrl = "http://lesse.com.br/tools/pmst_rp2/project/show_projects";
        var expectedMessage = "Project " + _title + " has been successfully created!";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        
        //Act
        registerProject(_driver, _title, _description, _objectives);
        var actualUrl = getCurrentUrl(_driver);
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertEquals(expectedUrl, actualUrl);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_try_create_a_new_project_with_title_empty_then_should_not_create_a_project() {
        //Arrange
        var config = Configuration.getConfiguration("RegisterProject/inputsTest2", _propsList);
        _title = config.getProperty("title");
        _description = config.getProperty("description");
        _objectives = config.getProperty("objectives");
        
        var expectedMessage = "Preencha este campo.";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        
        //Act
        registerProject(_driver, _title, _description, _objectives);
        var actualMessage = getMessageError(_driver);

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_try_create_a_new_project_with_title_only_with_space_then_should_not_create_a_project() {
        //Arrange
        var config = Configuration.getConfiguration("RegisterProject/inputsTest3", _propsList);
        _title = config.getProperty("title");
        _description = config.getProperty("description");
        _objectives = config.getProperty("objectives");
        
        var expectedUrl = "http://lesse.com.br/tools/pmst_rp2/new/";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        
        //Act
        registerProject(_driver, _title, _description, _objectives);
        var actualUrl = getCurrentUrl(_driver);

        //Assert
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void When_to_try_create_a_new_project_with_title_only_1000_characters_then_should_not_create_a_project() {
        //Arrange
        var config = Configuration.getConfiguration("RegisterProject/inputsTest4", _propsList);
        _title = config.getProperty("title");
        _description = config.getProperty("description");
        _objectives = config.getProperty("objectives");
        
        var expectedUrl = "http://lesse.com.br/tools/pmst_rp2/new/";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        
        //Act
        registerProject(_driver, _title, _description, _objectives);
        var actualUrl = getCurrentUrl(_driver);

        //Assert
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void When_to_try_create_a_new_project_with_description_only_1000_characters_then_should_not_create_a_project() {
        //Arrange
        var config = Configuration.getConfiguration("RegisterProject/inputsTest5", _propsList);
        _title = config.getProperty("title");
        _description = config.getProperty("description");
        _objectives = config.getProperty("objectives");
        
        var expectedUrl = "http://lesse.com.br/tools/pmst_rp2/new/";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        
        //Act
        registerProject(_driver, _title, _description, _objectives);
        var actualUrl = getCurrentUrl(_driver);

        //Assert
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void When_to_try_create_a_new_project_with_objectives_only_1000_characters_then_should_not_create_a_project() {
        //Arrange
        var config = Configuration.getConfiguration("RegisterProject/inputsTest6", _propsList);
        _title = config.getProperty("title");
        _description = config.getProperty("description");
        _objectives = config.getProperty("objectives");
        
        var expectedUrl = "http://lesse.com.br/tools/pmst_rp2/new/";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        
        //Act
        registerProject(_driver, _title, _description, _objectives);
        var actualUrl = getCurrentUrl(_driver);

        //Assert
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void When_to_try_create_a_new_project_with_title_only_special_characters_then_should_not_create_a_project() {
        //Arrange
        var config = Configuration.getConfiguration("RegisterProject/inputsTest7", _propsList);
        _title = config.getProperty("title");
        _description = config.getProperty("description");
        _objectives = config.getProperty("objectives");
        
        var expectedUrl = "http://lesse.com.br/tools/pmst_rp2/new/";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        
        //Act
        registerProject(_driver, _title, _description, _objectives);
        var actualUrl = getCurrentUrl(_driver);

        //Assert
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void When_to_try_create_a_new_project_with_description_only_special_characters_then_should_not_create_a_project() {
        //Arrange
        var config = Configuration.getConfiguration("RegisterProject/inputsTest8", _propsList);
        _title = config.getProperty("title");
        _description = config.getProperty("description");
        _objectives = config.getProperty("objectives");
        
        var expectedUrl = "http://lesse.com.br/tools/pmst_rp2/new/";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        
        //Act
        registerProject(_driver, _title, _description, _objectives);
        var actualUrl = getCurrentUrl(_driver);

        //Assert
        assertEquals(expectedUrl, actualUrl);
    }

    private String getMessageSuccess(WebDriver driver) {
        WebElement messageElementByClass = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.className("alert-success")));
        
        var contentMenssage = (messageElementByClass.findElement(By.tagName("strong"))).getText();
        return contentMenssage;
    }

    private String getMessageError(WebDriver driver) {
        String errorMessage = driver.findElement(By.id("title")).getAttribute("validationMessage");

        return errorMessage;
    }

    private void registerProject(WebDriver driver, String title, String description, String objectives) { 
        _driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/new/");

        WebElement inputTitleElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.id("title")));
        inputTitleElementById.sendKeys(title);

        WebElement inputDescriptionElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.id("description")));
        inputDescriptionElementById.sendKeys(description);
        
        WebElement inputObjectivesElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.id("objectives")));
        inputObjectivesElementById.sendKeys(objectives);

        WebElement saveProjectElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.id("new_project-submit")));
        saveProjectElementById.click();
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
