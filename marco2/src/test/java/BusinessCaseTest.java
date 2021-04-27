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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BusinessCaseTest {
    private static String _email;
    private static String _password;
    private static String _businessDeals;
    private static String _situationAnalysis;
    private static String _recommendation;
    private static String _evaluation;
    private static List<String> _propsList = new ArrayList<String>();
    private final String _uriLogin = "http://lesse.com.br/tools/pmst_rp2/";
    private final int _timeOutInSeconds = 10;
    private static WebDriver _driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "resources/windows/chromedriver.exe");
        _driver = new ChromeDriver();
        _propsList.add("businessDeals");
        _propsList.add("situationAnalysis");
        _propsList.add("recommendation");
        _propsList.add("evaluation");

        var config = Configuration.getConfiguration("BusinessCaseTest/inputLogin", Arrays.asList("email", "password"));
        _email = config.getProperty("email");
        _password = config.getProperty("password");
    }

    @AfterClass
    public static void teardown() {
        _driver.quit();
    }

    @Test
    public void When_to_try_create_a_business_case_then_the_business_deals_field_should_have_a_tooltip_example() {
        //Arrange
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        var expectedExample = "Business Deals Example";

        //Act, Assert
        assertTrue(existsTooltip(_driver, expectedExample));
    }

    @Test
    public void When_to_try_create_a_business_case_then_the_situation_analysis_field_should_have_a_tooltip_example() {
        //Arrange
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        var expectedExample = "Situation Analysis Example";

        //Act, Assert
        assertTrue(existsTooltip(_driver, expectedExample));
    }

    @Test
    public void When_to_try_create_a_business_case_then_the_recommendation_field_should_have_a_tooltip_example() {
        //Arrange
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        var expectedExample = "Recommendation Example";

        //Act, Assert
        assertTrue(existsTooltip(_driver, expectedExample));
    }

    @Test
    public void When_to_try_create_a_business_case_then_the_evaluation_field_should_have_a_tooltip_example() {
        //Arrange
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        var expectedExample = "Evaluation Example";

        //Act, Assert
        assertTrue(existsTooltip(_driver, expectedExample));
    }

    @Test
    public void When_to_try_create_a_business_case_with_business_deals_field_using_SQL_then_should_not_to_save_the_business_case() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest5", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        var expectedMessage = "Bussiness Case has been successfully changed!";

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_try_create_a_business_case_with_situation_analysis_field_using_SQL_then_should_not_to_save_the_business_case() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest6", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        var expectedMessage = "Bussiness Case has been successfully changed!";

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_try_create_a_business_case_with_recommendation_field_using_SQL_then_should_not_to_save_the_business_case() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest7", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        var expectedMessage = "Bussiness Case has been successfully changed!";

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_try_create_a_business_case_with_evaluation_field_using_SQL_then_should_not_to_save_the_business_case() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest8", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        var expectedMessage = "Bussiness Case has been successfully changed!";

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_try_create_a_business_case_with_business_deals_field_using_special_characters_then_should_not_to_save_the_business_case() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest9", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        var expectedMessage = "Bussiness Case has been successfully changed!";

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_try_create_a_business_case_with_situation_analysis_field_using_special_characters_then_should_not_to_save_the_business_case() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest10", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        var expectedMessage = "Bussiness Case has been successfully changed!";

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_try_create_a_business_case_with_recommendation_field_using_special_characters_then_should_not_to_save_the_business_case() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest11", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        var expectedMessage = "Bussiness Case has been successfully changed!";

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);
        var actualMessage = getMessageSuccess(_driver);

        //Assert
        assertNotEquals(expectedMessage, actualMessage);
    }

    @Test
    public void When_to_create_a_business_case_with_business_deals_field_with_10000_characteres_then_this_field_must_not_keep_this_many() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest12", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);
        
        String actualText = getFieldText(_driver, "business_deals");

        //Assert
        assertTrue(actualText.compareTo(_businessDeals) < 0);
    }

    @Test
    public void When_to_create_a_business_case_with_situation_analysis_field_with_10000_characteres_then_this_field_must_not_keep_this_many() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest13", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);
        
        String actualText = getFieldText(_driver, "situation_analysis");

        //Assert
        assertTrue(actualText.compareTo(_businessDeals) < 0);
    }

    @Test
    public void When_to_create_a_business_case_with_recommendation_field_with_10000_characteres_then_this_field_must_not_keep_this_many() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest14", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);
        
        String actualText = getFieldText(_driver, "recommendation");

        //Assert
        assertTrue(actualText.compareTo(_businessDeals) < 0);
    }

    @Test
    public void When_to_create_a_business_case_with_evaliation_field_with_10000_characteres_then_this_field_must_not_keep_this_many() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest15", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);
        
        String actualText = getFieldText(_driver, "evaluation");

        //Assert
        assertTrue(actualText.compareTo(_businessDeals) < 0);
    }

    @Test
    public void When_to_create_a_business_case_and_do_not_save_it_then_the_business_deals_field_must_keep_the_old_text() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest16", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);

        createBusinessCaseWithoutSaving(_driver, "", "", "", "");
        
        String actualText = getFieldText(_driver, "business_deals");

        //Assert
        assertTrue(actualText.equals(_businessDeals));
    }

    @Test
    public void When_to_create_a_business_case_and_do_not_save_it_then_the_situation_analysis_field_must_keep_the_old_text() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest16", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);

        createBusinessCaseWithoutSaving(_driver, "", "", "", "");
        
        String actualText = getFieldText(_driver, "situation_analysis");

        //Assert
        assertTrue(actualText.equals(_situationAnalysis));
    }

    @Test
    public void When_to_create_a_business_case_and_do_not_save_it_then_the_recommendation_field_must_keep_the_old_text() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest16", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);

        createBusinessCaseWithoutSaving(_driver, "", "", "", "");
        
        String actualText = getFieldText(_driver, "recommendation");

        //Assert
        assertTrue(actualText.equals(_recommendation));
    }

    @Test
    public void When_to_create_a_business_case_and_do_not_save_it_then_the_evaluation_field_must_keep_the_old_text() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest16", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);

        createBusinessCaseWithoutSaving(_driver, "", "", "", "");
        
        String actualText = getFieldText(_driver, "evaluation");

        //Assert
        assertTrue(actualText.equals(_evaluation));
    }

    @Test
    public void When_to_create_a_business_case_then_the_business_deals_field_must_be_saved() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest16", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);
        
        String actualText = getFieldText(_driver, "business_deals");

        //Assert
        assertEquals(actualText, _businessDeals);
    }

    @Test
    public void When_to_create_a_business_case_then_the_situatuion_analysis_field_must_be_saved() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest16", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);
        
        String actualText = getFieldText(_driver, "situation_analysis");

        //Assert
        assertEquals(actualText, _situationAnalysis);
    }

    @Test
    public void When_to_create_a_business_case_then_the_recommendation_field_must_be_saved() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest16", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);
        
        String actualText = getFieldText(_driver, "recommendation");

        //Assert
        assertEquals(actualText, _recommendation);
    }

    @Test
    public void When_to_create_a_business_case_then_the_evaluation_field_must_be_saved() {
        //Arrange
        var config = Configuration.getConfiguration("BusinessCaseTest/inputsTest16", _propsList);
        _businessDeals = config.getProperty("businessDeals");
        _situationAnalysis = config.getProperty("situationAnalysis");
        _recommendation = config.getProperty("recommendation");
        _evaluation = config.getProperty("evaluation");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);

        navigateToBusinessCase(_driver);

        //Act
        createBusinessCase(_driver, _businessDeals, _situationAnalysis, _recommendation, _evaluation);
        
        String actualText = getFieldText(_driver, "evaluation");

        //Assert
        assertEquals(actualText, _evaluation);
    }

    private void createBusinessCaseWithoutSaving(WebDriver driver,String businessDeals, String situationAnalysis, String recommendation, String evaluation) {
        WebElement businessDealElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("business_deals")));
        
        businessDealElementById.clear();
        businessDealElementById.sendKeys(businessDeals);

        WebElement situationAnalysisElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.id("situation_analysis")));
        
        situationAnalysisElementById.clear();
        situationAnalysisElementById.sendKeys(situationAnalysis);

        WebElement recommendationElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("recommendation")));

        recommendationElementById.clear();
        recommendationElementById.sendKeys(recommendation);

        WebElement evaluationElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("evaluation")));

        evaluationElementById.clear();
        evaluationElementById.sendKeys(evaluation);

        goBack(_driver);
    }

    private void goBack(WebDriver driver) {
        WebElement messageElementByClass = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div/div/div/form/div[5]/form/button")));
            messageElementByClass.click();
    }

    private void saveBusinessCase(WebDriver driver) {
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

    private String getFieldText(WebDriver driver, String field) {
        navigateToBusinessCase(_driver);

        WebElement businessDealElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id(field)));
        return businessDealElementById.getText();
    }

    private void createBusinessCase(WebDriver driver,String businessDeals, String situationAnalysis, String recommendation, String evaluation) {  
        WebElement businessDealElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("business_deals")));
        
        businessDealElementById.clear();
        businessDealElementById.sendKeys(businessDeals);

        WebElement situationAnalysisElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.id("situation_analysis")));
        
        situationAnalysisElementById.clear();
        situationAnalysisElementById.sendKeys(situationAnalysis);

        WebElement recommendationElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("recommendation")));

        recommendationElementById.clear();
        recommendationElementById.sendKeys(recommendation);

        WebElement evaluationElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("evaluation")));

        evaluationElementById.clear();
        evaluationElementById.sendKeys(evaluation);

        saveBusinessCase(driver);
    }

    private Map<String, String> getFieldsValues(WebDriver driver) {
        Map<String, String> dictonary = new HashMap<String, String>();
        
        WebElement businessDealElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("business_deals")));

        dictonary.put("businessDeals", businessDealElementById.getAttribute("value"));

        WebElement situationAnalysisElementById = (new WebDriverWait(driver, _timeOutInSeconds))
        .until(ExpectedConditions.presenceOfElementLocated(By.id("situation_analysis")));

        dictonary.put("situationAnalysis", situationAnalysisElementById.getAttribute("value"));

        WebElement recommendationElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("recommendation")));

        dictonary.put("recommendation", recommendationElementById.getAttribute("value"));

        WebElement evaluationElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("evaluation")));

        dictonary.put("evaluation", evaluationElementById.getAttribute("value"));

        return dictonary;
    }

    private void navigateToBusinessCase(WebDriver driver) {
        driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/project/198");
        driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/integration/business-case/edit/198");
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
