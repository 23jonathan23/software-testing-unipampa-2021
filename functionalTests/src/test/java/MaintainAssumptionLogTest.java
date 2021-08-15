import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MaintainAssumptionLogTest {
    private static String _email;
    private static String _password;
    private static String _descriptionLog;
    private static List<String> _propsList = new ArrayList<String>();
    private final String _uriLogin = "http://lesse.com.br/tools/pmst_rp2/";
    private final int _timeOutInSeconds = 10;
    private static WebDriver _driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "resources/windows/chromedriver.exe");
        _driver = new ChromeDriver();
        _propsList.add("descriptionLog");

        var config = Configuration.getConfiguration("MaintainAssumptionLog/inputLogin", Arrays.asList("email", "password"));
        _email = config.getProperty("email");
        _password = config.getProperty("password");
    }

    @AfterClass
    public static void teardown() {
        _driver.quit();
    }

    @Test
    public void When_to_try_create_a_assumption_with_description_log_field_using_4000_characters_then_should_to_limit_the_field_on_2000_characters_and_save_the_assumption() {
        //Arrange
        var config = Configuration.getConfiguration("MaintainAssumptionLog/inputsTest1", _propsList);
        _descriptionLog = config.getProperty("descriptionLog");

        var expectedMessage = "Assumption Log has been successfully created!";

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToAssumptionLog(_driver);

        //Act
        createAssumptionLogWithoutToSave(_driver, _descriptionLog, "assumption");
        
        var actualValueDescriptionLog = getFieldsValues(_driver).get("descriptionLog");
        
        saveAssumptionLog(_driver);
        
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(_descriptionLog, actualValueDescriptionLog);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_try_create_a_constraint_with_description_log_field_using_4000_characters_then_should_to_limit_the_field_on_2000_characters_and_save_the_constraint() {
        //Arrange
        var config = Configuration.getConfiguration("MaintainAssumptionLog/inputsTest2", _propsList);
        _descriptionLog = config.getProperty("descriptionLog");

        var expectedMessage = "Assumption Log has been successfully created!";

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToAssumptionLog(_driver);

        //Act
        createAssumptionLogWithoutToSave(_driver, _descriptionLog, "");
        
        var actualValueDescriptionLog = getFieldsValues(_driver).get("descriptionLog");
        
        saveAssumptionLog(_driver);
        
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(_descriptionLog, actualValueDescriptionLog);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_try_create_a_assumption_with_description_log_field_using_HTML_then_should_not_to_save_the_assumption() {
        //Arrange
        var config = Configuration.getConfiguration("MaintainAssumptionLog/inputsTest3", _propsList);
        _descriptionLog = config.getProperty("descriptionLog");

        var notExpectedMessage = "Assumption Log has been successfully created!";

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToAssumptionLog(_driver);

        //Act
        createAssumptionLogWithoutToSave(_driver, _descriptionLog, "assumption");
        
        saveAssumptionLog(_driver);

        acceptAlert(_driver);
        
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(notExpectedMessage, actualMessage);
    }

    @Test
    public void When_to_try_create_a_constraint_with_description_log_field_using_HTML_characters_then_should_not_to_save_the_constraint() {
        //Arrange
        var config = Configuration.getConfiguration("MaintainAssumptionLog/inputsTest4", _propsList);
        _descriptionLog = config.getProperty("descriptionLog");

        var notExpectedMessage = "Assumption Log has been successfully created!";

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToAssumptionLog(_driver);

        //Act
        createAssumptionLogWithoutToSave(_driver, _descriptionLog, "");
        
        saveAssumptionLog(_driver);

        acceptAlert(_driver);
        
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(notExpectedMessage, actualMessage);
    }

    @Test
    public void When_to_try_create_a_assumption_with_description_log_field_empty_then_should_not_to_save_the_assumption_and_show_message_validation() {
        //Arrange
        var expectedMessage = "Preencha este campo.";

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToAssumptionLog(_driver);

        //Act
        createAssumptionLogWithoutToSave(_driver, "", "assumption");
        
        saveAssumptionLog(_driver);
        
        var actualMessageError = getMessageError(_driver);

        //Assert
        assertEquals(expectedMessage, actualMessageError);
    }

    @Test
    public void When_to_try_create_a_constraint_with_description_log_field_empty_characters_then_should_not_to_save_the_constraint_and_show_message_validation() {
        //Arrange
        var expectedMessage = "Preencha este campo.";

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToAssumptionLog(_driver);

        //Act
        createAssumptionLogWithoutToSave(_driver, "", "");
        
        saveAssumptionLog(_driver);

        var actualMessageError = getMessageError(_driver);

        //Assert
        assertEquals(expectedMessage, actualMessageError);
    }

    @Test
    public void When_to_try_create_a_assumption_then_the_description_log_field_should_have_a_tooltip_example() {
        //Arrange
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToAssumptionLog(_driver);

        createAssumptionLogWithoutToSave(_driver, "", "assumption");

        var expectedExample = "Description Log Example";

        //Act, Assert
        assertTrue(existsTooltip(_driver, expectedExample));
    }

    @Test
    public void When_to_try_create_a_constraint_then_the_description_log_field_should_have_a_tooltip_example() {
        //Arrange
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToAssumptionLog(_driver);

        createAssumptionLogWithoutToSave(_driver, "", "");

        var expectedExample = "Description Log Example";

        //Act, Assert
        assertTrue(existsTooltip(_driver, expectedExample));
    }

    @Test
    public void When_to_create_a_assumption_with_spaces_in_description_then_it_must_not_change_URL() {
        //Arrange
        var config = Configuration.getConfiguration("MaintainAssumptionLog/inputsTest5", _propsList);
        String desc = config.getProperty("descriptionLog");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        String currentURL;

        //Act
        navigateToAssumptionLog(_driver);
        createAssumptionLogWithoutToSave(_driver, desc, "assumption");
        currentURL = _driver.getCurrentUrl();
        saveAssumptionLog(_driver);

        //Assert
        assertNotEquals(currentURL, _driver.getCurrentUrl());
    }

    @Test
    public void When_to_create_a_constraints_with_spaces_in_description_then_it_must_not_change_URL() {
        //Arrange
        var config = Configuration.getConfiguration("MaintainAssumptionLog/inputsTest5", _propsList);
        String desc = config.getProperty("descriptionLog");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        String currentURL;

        //Act
        navigateToAssumptionLog(_driver);
        createAssumptionLogWithoutToSave(_driver, desc, "");
        currentURL = _driver.getCurrentUrl();
        saveAssumptionLog(_driver);

        //Assert
        assertNotEquals(currentURL, _driver.getCurrentUrl());
    }

    @Test
    public void When_to_create_a_assumption_with_correct_input_in_description_then_it_must_change_URL() {
        //Arrange
        var config = Configuration.getConfiguration("MaintainAssumptionLog/inputsTest6", _propsList);
        String desc = config.getProperty("descriptionLog");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        String expectedURL;

        //Act
        navigateToAssumptionLog(_driver);
        expectedURL = _driver.getCurrentUrl();
        createAssumptionLogWithoutToSave(_driver, desc, "assumption");
        saveAssumptionLog(_driver);

        //Assert
        assertEquals(expectedURL, _driver.getCurrentUrl());
    }

    @Test
    public void When_to_create_a_constraint_with_correct_input_in_description_then_it_must_change_URL() {
        //Arrange
        var config = Configuration.getConfiguration("MaintainAssumptionLog/inputsTest6", _propsList);
        String desc = config.getProperty("descriptionLog");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        String expectedURL;

        //Act
        navigateToAssumptionLog(_driver);
        expectedURL = _driver.getCurrentUrl();
        createAssumptionLogWithoutToSave(_driver, desc, "");
        saveAssumptionLog(_driver);

        //Assert
        assertEquals(expectedURL, _driver.getCurrentUrl());
    }

    @Test
    public void When_to_search_for_item_with_description_just_with_spaces_than_it_must_give_the_correct_finding() {
        //Arrange
        var config = Configuration.getConfiguration("MaintainAssumptionLog/inputsTest5", _propsList);
        String desc = config.getProperty("descriptionLog");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        //Act
        navigateToAssumptionLog(_driver);
        createAssumptionLogWithoutToSave(_driver, desc, "");
        saveAssumptionLog(_driver);

        config = Configuration.getConfiguration("MaintainAssumptionLog/inputsTest6", _propsList);
        desc = config.getProperty("descriptionLog");

        createAssumptionLogWithoutToSave(_driver, desc, "");
        saveAssumptionLog(_driver);
        searchForSomething(_driver, desc);

        //Assert
        assertEquals(desc, getFindings(_driver));
    }

    @Test
    public void When_to_search_for_item_than_it_must_give_the_correct_finding() {
        //Arrange
        var config = Configuration.getConfiguration("MaintainAssumptionLog/inputsTest6", _propsList);
        String desc = config.getProperty("descriptionLog");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        //Act
        navigateToAssumptionLog(_driver);
        createAssumptionLogWithoutToSave(_driver, desc, "");
        saveAssumptionLog(_driver);

        searchForSomething(_driver, desc);

        //Assert
        assertEquals(desc, getFindings(_driver));
    }

    private void searchForSomething(WebDriver driver, String entry) {
        WebElement inputEmailElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div/div/div/div[2]/div/div/div[1]/div[2]/div/label/input")));
        inputEmailElementById.sendKeys(entry);
    }

    private String getFindings(WebDriver driver) {
        WebElement inputEmailElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.className("odd")));
            inputEmailElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.className("texttd")));
        String desc = inputEmailElementById.getText();
        return desc;
    }

    private void createAssumptionLogWithoutToSave(WebDriver driver,String descriptionLog, String option) { 
        
        List<WebElement> buttonsByClassName = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("btn-info")));

        WebElement buttonCreateAssumption = null;
        WebElement buttonCreateConstraint = null;

        for(var button : buttonsByClassName) {
            if(button.getText().equals("New Assumption")) {
                buttonCreateAssumption = button;
            } else if(button.getText().equals("New Constraint")) {
                buttonCreateConstraint = button;
            }
        }

        if(option != null && option.equals("assumption")) {
            buttonCreateAssumption.click();
        } else {
            buttonCreateConstraint.click();
        }

        WebElement descriptionLogElementByName = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("description_log")));

        descriptionLogElementByName.sendKeys(descriptionLog);
    }

    private void saveAssumptionLog(WebDriver driver) {
        List<WebElement> buttonsElementByClass = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("btn-success")));

        for(var button : buttonsElementByClass) {
           var content = button.getText();
           if(content != null && content.contains("Save")) {
               button.click();
           }
        }
    }

    private String getMessageSuccess(WebDriver driver) {
        WebElement messageElementByClass = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.className("alert-success")));
        
        var contentMenssage = (messageElementByClass.findElement(By.tagName("strong"))).getText();
        return contentMenssage;
    }

    private String getMessageError(WebDriver driver) {
        try {
            String errorMessage = driver.findElement(By.name("description_log")).getAttribute("validationMessage");

            return errorMessage;
        } catch (Exception ex) { return null; }
    }

    private boolean existsTooltip(WebDriver driver, String title) {
        try {
            List<WebElement> elementsByClass = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("btn-default")));
            
            for(var element : elementsByClass) {
                var atribute = element.getAttribute("data-toggle");
                var dataTitle = element.getAttribute("data-original-title");
                if(atribute != null && atribute.contains("tooltip") && dataTitle.contains(title)) {
                    return true;
                }
            }
    
            return false;
        } catch(Exception e) {
            return false;
        }
    }

    private void acceptAlert(WebDriver driver) {
        try {
            Alert alert = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.alertIsPresent());
            if(alert != null) {
                alert.accept();
            }
        }catch(Exception er) { return; }
    }

    private Map<String, String> getFieldsValues(WebDriver driver) {
        Map<String, String> dictonary = new HashMap<String, String>();
        
        WebElement descriptionLogElementByName = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("description_log")));

        dictonary.put("descriptionLog", descriptionLogElementByName.getAttribute("value"));

        return dictonary;
    }

    private void navigateToAssumptionLog(WebDriver driver) {
        driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/project/210");
        driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/integration/assumption-log/list/210");
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
}
