package POM_Implementation.TestCases;

import POM_Implementation.Pages.Dashboard;
import POM_Implementation.Pages.Login;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HRMLogin extends BaseTest {

    @BeforeAll
    void setup(){
        try {
            init("chrome","https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(1)
    void validLogin(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        loginPage.enterLoginCredentials();
        //click login
        dashboardPage = loginPage.clickLogin();
        String expected = dashboardPage.url;
        String actual = driver.getCurrentUrl();

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Navigate to PIM")
    @Order(2)
    void navigateToPIM(){
        System.err.println(driver.getCurrentUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        dashboardPage.clickPIM_Menu();
        System.err.println(driver.getCurrentUrl());
    }

    @BeforeEach
    void generateReport(TestInfo info){
        createReport(info);
    }

    @AfterAll
    void dispose(){
        tearDown();
    }



}
