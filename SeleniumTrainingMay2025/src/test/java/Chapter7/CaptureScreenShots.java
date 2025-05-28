package Chapter7;

import com.google.common.io.Files;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CaptureScreenShots {

    WebDriver driver;
    @BeforeAll
    void setUp(){
        driver = new EdgeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
    }

    @AfterEach
    void takeAScreenShot(TestInfo info){

        try{
            Thread.sleep(2000);
            File sourceFile = ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.FILE);

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            String fileName = info.getDisplayName() + "_[" + timeStamp + "].png";
            File destinationFile = new File("src/test/resources/screen shots/" + fileName);
            //move from source to destination
            Files.move(sourceFile,destinationFile);
        }catch(IOException | InterruptedException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Test
    @DisplayName("Valid Login")
    void login(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

    }
    @ParameterizedTest(name = "Invalid Login")
    @CsvSource(value = {"Admin,admin", "admin123,admin123"})
    void testinValidLogin(String username, String password){
        String expected = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys(username);
        driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String actual = driver.getCurrentUrl().toString();

        Assertions.assertEquals(expected, actual);
    }
}
