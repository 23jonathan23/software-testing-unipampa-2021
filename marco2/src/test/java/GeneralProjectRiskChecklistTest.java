import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

public class GeneralProjectRiskChecklistTest {
    private static String _email;
    private static String _password;
    private static String _aspects;
    private static String _weight;
    private static String _level;
    private static String _comments;
    private static List<String> _propsList = new ArrayList<String>();
    private final String _uriLogin = "http://lesse.com.br/tools/pmst_rp2/";
    private final String _uriProject1 = "http://lesse.com.br/tools/pmst_rp2/project/96";
    private final String _uriProject2 = "http://lesse.com.br/tools/pmst_rp2/project/97";
    private final int _timeOutInSeconds = 10;
    private static WebDriver _driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "resources/windows/chromedriver.exe");
        _driver = new ChromeDriver();
        _propsList.add("aspects");
        _propsList.add("weight");
        _propsList.add("level");
        _propsList.add("comments");

        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputLogin", Arrays.asList("email", "password"));
        _email = config.getProperty("email");
        _password = config.getProperty("password");
    }

    @AfterClass
    public static void teardown() {
        _driver.quit();
    }

    @Test
    public void When_to_create_a_new_topic_with_correctly_datas_then_should_to_save_the_checklist() {
        //Arrange
        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputsTest1", _propsList);
        _aspects = config.getProperty("aspects");
        _weight = config.getProperty("weight");
        _level = config.getProperty("level");
        _comments = config.getProperty("comments");
        
        var expectedMessage = "General Project Risk Checklist has been successfully changed!";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject2);
        
        //Act
        createTopic(_driver, _aspects, _weight, _level, _comments);
        saveChecklist(_driver);
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_create_a_new_topic_and_the_percentage_of_the_score_has_already_been_calculated_then_should_the_total_score_to_show_sum_percentage_correctly() {
        //Arrange
        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputsTest2", _propsList);
        _aspects = config.getProperty("aspects");
        _weight = config.getProperty("weight");
        _level = config.getProperty("level");
        _comments = config.getProperty("comments");
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject2);
        
        createTopic(_driver, _aspects, _weight, _level, _comments);

        var expectedScore = getScoreTopic(_driver);
        
        //Act
        var totalScore = getTotalScore(_driver);

        //Assert
        assertEquals(expectedScore, totalScore);
    }

    @Test
    public void When_to_create_a_new_topic_and_the_percentage_of_the_score_has_already_been_calculated_and_save_checklist_then_should_the_total_score_to_show_sum_percentage_correctly() {
        //Arrange
        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputsTest3", _propsList);
        _aspects = config.getProperty("aspects");
        _weight = config.getProperty("weight");
        _level = config.getProperty("level");
        _comments = config.getProperty("comments");
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject2);
        
        createTopic(_driver, _aspects, _weight, _level, _comments);

        var expectedScore = getScoreTopic(_driver);
        
        saveChecklist(_driver);
        
        //Act
        var totalScore = getTotalScore(_driver);

        //Assert
        assertEquals(expectedScore, totalScore);
    }

    @Test
    public void When_to_create_a_new_topic_and_with_aspects_field_only_1000_characters_then_should_limit_the_number_of_characters() {
        //Arrange
        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputsTest4", _propsList);
        _aspects = config.getProperty("aspects");
        _weight = config.getProperty("weight");
        _level = config.getProperty("level");
        _comments = config.getProperty("comments");
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject2);
        
        var expectedChars = _aspects.length();
        
        //Act
        createTopic(_driver, _aspects, _weight, _level, _comments);
        
        var totalChars = (getFieldsValues(_driver)).get("aspects").length();

        //Assert
        assertNotEquals(expectedChars, totalChars);
    }

    @Test
    public void When_to_create_a_new_topic_and_with_weight_field_only_1000_characters_then_should_not_accept_this_input() {
        //Arrange
        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputsTest5", _propsList);
        _aspects = config.getProperty("aspects");
        _weight = config.getProperty("weight");
        _level = config.getProperty("level");
        _comments = config.getProperty("comments");
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject2);
        
        var expectedValue = "";
        
        //Act
        createTopic(_driver, _aspects, _weight, _level, _comments);
        
        var atualValue = getFieldsValues(_driver).get("weight");

        //Assert
        assertEquals(expectedValue, atualValue);
    }

    @Test
    public void When_to_create_a_new_topic_and_with_level_field_only_1000_characters_then_should_not_accept_this_input() {
        //Arrange
        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputsTest6", _propsList);
        _aspects = config.getProperty("aspects");
        _weight = config.getProperty("weight");
        _level = config.getProperty("level");
        _comments = config.getProperty("comments");
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject2);
        
        var expectedValue = "";
        
        //Act
        createTopic(_driver, _aspects, _weight, _level, _comments);
        
        var atualValue = getFieldsValues(_driver).get("level");

        //Assert
        assertEquals(expectedValue, atualValue);
    }

    @Test
    public void When_to_create_a_new_topic_and_with_comments_field_only_1000_characters_then_should_limit_the_number_of_characters() {
        //Arrange
        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputsTest7", _propsList);
        _aspects = config.getProperty("aspects");
        _weight = config.getProperty("weight");
        _level = config.getProperty("level");
        _comments = config.getProperty("comments");
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject2);
        
        var expectedChars = _comments.length();
        
        //Act
        createTopic(_driver, _aspects, _weight, _level, _comments);
        
        var totalChars = (getFieldsValues(_driver)).get("comments").length();

        //Assert
        assertNotEquals(expectedChars, totalChars);
    }

    @Test
    public void When_to_create_a_new_topic_and_with_aspects_field_only_special_characters_then_should_not_to_save_the_checklist() {
        //Arrange
        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputsTest8", _propsList);
        _aspects = config.getProperty("aspects");
        _weight = config.getProperty("weight");
        _level = config.getProperty("level");
        _comments = config.getProperty("comments");

        var expectedValue = _aspects;

        var expectedMessage = "General Project Risk Checklist has been successfully changed!";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject2);
        
        //Act
        createTopic(_driver, _aspects, _weight, _level, _comments);
        
        var actualValue = getFieldsValues(_driver).get("aspects");
        
        saveChecklist(_driver);
        
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(expectedValue, actualValue);
        assertNotEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_delete_all_topics_of_checklist_and_to_save_then_should_have_to_create_standard_topics() {
        //Arrange
        var expectedAspectsValueOfFistTopic = "Os objetivos do projeto foram validados com o cliente?";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject1);
        
        //Act
        deleteTopics(_driver);
        
        saveChecklist(_driver);
        
        var actualAspectsValue = getFieldsValues(_driver).get("aspects");

        //Assert
        assertEquals(expectedAspectsValueOfFistTopic, actualAspectsValue);
    }

    @Test
    public void When_to_create_a_new_topic_with_comments_field_using_special_characters_only_then_should_not_to_save_the_checklist() {
        //Arrange
        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputsTest9", _propsList);
        _aspects = config.getProperty("aspects");
        _weight = config.getProperty("weight");
        _level = config.getProperty("level");
        _comments = config.getProperty("comments");

        var expectedValue = _aspects;

        var expectedMessage = "General Project Risk Checklist has been successfully changed!";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject2);
        
        //Act
        createTopic(_driver, _aspects, _weight, _level, _comments);
        
        var actualValue = getFieldsValues(_driver).get("aspects");
        
        saveChecklist(_driver);
        
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(expectedValue, actualValue);
        assertNotEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_create_a_new_topic_with_weight_field_using_special_characters_only_then_should_not_to_save_the_checklist() {
        //Arrange
        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputsTest10", _propsList);
        _aspects = config.getProperty("aspects");
        _weight = config.getProperty("weight");
        _level = config.getProperty("level");
        _comments = config.getProperty("comments");

        var expectedValue = _aspects;

        var expectedMessage = "General Project Risk Checklist has been successfully changed!";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject2);
        
        //Act
        createTopic(_driver, _aspects, _weight, _level, _comments);
        
        var actualValue = getFieldsValues(_driver).get("aspects");
        
        saveChecklist(_driver);
        
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(expectedValue, actualValue);
        assertNotEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_create_a_new_topic_with_level_field_using_special_characters_only_then_should_not_to_save_the_checklist() {
        //Arrange
        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputsTest11", _propsList);
        _aspects = config.getProperty("aspects");
        _weight = config.getProperty("weight");
        _level = config.getProperty("level");
        _comments = config.getProperty("comments");

        var expectedValue = _aspects;

        var expectedMessage = "General Project Risk Checklist has been successfully changed!";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject2);
        
        //Act
        createTopic(_driver, _aspects, _weight, _level, _comments);
        
        var actualValue = getFieldsValues(_driver).get("aspects");
        
        saveChecklist(_driver);
        
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(expectedValue, actualValue);
        assertNotEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_create_a_new_topic_with_aspects_field_using_html_then_should_not_to_save_the_checklist() {
        //Arrange
        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputsTest12", _propsList);
        _aspects = config.getProperty("aspects");
        _weight = config.getProperty("weight");
        _level = config.getProperty("level");
        _comments = config.getProperty("comments");

        var expectedValue = _aspects;

        var expectedMessage = "General Project Risk Checklist has been successfully changed!";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject2);
        
        //Act
        createTopic(_driver, _aspects, _weight, _level, _comments);
        
        var actualValue = getFieldsValues(_driver).get("aspects");
        
        saveChecklist(_driver);
        
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(expectedValue, actualValue);
        assertNotEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_create_a_new_topic_with_weight_field_using_html_then_should_not_to_save_the_checklist() {
        //Arrange
        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputsTest13", _propsList);
        _aspects = config.getProperty("aspects");
        _weight = config.getProperty("weight");
        _level = config.getProperty("level");
        _comments = config.getProperty("comments");

        var expectedValue = _aspects;

        var expectedMessage = "General Project Risk Checklist has been successfully changed!";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject2);
        
        //Act
        createTopic(_driver, _aspects, _weight, _level, _comments);
        
        var actualValue = getFieldsValues(_driver).get("aspects");
        
        saveChecklist(_driver);
        
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(expectedValue, actualValue);
        assertNotEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_create_a_new_topic_with_level_field_using_html_then_should_not_to_save_the_checklist() {
        //Arrange
        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputsTest14", _propsList);
        _aspects = config.getProperty("aspects");
        _weight = config.getProperty("weight");
        _level = config.getProperty("level");
        _comments = config.getProperty("comments");

        var expectedValue = _aspects;

        var expectedMessage = "General Project Risk Checklist has been successfully changed!";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject2);
        
        //Act
        createTopic(_driver, _aspects, _weight, _level, _comments);
        
        var actualValue = getFieldsValues(_driver).get("aspects");
        
        saveChecklist(_driver);
        
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(expectedValue, actualValue);
        assertNotEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_create_a_new_topic_with_comments_field_using_html_then_should_not_to_save_the_checklist() {
        //Arrange
        var config = Configuration.getConfiguration("GeneralProjectRiskChecklistTest/inputsTest15", _propsList);
        _aspects = config.getProperty("aspects");
        _weight = config.getProperty("weight");
        _level = config.getProperty("level");
        _comments = config.getProperty("comments");

        var expectedValue = _aspects;

        var expectedMessage = "General Project Risk Checklist has been successfully changed!";
        
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToChecklist(_driver, _uriProject2);
        
        //Act
        createTopic(_driver, _aspects, _weight, _level, _comments);
        
        var actualValue = getFieldsValues(_driver).get("aspects");
        
        saveChecklist(_driver);
        
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(expectedValue, actualValue);
        assertNotEquals(expectedMessage, actualMessage);
    }

    private void createTopic(WebDriver driver,String aspects, String weight, String level, String comments) {
        deleteTopics(driver);

        List<WebElement> buttonsElementByClass = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("btn-success")));
        
        for(var button : buttonsElementByClass) {
            var atribute = button.getAttribute("onclick");
            if(atribute != null && atribute.contains("education_fields()")) {
                button.click();
            }
        }

        WebElement aspectsElementByName = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.name("aspects[]")));
        aspectsElementByName.sendKeys(aspects);

        WebElement weightElementByName = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.name("weight[]")));
            weightElementByName.sendKeys(weight);

        WebElement levelElementByName = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.name("level[]")));
        
        levelElementByName.click();

        acceptAlert(driver);
        
        levelElementByName.sendKeys(level);

        WebElement commentsElementByName = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.name("comments[]")));

        commentsElementByName.click();

        acceptAlert(driver);

        commentsElementByName.sendKeys(comments);
    }

    private void saveChecklist(WebDriver driver) {
        List<WebElement> buttonsElementByClass = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("btn-success")));

        for(var button : buttonsElementByClass) {
           var content = button.getText();
           if(content != null && content.contains("Save")) {
               button.click();
           }
        }
    }

    private void deleteTopics(WebDriver driver) {
        List<WebElement> buttonsDeleteElementByClass = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("btn-danger")));

        for (WebElement button : buttonsDeleteElementByClass) {
            var atribute = button.getAttribute("id");
            if(atribute != null && atribute.contains("button[")) {
                button.click();
            }
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

    private void navigateToChecklist(WebDriver driver, String uri) {
        driver.navigate().to(uri);
        var chars =  uri.split("");
        var projetNumber =  chars[uri.length() -2] + chars[uri.length() -1];
        driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/risk/risk-checklist/edit/" + projetNumber);
    }

    private String getMessageSuccess(WebDriver driver) {
        WebElement messageElementByClass = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.className("alert-success")));
        
        var contentMenssage = (messageElementByClass.findElement(By.tagName("strong"))).getText();
        return contentMenssage;
    }

    private String getScoreTopic(WebDriver driver) {
        WebElement scoreElementByName = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.name("score[]")));

        return scoreElementByName.getAttribute("value");
    }

    private Map<String, String> getFieldsValues(WebDriver driver) {
        Map<String, String> dictonary = new HashMap<String, String>();
        
        WebElement aspectsElementByName;
        try {
            aspectsElementByName = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("aspects[]")));
        } catch (Exception er) {
            aspectsElementByName = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("aspects[1]")));
        }

        dictonary.put("aspects", aspectsElementByName.getAttribute("value"));

        WebElement weightElementByName = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.name("weight[]")));
           
        dictonary.put("weight", weightElementByName.getAttribute("value"));

        WebElement levelElementByName = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.name("level[]")));

        dictonary.put("level", levelElementByName.getAttribute("value"));

        WebElement commentsElementByName = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.name("comments[]")));

        dictonary.put("comments", commentsElementByName.getAttribute("value"));

        return dictonary;
    }

    private String getTotalScore(WebDriver driver) {
        WebElement scoreTotalElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.id("gprc_1")));
        
        var scoreTotalValue = scoreTotalElementById.getText()
            .replaceAll("Total Score: ", "")
            .replaceAll("%", "");
        
        return scoreTotalValue;
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
