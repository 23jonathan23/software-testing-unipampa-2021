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
        _propsList1.add("fileDescription");
    }

    @AfterClass
    public static void teardown() {
      _driver.quit();
    }

    @Test
    public void test1() {
        _driver.navigate().to(_uriLogin);
        signin(_driver, "usuarioGrupo6@gmail.com", "123456");
        registerProject(_driver, "titulo1", "descricao1", "objetivo1");
    }

    @Test
    public void test2() {
        _driver.navigate().to(_uriLogin);
        signin(_driver, "usuarioGrupo6@gmail.com", "123456");
        deleteProject(_driver);
    }

    @Test
    public void test3() {
        _driver.navigate().to(_uriLogin);
        signin(_driver, "usuarioGrupo6@gmail.com", "123456");
        createReport(_driver, "tana", "17/04/2021", "tama", "tama", "tama", "tama", "tama", "tama", "tama");
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
          .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div/div/div/div[2]/table/tbody[2]/tr/td[3]/a[1]")));
        findExpected.click();
        

        driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/integration/work-performance-reports/list/116");

        

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
