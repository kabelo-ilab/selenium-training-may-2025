package Chapter6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Login {

    WebDriver driver;
    @BeforeAll
    void setUp(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @ParameterizedTest
    @CsvSource(value = {"Admin,admin123", "Admin,admin", "admin123,admin123"})
    void testValidLogin(String username, String password){
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
