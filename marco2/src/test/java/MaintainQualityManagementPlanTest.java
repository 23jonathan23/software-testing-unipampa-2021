import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MaintainQualityManagementPlanTest {
    
    private static String _email;
    private static String _password;
    private static String _qualityStandards;
    private static String _qualityObjectives;
    private static String _qualityRoles;
    private static String _projectDeliverables;
    private static String _qualityControl;
    private static String _qualityTools;
    private static String _majorProcedures;
    private static String _javascriptInjection;

    private final String _uriLogin = "http://lesse.com.br/tools/pmst_rp2/";
    private static WebDriver _driver;    
    private final int _timeOutInSeconds = 10;
    private static List<String> _propsList = new ArrayList<String>();
    
    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "resources/windows/chromedriver.exe");
         ChromeOptions options = new ChromeOptions();
         options.addArguments("--start-maximized");
        _driver = new ChromeDriver(options);        
        _propsList.add("qualityStandards");
        _propsList.add("qualityObjectives");
        _propsList.add("qualityRoles");
        _propsList.add("projectDeliverables");
        _propsList.add("qualityControl");
        _propsList.add("qualityTools");
        _propsList.add("majorProcedures");
        _propsList.add("javascriptInjection");

        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/inputLogin", Arrays.asList("email", "password"));
        _email = config.getProperty("email");
        _password = config.getProperty("password");
    }
    
    @AfterClass
    public static void teardown() {
        _driver.quit();
    }    
    
    @Test
    public void when_entering_a_value_in_the_quality_standards_field_the_quality_standards_field_must_have_the_same_value_as_the_entry(){
        //Arrange
        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/RP2G6-103", _propsList);
        _qualityStandards = config.getProperty("qualityStandards");
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);
        //Act        
        fillInput(_driver, "standards", _qualityStandards);
        var expected = _qualityStandards;
        _driver.navigate().refresh();
        var actual = getActualFieldValue(_driver, "standards");       
        //Assert       
        assertEquals(expected, actual);
    }
    
    @Test
    public void when_entering_a_value_in_the_quality_objectives_field_the_quality_objectives_field_must_have_the_same_value_as_the_entry(){
        //Arrange
        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/RP2G6-104", _propsList);
        _qualityObjectives = config.getProperty("qualityObjectives");
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);
        //Act        
        fillInput(_driver, "objectives", _qualityObjectives);
        var expected = _qualityObjectives;
        _driver.navigate().refresh();
        var actual = getActualFieldValue(_driver, "objectives");       
        //Assert       
        assertEquals(expected, actual);
    }

    @Test
    public void when_entering_a_value_in_the_quality_roles_field_the_quality_roles_field_must_have_the_same_value_as_the_entry(){
        //Arrange
        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/RP2G6-105", _propsList);
        _qualityRoles = config.getProperty("qualityRoles");
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);
        //Act        
        fillInput(_driver, "roles_responsibilities", _qualityRoles);
        var expected = _qualityRoles;
        _driver.navigate().refresh();
        var actual = getActualFieldValue(_driver, "roles_responsibilities");       
        //Assert       
        assertEquals(expected, actual);
    }

    @Test
    public void when_entering_a_value_in_the_project_deliverables_field_the_project_deliverables_field_must_have_the_same_value_as_the_entry(){
        //Arrange
        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/RP2G6-106", _propsList);
        _projectDeliverables = config.getProperty("projectDeliverables");
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);
        //Act        
        fillInput(_driver, "deliverables", _projectDeliverables);
        var expected = _projectDeliverables;
        _driver.navigate().refresh();
        var actual = getActualFieldValue(_driver, "deliverables");       
        //Assert       
        assertEquals(expected, actual);
    }
    
    @Test
    public void when_entering_a_value_in_the_quality_control_field_the_quality_control_field_must_have_the_same_value_as_the_entry(){
        //Arrange
        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/RP2G6-107", _propsList);
        _qualityControl = config.getProperty("qualityControl");
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);
        //Act        
        fillInput(_driver, "activities", _qualityControl);
        var expected = _qualityControl;
        _driver.navigate().refresh();
        var actual = getActualFieldValue(_driver, "activities");       
        //Assert       
        assertEquals(expected, actual);
    }

    @Test
    public void when_entering_a_value_in_the_quality_tools_field_the_quality_tools_field_must_have_the_same_value_as_the_entry(){
        //Arrange
        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/RP2G6-108", _propsList);
        _qualityTools = config.getProperty("qualityTools");
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);
        //Act        
        fillInput(_driver, "tools", _qualityTools);
        var expected = _qualityTools;
        _driver.navigate().refresh();
        var actual = getActualFieldValue(_driver, "tools");       
        //Assert       
        assertEquals(expected, actual);
    }
    
    @Test
    public void when_entering_a_value_in_the_major_procedures_field_the_major_procedures_field_must_have_the_same_value_as_the_entry(){
        //Arrange
        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/RP2G6-109", _propsList);
        _majorProcedures = config.getProperty("majorProcedures");
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);
        //Act        
        fillInput(_driver, "procedures", _majorProcedures);
        var expected = _majorProcedures;
        _driver.navigate().refresh();
        var actual = getActualFieldValue(_driver, "procedures");       
        //Assert       
        assertEquals(expected, actual);
    }  
    
    @Test
    public void When_hovering_the_mouse_over_the_quality_standards_tooltip__must_should_show_a_tip_for_filling(){
        //Arrange
        _driver.navigate().to(_uriLogin);    
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);
        //Act
        var notExpected = "";
        var messageTooltip = getTooltipValue(_driver, "/html/body/div[1]/div/section/div/div/div/form/div[1]/a");
        //Assert
        assertNotEquals(notExpected, messageTooltip);
    }
    
    @Test
    public void When_hovering_the_mouse_over_the_quality_objectives_tooltip_must_should_show_a_tip_for_filling(){
        //Arrange
        _driver.navigate().to(_uriLogin);    
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);
        //Act
        var notExpected = "";
        var messageTooltip = getTooltipValue(_driver, "/html/body/div[1]/div/section/div/div/div/form/div[2]/a");
        //Assert
        assertNotEquals(notExpected, messageTooltip);
    }
    
    @Test
    public void When_hovering_the_mouse_over_the_quality_roles_tooltip_must_should_show_a_tip_for_filling(){
        //Arrange
        _driver.navigate().to(_uriLogin);    
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);
        //Act
        var notExpected = "";
        var messageTooltip = getTooltipValue(_driver, "/html/body/div[1]/div/section/div/div/div/form/div[3]/a");
        //Assert
        assertNotEquals(notExpected, messageTooltip);
    }

    @Test
    public void When_hovering_the_mouse_over_the_project_deliverables_tooltip_must_should_show_a_tip_for_filling(){
        //Arrange
        _driver.navigate().to(_uriLogin);    
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);
        //Act
        var notExpected = "";
        var messageTooltip = getTooltipValue(_driver, "/html/body/div[1]/div/section/div/div/div/form/div[4]/a");
        //Assert
        assertNotEquals(notExpected, messageTooltip);
    }
    
    @Test
    public void When_hovering_the_mouse_over_the_quality_control_tooltip_must_should_show_a_tip_for_filling(){
        //Arrange
        _driver.navigate().to(_uriLogin);    
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);
        //Act
        var notExpected = "";
        var messageTooltip = getTooltipValue(_driver, "/html/body/div[1]/div/section/div/div/div/form/div[5]/a");
        //Assert
        assertNotEquals(notExpected, messageTooltip);
    }
    
    @Test
    public void When_hovering_the_mouse_over_the_quality_tools_tooltip_must_should_show_a_tip_for_filling(){
        //Arrange
        _driver.navigate().to(_uriLogin);    
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);
        //Act
        var notExpected = "";
        var messageTooltip = getTooltipValue(_driver, "/html/body/div[1]/div/section/div/div/div/form/div[6]/a");
        //Assert
        assertNotEquals(notExpected, messageTooltip);
    }

    @Test
    public void When_hovering_the_mouse_over_the_major_procedures_tooltip_must_should_show_a_tip_for_filling(){
        //Arrange
        _driver.navigate().to(_uriLogin);    
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);
        //Act
        var notExpected = "";
        var messageTooltip = getTooltipValue(_driver, "/html/body/div[1]/div/section/div/div/div/form/div[7]/a");
        //Assert
        assertNotEquals(notExpected, messageTooltip);
    }

    @Test
    public void When_an_invalid_file_must_should_show_a_failure_message(){
        //Arrange
        _driver.navigate().to(_uriLogin);    
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver); 
        var expected = "No images found.";
        //Act       
        uploadFile(_driver, ".", "config/MaintainQualityManagementPlan/inValidFile.txt");
        
        WebElement validationFile = (new WebDriverWait(_driver, _timeOutInSeconds))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div/div/div/form/div[8]/div[2]/div/div")));         
        var actual = validationFile.getText();
        //Assert
        assertEquals(expected, actual);      
    }

    @Test
    public void When_inserting_javascript_must_not_execute_the_javascript(){
        //Arrange
        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/RP2G6-118", _propsList);
        _javascriptInjection = config.getProperty("javascriptInjection");        
        _driver.navigate().to(_uriLogin);    
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver); 
        //Act      
        uploadFile(_driver, _javascriptInjection, "config/MaintainQualityManagementPlan/validFile.PNG");
        var isAlert = isAlertPresent(_driver);
        //Assert
        assertFalse(isAlert);       
    }

    @Test
    public void When_entering_10_million_chars_in_the_quality_standards_field_and_save_the_action_must_fail(){
        // Arrange
        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/RP2G6-131-a-137", _propsList);
        _qualityStandards = config.getProperty("qualityStandards");
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);

        // Act
        fillInput(_driver, "standards", _qualityStandards);
        var expected = _qualityStandards;
        _driver.navigate().refresh();
        var actual = getActualFieldValue(_driver, "standards");

        // Assert       
        assertNotEquals(expected, actual);
    }

    @Test
    public void When_entering_10_million_chars_in_the_quality_objectives_field_and_save_the_action_must_fail(){
        // Arrange
        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/RP2G6-131-a-137", _propsList);
        _qualityObjectives = config.getProperty("qualityStandards");
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);

        // Act
        fillInput(_driver, "objectives", _qualityObjectives);
        var expected = _qualityObjectives;
        _driver.navigate().refresh();
        var actual = getActualFieldValue(_driver, "objectives");

        // Assert       
        assertNotEquals(expected, actual);
    }

    @Test
    public void When_entering_10_million_chars_in_the_quality_roles_field_and_save_the_action_must_fail(){
        // Arrange
        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/RP2G6-131-a-137", _propsList);
        _qualityRoles = config.getProperty("qualityStandards");
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);

        // Act
        fillInput(_driver, "roles_responsibilities", _qualityRoles);
        var expected = _qualityRoles;
        _driver.navigate().refresh();
        var actual = getActualFieldValue(_driver, "roles_responsibilities");

        // Assert       
        assertNotEquals(expected, actual);
    }

    @Test
    public void When_entering_10_million_chars_in_the_project_deliverables_field_and_save_the_action_must_fail(){
        // Arrange
        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/RP2G6-131-a-137", _propsList);
        _projectDeliverables = config.getProperty("qualityStandards");
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);

        // Act
        fillInput(_driver, "deliverables", _projectDeliverables);
        var expected = _projectDeliverables;
        _driver.navigate().refresh();
        var actual = getActualFieldValue(_driver, "deliverables");

        // Assert       
        assertNotEquals(expected, actual);
    }

    @Test
    public void When_entering_10_million_chars_in_the_quality_control_field_and_save_the_action_must_fail(){
        // Arrange
        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/RP2G6-131-a-137", _propsList);
        _qualityControl = config.getProperty("qualityStandards");
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);

        // Act
        fillInput(_driver, "activities", _qualityControl);
        var expected = _qualityControl;
        _driver.navigate().refresh();
        var actual = getActualFieldValue(_driver, "activities");

        // Assert       
        assertNotEquals(expected, actual);
    }

    @Test
    public void When_entering_10_million_chars_in_the_quality_tools_field_and_save_the_action_must_fail(){
        // Arrange
        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/RP2G6-131-a-137", _propsList);
        _qualityTools = config.getProperty("qualityStandards");
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);

        // Act
        fillInput(_driver, "tools", _qualityTools);
        var expected = _qualityTools;
        _driver.navigate().refresh();
        var actual = getActualFieldValue(_driver, "tools");

        // Assert       
        assertNotEquals(expected, actual);
    }

    @Test
    public void When_entering_10_million_chars_in_the_major_procedures_field_and_save_the_action_must_fail(){
        // Arrange
        var config = Configuration.getConfiguration("MaintainQualityManagementPlan/RP2G6-131-a-137", _propsList);
        _majorProcedures = config.getProperty("qualityStandards");
        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        navigateToMaintainQualityManagementPlan(_driver);

        // Act
        fillInput(_driver, "procedures", _majorProcedures);
        var expected = _majorProcedures;
        _driver.navigate().refresh();
        var actual = getActualFieldValue(_driver, "procedures");

        // Assert       
        assertNotEquals(expected, actual);
    }

    private boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } 
        catch (Exception e) {
            return false;
        } 
    }

    private void uploadFile(WebDriver driver, String imgDescription, String filePath) {
        File file = new File(filePath);         
        WebElement inputDescription = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div/div/div/form/div[8]/div[1]/div/div/form/div[1]/div/div/input")));
        inputDescription.sendKeys(imgDescription);
        
        WebElement inputFile = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div/div/div/form/div[8]/div[1]/div/div/form/div[2]/div/div/input")));
        inputFile.sendKeys(file.getAbsolutePath());   
        
        WebElement btnSubmit = (new WebDriverWait(driver, _timeOutInSeconds))
        .until(ExpectedConditions.presenceOfElementLocated(By.id("img-submit")));
        btnSubmit.click();        
    }    

    private String getTooltipValue(WebDriver driver, String pathTooltip){
        WebElement tooltipTarget = (new WebDriverWait(_driver, _timeOutInSeconds))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(pathTooltip)));        
        var actual = tooltipTarget.getAttribute("data-original-title");
        return actual;
    }

    private void fillInput(WebDriver driver, String idInput, String data){
        WebElement inputQualityStandards = (new WebDriverWait(driver, _timeOutInSeconds))
        .until(ExpectedConditions.presenceOfElementLocated(By.id(idInput)));
        inputQualityStandards.clear();
        inputQualityStandards.sendKeys(data);
        
        WebElement btnSave = (new WebDriverWait(_driver, _timeOutInSeconds))
        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div/div/div/form/div[8]/button")));
        btnSave.click();            
    }

    private String getActualFieldValue(WebDriver driver, String id){
        WebElement inputTarget = (new WebDriverWait(_driver, _timeOutInSeconds))
        .until(ExpectedConditions.presenceOfElementLocated(By.id(id)));        
        var actual = inputTarget.getText();
        return actual;
    }

    private void navigateToMaintainQualityManagementPlan(WebDriver driver) {
        driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/project/75");
        driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/quality/quality-mp/edit/75");
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
