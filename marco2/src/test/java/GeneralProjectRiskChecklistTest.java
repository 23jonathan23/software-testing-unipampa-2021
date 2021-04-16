import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
        Alert alert = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.alertIsPresent());
        if(alert != null) {
            alert.accept();
        }
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
