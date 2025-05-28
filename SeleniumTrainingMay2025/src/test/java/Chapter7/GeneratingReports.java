package Chapter7;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;


import java.time.Duration;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GeneratingReports {

    WebDriver driver;
    ExtentReports extent;
    ExtentSparkReporter rpt;
    boolean isPass;

    @BeforeAll
    void setUp(){
        isPass = false;
        extent = new ExtentReports();
        rpt = new ExtentSparkReporter("src/test/resources/reports/Automation.html");
        rpt.config().setReportName("Automation Test Report");
        rpt.config().setDocumentTitle("HRM Login Feature");
        driver = new EdgeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
    }
    @Test
    @DisplayName("Valid Login")
    void login(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String expected = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        String actual = driver.getCurrentUrl().toString();

        isPass = expected.equals(actual);//true

        Assertions.assertTrue(isPass);

    }

    @ParameterizedTest(name = "Invalid Login")
    @CsvSource(value = {"Admin,admin", "admin123,admin123"})
    void testinValidLogin(String username, String password){
        //String expected = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys(username);
        driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String expected = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        String actual = driver.getCurrentUrl().toString();

        isPass = expected.equals(actual);//false

        Assertions.assertFalse(isPass);
    }

    @AfterEach
    void generateTestReport(TestInfo info){

        ExtentTest myTest = extent.createTest(info.getDisplayName());

        try{
            Thread.sleep(3000);
            String sourceFile = ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.BASE64);

            if (isPass == true){
                myTest.log(Status.PASS,"Test was successful. " + info.getDisplayName());
            }else{
                myTest.log(Status.FAIL,"Test for " + info.getDisplayName() + " failed");
            }

            myTest.addScreenCaptureFromBase64String(sourceFile);

        }catch(InterruptedException ex){
            System.err.println(ex.getMessage());
        }
    }

    @AfterAll
    void tearDown(){
        extent.attachReporter(rpt);
        extent.flush();
    }
}
