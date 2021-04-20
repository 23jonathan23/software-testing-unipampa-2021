import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MaintainWorkPerformanceReportTest {
    private String _email;
    private String _password;
    private String _projectName;
    private String _projectDescription;
    private String _projectObjective;
    private final String _uriLogin = "http://lesse.com.br/tools/pmst_rp2/";
    private static WebDriver _driver;
    private static List<String> _propsList1 = new ArrayList<String>(); 
    private final int _timeOutInSeconds = 10;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "resources/windows/chromedriver.exe");
        _driver = new ChromeDriver();
        _propsList1.add("email");
        _propsList1.add("password");
        _propsList1.add("projectName");
        _propsList1.add("projectDescription");
        _propsList1.add("projectObjective");
        _propsList1.add("responsible");
        _propsList1.add("date");
        _propsList1.add("activitiesInExecution");
        _propsList1.add("activitiesToPerform");
        _propsList1.add("generalComments");
        _propsList1.add("issues");
        _propsList1.add("changes");
        _propsList1.add("risks");
        _propsList1.add("attentionPoints");
        _propsList1.add("fileUploadPath");
        _propsList1.add("fileDescription");
    }

    @AfterClass
    public static void teardown() {
      _driver.quit();
    }

    @Test
    public void When_to_create_a_new_Work_Performance_Report_with_blank_field_then_it_must_keep_at_the_same_URL() {
        var props = Configuration.getConfiguration("WorkPerformanceReport/RP2G6-72", _propsList1);
        _email = props.getProperty("email");
        _password = props.getProperty("password");
        _projectName = props.getProperty("projectName");
        _projectDescription = props.getProperty("projectDescription");
        _projectObjective = props.getProperty("projectObjective");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        registerProject(_driver, _projectName, _projectDescription, _projectObjective);

        String responsible = "";
        String date = "";
        String activitiesInExecution = "";
        String activitiesToPerform = "";
        String generalComments = "";
        String issues = "";
        String changes = "";
        String risks = "";
        String attentionPoints = "";

        createReport(_driver, responsible, date, activitiesInExecution, activitiesToPerform, 
            generalComments, issues, changes, risks, attentionPoints);

            WebElement findExpected1 = (new WebDriverWait(_driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]")));
          findExpected1.click();
  
          Thread time = new Thread();
          try {
              time.sleep(1000);
          } catch (Exception e) {}
          
          WebElement findExpected2 = (new WebDriverWait(_driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]/ul/li[7]")));
          findExpected2.click();
  
          String notExpectedURL = _driver.getCurrentUrl();

        try {
            time.sleep(2000);
        } catch (Exception e) {}

        String actualURL = _driver.getCurrentUrl();

        _driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/projects");
        deleteProject(_driver);

        assertNotEquals(notExpectedURL, actualURL);
    }

    @Test
    public void When_to_create_a_new_Work_Performance_Report_with_only_the_name_of_the_responsible_then_it_must_keep_at_the_same_URL() { //TODO
        var props = Configuration.getConfiguration("WorkPerformanceReport/RP2G6-73", _propsList1);
        _email = props.getProperty("email");
        _password = props.getProperty("password");
        _projectName = props.getProperty("projectName");
        _projectDescription = props.getProperty("projectDescription");
        _projectObjective = props.getProperty("projectObjective");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        registerProject(_driver, _projectName, _projectDescription, _projectObjective);

        String responsible = props.getProperty("responsible");
        String date = "";
        String activitiesInExecution = "";
        String activitiesToPerform = "";
        String generalComments = "";
        String issues = "";
        String changes = "";
        String risks = "";
        String attentionPoints = "";

        createReport(_driver, responsible, date, activitiesInExecution, activitiesToPerform, 
            generalComments, issues, changes, risks, attentionPoints);

            WebElement findExpected1 = (new WebDriverWait(_driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]")));
          findExpected1.click();
  
          Thread time = new Thread();
          try {
              time.sleep(1000);
          } catch (Exception e) {}
          
          WebElement findExpected2 = (new WebDriverWait(_driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]/ul/li[7]")));
          findExpected2.click();
  
          String notExpectedURL = _driver.getCurrentUrl();

        try {
            time.sleep(2000);
        } catch (Exception e) {}

        String actualURL = _driver.getCurrentUrl();

        _driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/projects");
        deleteProject(_driver);

        assertNotEquals(notExpectedURL, actualURL);
    }

    @Test
    public void When_to_create_a_new_Work_Performance_Report_with_the_correct_inputs_then_it_must_go_to_the_list_URL() { //TODO
        var props = Configuration.getConfiguration("WorkPerformanceReport/RP2G6-74", _propsList1);
        _email = props.getProperty("email");
        _password = props.getProperty("password");
        _projectName = props.getProperty("projectName");
        _projectDescription = props.getProperty("projectDescription");
        _projectObjective = props.getProperty("projectObjective");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        registerProject(_driver, _projectName, _projectDescription, _projectObjective);

        
        String responsible = props.getProperty("responsible");
        String date = props.getProperty("date");
        String activitiesInExecution = props.getProperty("activitiesInExecution");
        String activitiesToPerform = props.getProperty("activitiesToPerform");
        String generalComments = props.getProperty("generalComments");
        String issues = props.getProperty("issues");
        String changes = props.getProperty("changes");
        String risks = props.getProperty("risks");
        String attentionPoints = props.getProperty("attentionPoints");

        createReport(_driver, responsible, date, activitiesInExecution, activitiesToPerform, 
            generalComments, issues, changes, risks, attentionPoints);

            WebElement findExpected1 = (new WebDriverWait(_driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]")));
          findExpected1.click();
  
          Thread time = new Thread();
          try {
              time.sleep(1000);
          } catch (Exception e) {}
          
          WebElement findExpected2 = (new WebDriverWait(_driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]/ul/li[7]")));
          findExpected2.click();
  
          String expectedURL = _driver.getCurrentUrl();

        try {
            time.sleep(2000);
        } catch (Exception e) {}

        String actualURL = _driver.getCurrentUrl();
        String notExpectedMessage = "A PHP Error was encountered";
        String actualMessage = _driver.findElement(By.xpath("/html/body/div[1]/div/section/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td[4]/div[1]/div/form/h4")).getText();

        _driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/projects");
        deleteProject(_driver);

        assertNotEquals(notExpectedMessage, actualMessage);
        assertEquals(expectedURL, actualURL);
        
    }

    @Test
    public void When_to_create_a_new_Work_Performance_Report_with_a_large_number_of_character_in_all_fields_then_it_must_keep_at_the_same_URL() { //TODO
        var props = Configuration.getConfiguration("WorkPerformanceReport/RP2G6-75", _propsList1);
        _email = props.getProperty("email");
        _password = props.getProperty("password");
        _projectName = props.getProperty("projectName");
        _projectDescription = props.getProperty("projectDescription");
        _projectObjective = props.getProperty("projectObjective");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        registerProject(_driver, _projectName, _projectDescription, _projectObjective);

        String responsible = props.getProperty("responsible");
        String date = props.getProperty("date");
        String activitiesInExecution = props.getProperty("activitiesInExecution");
        String activitiesToPerform = props.getProperty("activitiesToPerform");
        String generalComments = props.getProperty("generalComments");
        String issues = props.getProperty("issues");
        String changes = props.getProperty("changes");
        String risks = props.getProperty("risks");
        String attentionPoints = props.getProperty("attentionPoints");

        createReport(_driver, responsible, date, activitiesInExecution, activitiesToPerform, 
            generalComments, issues, changes, risks, attentionPoints);

            WebElement findExpected1 = (new WebDriverWait(_driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]")));
          findExpected1.click();
  
          Thread time = new Thread();
          try {
              time.sleep(1000);
          } catch (Exception e) {}
          
          WebElement findExpected2 = (new WebDriverWait(_driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]/ul/li[7]")));
          findExpected2.click();
  
          String notExpectedURL = _driver.getCurrentUrl();

        try {
            time.sleep(2000);
        } catch (Exception e) {}

        String actualURL = _driver.getCurrentUrl();

        _driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/projects");
        deleteProject(_driver);

        assertNotEquals(notExpectedURL, actualURL);
    }

    @Test
    public void When_to_create_a_new_Work_Performance_Report_with_special_catacters_in_all_fields_then_it_must_keep_at_the_same_URL() { //TODO
        var props = Configuration.getConfiguration("WorkPerformanceReport/RP2G6-76", _propsList1);
        _email = props.getProperty("email");
        _password = props.getProperty("password");
        _projectName = props.getProperty("projectName");
        _projectDescription = props.getProperty("projectDescription");
        _projectObjective = props.getProperty("projectObjective");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        registerProject(_driver, _projectName, _projectDescription, _projectObjective);

        String responsible = props.getProperty("responsible");
        String date = props.getProperty("date");
        String activitiesInExecution = props.getProperty("activitiesInExecution");
        String activitiesToPerform = props.getProperty("activitiesToPerform");
        String generalComments = props.getProperty("generalComments");
        String issues = props.getProperty("issues");
        String changes = props.getProperty("changes");
        String risks = props.getProperty("risks");
        String attentionPoints = props.getProperty("attentionPoints");

        createReport(_driver, responsible, date, activitiesInExecution, activitiesToPerform, 
            generalComments, issues, changes, risks, attentionPoints);

            WebElement findExpected1 = (new WebDriverWait(_driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]")));
        findExpected1.click();

        Thread time = new Thread();
        try {
            time.sleep(1000);
        } catch (Exception e) {}
        
        WebElement findExpected2 = (new WebDriverWait(_driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]/ul/li[7]")));
        findExpected2.click();

        String notExpectedURL = _driver.getCurrentUrl();

        try {
            time.sleep(2000);
        } catch (Exception e) {}

        String actualURL = _driver.getCurrentUrl();

        _driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/projects");
        deleteProject(_driver);

        assertNotEquals(notExpectedURL, actualURL);
    }

    @Test
    public void When_to_create_a_new_Work_Performance_Report_with_only_spaces_in_the_fields_then_it_must_keep_at_the_same_URL() { //TODO
        var props = Configuration.getConfiguration("WorkPerformanceReport/RP2G6-77", _propsList1);
        _email = props.getProperty("email");
        _password = props.getProperty("password");
        _projectName = props.getProperty("projectName");
        _projectDescription = props.getProperty("projectDescription");
        _projectObjective = props.getProperty("projectObjective");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        registerProject(_driver, _projectName, _projectDescription, _projectObjective);

        String responsible = props.getProperty("responsible");
        String date = props.getProperty("date");
        String activitiesInExecution = props.getProperty("activitiesInExecution");
        String activitiesToPerform = props.getProperty("activitiesToPerform");
        String generalComments = props.getProperty("generalComments");
        String issues = props.getProperty("issues");
        String changes = props.getProperty("changes");
        String risks = props.getProperty("risks");
        String attentionPoints = props.getProperty("attentionPoints");

        createReport(_driver, responsible, date, activitiesInExecution, activitiesToPerform, 
            generalComments, issues, changes, risks, attentionPoints);

            WebElement findExpected1 = (new WebDriverWait(_driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]")));
          findExpected1.click();
  
          Thread time = new Thread();
          try {
              time.sleep(1000);
          } catch (Exception e) {}
          
          WebElement findExpected2 = (new WebDriverWait(_driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]/ul/li[7]")));
          findExpected2.click();
  
          String notExpectedURL = _driver.getCurrentUrl();

        try {
            time.sleep(2000);
        } catch (Exception e) {}

        String actualURL = _driver.getCurrentUrl();

        _driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/projects");
        deleteProject(_driver);

        assertNotEquals(notExpectedURL, actualURL);
    }

    @Test
    public void When_to_click_to_edit_a_report_it_must_change_URL() {
        var props = Configuration.getConfiguration("WorkPerformanceReport/RP2G6-78", _propsList1);
        _email = props.getProperty("email");
        _password = props.getProperty("password");
        _projectName = props.getProperty("projectName");
        _projectDescription = props.getProperty("projectDescription");
        _projectObjective = props.getProperty("projectObjective");

        _driver.navigate().to(_uriLogin);
        signin(_driver, _email, _password);
        registerProject(_driver, _projectName, _projectDescription, _projectObjective);

        String responsible = props.getProperty("responsible");
        String date = props.getProperty("date");
        String activitiesInExecution = props.getProperty("activitiesInExecution");
        String activitiesToPerform = props.getProperty("activitiesToPerform");
        String generalComments = props.getProperty("generalComments");
        String issues = props.getProperty("issues");
        String changes = props.getProperty("changes");
        String risks = props.getProperty("risks");
        String attentionPoints = props.getProperty("attentionPoints");

        createReport(_driver, responsible, date, activitiesInExecution, activitiesToPerform, 
            generalComments, issues, changes, risks, attentionPoints);

        WebElement findExpected1 = (new WebDriverWait(_driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]")));
        findExpected1.click();

        Thread time = new Thread();
        try {
            time.sleep(1000);
        } catch (Exception e) {}
        
        WebElement findExpected2 = (new WebDriverWait(_driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]/ul/li[7]")));
        findExpected2.click();

        String notExpectedURL = _driver.getCurrentUrl();
        
        WebElement inputTitleElementById = (new WebDriverWait(_driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div/div/div/div[2]/div/div/div[2]/div/table/tbody/tr/td[4]/div[1]/button")));
        inputTitleElementById.click();

        try {
            time.sleep(4000);
        } catch (Exception e) {}
        String actualURL = _driver.getCurrentUrl();

        _driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/projects");
        deleteProject(_driver);

        assertNotEquals(notExpectedURL, actualURL);
    }

    private void deleteProject(WebDriver driver) {
        WebElement inputTitleElementById = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div/div/div/div[2]/table/tbody[2]/tr/td[3]/a[4]/span")));
        inputTitleElementById.click();

        Thread time = new Thread();
        try {
            time.sleep(2000);
        } catch (Exception e){}

        Alert alerta = driver.switchTo().alert();
        alerta.accept();

        WebElement deleteMessage = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div[1]/strong")));
        String message = deleteMessage.getText();
        System.out.println(message);
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

    private void createReport(WebDriver driver, String nome, String date, String mainActivities, String nextActivities, String comments, String issues, String changes, String risks, String attentionPoints) {

        WebElement findExpected = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div[2]/div/div/div[2]/table/tbody[2]/tr/td[3]/a[1]/span")));
        findExpected.click();

        WebElement findExpected1 = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]")));
        findExpected1.click();

        Thread time = new Thread();
        try {
            time.sleep(1000);
        } catch (Exception e) {}
        
        WebElement findExpected2 = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]/ul/li[7]")));
        findExpected2.click();

        WebElement clickToAdd = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div/div/div/div[1]/div/button")));
        clickToAdd.click();

        
      
        WebElement inputNomeElement = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.id("responsible")));
        inputNomeElement.sendKeys(nome);

       

        WebElement inputDateElement = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.id("date")));
        inputDateElement.sendKeys(date);

        WebElement inputMainActivitiesElement = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.id("main_activities")));
        inputMainActivitiesElement.sendKeys(mainActivities);

        WebElement inputNextActivitiesElement = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.id("next_activities")));
        inputNextActivitiesElement.sendKeys(nextActivities);

        WebElement inputCommentsElement = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.id("comments")));
        inputCommentsElement.sendKeys(comments);

        WebElement inputIssuesElement = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.id("issues")));
        inputIssuesElement.sendKeys(issues);

        WebElement inputChangesElement = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.id("changes")));
        inputChangesElement.sendKeys(changes);

        WebElement inputRisksElement = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.id("risks")));
        inputRisksElement.sendKeys(risks);

        WebElement inputEttentionPointsElement = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.id("attention_points")));
        inputEttentionPointsElement.sendKeys(attentionPoints);

        WebElement createButton = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.id("work_performance_report-submit")));
        createButton.click();
    }

    private void uploadFile(WebDriver driver, String path) {
        WebElement findExpected = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div[2]/div/div/div[2]/table/tbody[2]/tr/td[3]/a[1]/span")));
        findExpected.click();

        WebElement findExpected1 = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]")));
        findExpected1.click();

        Thread time = new Thread();
        try {
            time.sleep(1000);
        } catch (Exception e) {}
        
        WebElement findExpected2 = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/aside/section/ul/li[4]/ul/li[7]")));
        findExpected2.click();

        WebElement findExpected3 = (new WebDriverWait(driver, _timeOutInSeconds))
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div/div/div/div[3]/div/div/form/div[2]/div/div/input")));
        findExpected3.sendKeys(path);

        try {
            time.sleep(10000);
        } catch (Exception e) {}
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
