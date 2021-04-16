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

public class AddMemberTest {
    
    private String _email;
    private String _password;    
    private String _emailNewMember;    
    private static List<String> _propsList = new ArrayList<String>(); 
    private final String _uriLogin = "http://lesse.com.br/tools/pmst_rp2/";
    private final String _pathAddMember = "/html/body/div[1]/div/section/div/div/div/div[2]/table/tbody/tr/td[3]/a[3]";
    private final String _pathInputEmail = "/html/body/div[1]/div/section/div/div/div/form/div[1]/div/input";
    private final int _timeOutInSeconds = 10;
    private static WebDriver _driver;
    
    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "resources/windows/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        _driver = new ChromeDriver(options);
        _propsList.add("email");
        _propsList.add("password");
        _propsList.add("emailNewMember");
    }

    @AfterClass
    public static void teardown() {
        _driver.quit();
    }

    @Test
    public void when_add_member_with_spaces_in_the_email_field_then_a_warning_message_must_be_shown(){
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-29", _propsList);
        
        _email = config.getProperty("email");
        _password = config.getProperty("password");
        _emailNewMember = config.getProperty("emailNewMember");
        
        _driver.navigate().to(_uriLogin);
        
        signin(_driver, _email, _password);
      
        //Act
        addMember(_driver, _emailNewMember, 0);
        String notExpected = "User update!";
        
        //Assert
        assertNotEquals(notExpected, msgReturn(_driver));
        
        logout();
    }
    @Test
    public void when_add_member_with_empty_the_email_field_then_a_warning_message_must_be_shown(){
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-30", _propsList);
        
        _email = config.getProperty("email");
        _password = config.getProperty("password");
        _emailNewMember = "";

        _driver.navigate().to(_uriLogin);
        
      
        //Act
        addMember(_driver, _emailNewMember, 0);
        
        //Assert
        assertNotEquals(_uriLogin, _driver.getCurrentUrl());
        
        logout();
    }
    
    @Test
    public void when_add_member_with_email_not_cadastred_the_email_field_then_a_warning_message_must_be_shown(){
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-31", _propsList);
        
        _email = config.getProperty("email");
        _password = config.getProperty("password");
        _emailNewMember = config.getProperty("emailNewMember");
        
        _driver.navigate().to(_uriLogin);
            
        signin(_driver, _email, _password);
        
      
        //Act
        addMember(_driver, _emailNewMember, 0);
        String Expected = "This e-mail doesn`t exist in our database!";
        
        //Assert
        assertEquals(Expected, msgReturn(_driver));
        
        logout();
    }
    
    @Test
    public void when_add_member_with_correct_email_the_email_field_then_a_success_message_must_be_shown(){
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-32", _propsList);
        
        _email = config.getProperty("email");
        _password = config.getProperty("password");
        _emailNewMember = config.getProperty("emailNewMember");
        
        _driver.navigate().to(_uriLogin);
            
        signin(_driver, _email, _password);
        
      
        //Act
        addMember(_driver, _emailNewMember, 0);
        String Expected = "User update!";
        
        //Assert
        assertEquals(Expected, msgReturn(_driver));
        
        logout();
    }
    
    @Test
    public void when_add_member_with_current_user__the_email_field_then_a_warning_message_must_be_shown(){
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-33", _propsList);
        
        _email = config.getProperty("email");
        _password = config.getProperty("password");
        _emailNewMember = config.getProperty("emailNewMember");
        
        _driver.navigate().to(_uriLogin);
            
        signin(_driver, _email, _password);
        
      
        //Act
        addMember(_driver, _emailNewMember, 2);
        String notExpected = "User update!";
        
        //Assert
        assertNotEquals(notExpected, msgReturn(_driver));
        
        logout();
    }
    
    @Test
    public void when_add_member_with_already_existing_the_email_field_then_a_warning_message_must_be_shown(){
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-34", _propsList);
        
        _email = config.getProperty("email");
        _password = config.getProperty("password");
        _emailNewMember = config.getProperty("emailNewMember");
        
        _driver.navigate().to(_uriLogin);
            
        signin(_driver, _email, _password);
        
      
        //Act
        addMember(_driver, _emailNewMember, 0);
        String Expected = "User update!";
        
        //Assert
        assertEquals(Expected, msgReturn(_driver));
        
        logout();
    }
    
    @Test
    public void when_add_a_member_with_a_staff_user_then_a_warning_message_must_be_shown(){
     //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-35", _propsList);
        
        _email = config.getProperty("email");
        _password = config.getProperty("password");
        _emailNewMember = config.getProperty("emailNewMember");
        
        _driver.navigate().to(_uriLogin);
            
        signin(_driver, _email, _password);
             
        //Act
        String uriAddMember = _driver.findElement(By.xpath("/html/body/div[1]/div/section/div/div/div/div[2]/table/tbody/tr/td[3]/a[3]")).getAttribute("href"); 
          
        _driver.navigate().to(uriAddMember);
        
        addMemberNotClick(_driver, _emailNewMember);
        String NotExpected =  "User update!";
        
        //Assert
        assertNotEquals(NotExpected, msgReturn(_driver));
        
        logout();
    }
    
    @Test
    public void when_add_a_member_with_a_professor_user_then_a_warning_message_must_be_shown(){
     //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-36", _propsList);
        
        _email = config.getProperty("email");
        _password = config.getProperty("password");
        _emailNewMember = config.getProperty("emailNewMember");
        
        _driver.navigate().to(_uriLogin);
            
        signin(_driver, _email, _password);
             
        //Act
        String uriAddMember = _driver.findElement(By.xpath("/html/body/div[1]/div/section/div/div/div/div[2]/table/tbody/tr/td[3]/a[3]")).getAttribute("href"); 
          
        _driver.navigate().to(uriAddMember);
        
        addMemberNotClick(_driver, _emailNewMember);
        String NotExpected =  "User update!";
        
        //Assert
        assertNotEquals(NotExpected, msgReturn(_driver));
        
        logout();
    }

    @Test
    public void test_to_input_an_empty_email_and_give_Professor_permission_then_the_URL_must_be_the_same_and_the_addition_doesnt_happen() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-37", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = "";

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 1);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        assertEquals(expectedURL, currentURL);
        logout();
    }

    @Test
    public void test_to_input_an_empty_email_and_give_Project_Manager_permission_then_the_URL_must_be_the_same_and_the_addition_doesnt_happen() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-38", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = "";

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 2);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        assertEquals(expectedURL, currentURL);
        logout();
    }

    @Test
    public void test_to_add_member_with_correct_email_the_email_field_and_give_Project_Manager_permission_then_a_success_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-39", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 2);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/projects/";
        String currentURL = _driver.getCurrentUrl();

        String expectedMessage = "User update!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_add_member_with_correct_email_the_email_field_and_give_Professor_permission_then_a_success_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-40", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 1);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/projects/";
        String currentURL = _driver.getCurrentUrl();

        String expectedMessage = "User update!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_add_member_with_spaces_in_the_email_field_and_give_Project_Manager_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-42", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 2);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        String notExpectedMessage = "User update!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertNotEquals(notExpectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_add_member_with_spaces_in_the_email_field_and_give_Professor_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-43", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 1);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        String notExpectedMessage = "User update!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertNotEquals(notExpectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_add_member_with_email_without_the_user_part_and_give_Project_Manager_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-45", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 2);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        String expectedMessage = "This e-mail doesn`t exist in our database!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_add_member_with_email_without_the_user_part_and_give_Professor_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-46", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 1);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        String expectedMessage = "This e-mail doesn`t exist in our database!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_add_member_with_email_without_the_user_part_and_give_Staff_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-47", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 0);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        String expectedMessage = "This e-mail doesn`t exist in our database!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_add_member_with_email_without_the_at_sign_and_give_Project_Manager_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-48", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 2);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        String expectedMessage = "This e-mail doesn`t exist in our database!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_add_member_with_email_without_the_at_sign_and_give_Professor_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-49", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 1);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        String expectedMessage = "This e-mail doesn`t exist in our database!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_add_member_with_email_without_the_at_sign_and_give_Staff_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-50", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 0);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        String expectedMessage = "This e-mail doesn`t exist in our database!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_add_member_with_email_without_the_provider_and_give_Project_Manager_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-51", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 2);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        String expectedMessage = "This e-mail doesn`t exist in our database!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_add_member_with_email_without_the_provider_and_give_Professor_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-52", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 1);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        String expectedMessage = "This e-mail doesn`t exist in our database!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_add_member_with_email_without_the_provider_and_give_Staff_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-53", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 0);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        String expectedMessage = "This e-mail doesn`t exist in our database!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_add_member_with_email_with_a_space_insted_of_at_sign_and_give_Project_Manager_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-54", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 2);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        String expectedMessage = "This e-mail doesn`t exist in our database!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_add_member_with_email_with_a_space_insted_of_at_sign_and_give_Professor_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-55", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 1);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        String expectedMessage = "This e-mail doesn`t exist in our database!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_add_member_with_email_with_a_space_insted_of_at_sign_and_give_Staff_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-56", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act
        addMember(_driver, _emailNewMember, 0);

        String expectedURL = "http://lesse.com.br/tools/pmst_rp2/researcher/75";
        String currentURL = _driver.getCurrentUrl();

        String expectedMessage = "This e-mail doesn`t exist in our database!";
        String actualMessage = msgReturn(_driver);

        assertEquals(expectedURL, currentURL);
        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_input_the_email_name_on_the_email_field_and_give_Project_Manager_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-60", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act

        addMember(_driver, _emailNewMember, 2);

        String expectedMessage = "This e-mail doesn`t exist in our database!";
        String actualMessage = msgReturn(_driver);

        System.out.println(actualMessage);

        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_input_the_email_name_on_the_email_field_and_give_Professor_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-60", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act

        addMember(_driver, _emailNewMember, 1);

        String expectedMessage = "This e-mail doesn`t exist in our database!";
        String actualMessage = msgReturn(_driver);

        System.out.println(actualMessage);

        assertEquals(expectedMessage, actualMessage);
        logout();
    }

    @Test
    public void test_to_input_the_email_name_on_the_email_field_and_give_Staff_permission_then_a_warning_message_must_be_shown() {
        //Arrange
        var config = Configuration.getConfiguration("AddMember/RP2G6-60", _propsList);

        String _email = config.getProperty("email");
        String _password = config.getProperty("password");
        String _emailNewMember = config.getProperty("emailNewMember");

        _driver.navigate().to(_uriLogin);

        signin(_driver, _email, _password);

        //Act

        addMember(_driver, _emailNewMember, 0);

        String expectedMessage = "This e-mail doesn`t exist in our database!";
        String actualMessage = msgReturn(_driver);

        System.out.println(actualMessage);

        assertEquals(expectedMessage, actualMessage);
        logout();
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
    
    public void addMember(WebDriver driver, String emailNewMember, int index){

        Thread time = new Thread();
        try{
            time.sleep(5000);
        }
        catch (InterruptedException iex) {};

        WebElement clickAddMember = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(_pathAddMember)));
        clickAddMember.click();
        WebElement inputEmailElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(_pathInputEmail)));
        inputEmailElementById.sendKeys(emailNewMember); 

        WebElement selectElement = _driver.findElement(By.xpath("/html/body/div[1]/div/section/div/div/div/form/div[2]/div/select"));
        Select select = new Select(selectElement);
        select.selectByIndex(index);

        try {
            time.sleep(2000);
        } catch (InterruptedException iex) {};
        
        WebElement submitLoginElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("add_researcher_submit")));
        submitLoginElementById.click();
    }
    
    public void addMemberNotClick(WebDriver driver, String emailNewMember){

        WebElement inputEmailElementById = (new WebDriverWait(driver, _timeOutInSeconds))
            .until(ExpectedConditions.presenceOfElementLocated(By.xpath(_pathInputEmail)));
        inputEmailElementById.sendKeys(emailNewMember);        
        
        WebElement selectElement = _driver.findElement(By.id("access_level"));
        Select selectObject = new Select(selectElement);
        selectObject.selectByIndex(2);
        
        WebElement submitLoginElementById = (new WebDriverWait(driver, _timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("add_researcher_submit")));
        submitLoginElementById.click();
    }
    
    private String getMessageError(WebDriver driver) {
        String errorMessage = driver.findElement(By.id("email")).getAttribute("validationMessage");

        return errorMessage;
    }

    private String msgReturn(WebDriver driver) {
        String mensagem = null;
        
        try {
            WebDriverWait wait = new WebDriverWait(driver, _timeOutInSeconds);
            mensagem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/section/div[1]/strong"))).getText();
        } catch (Exception e) { 
        }

        return mensagem;
    }
    private void logout(){
       _driver.navigate().to("http://lesse.com.br/tools/pmst_rp2/authentication/logout");
       _driver.navigate().refresh();

    }
}
