package POM_Implementation.TestCases;

import POM_Implementation.Pages.AddEmployee;
import POM_Implementation.Pages.Dashboard;
import POM_Implementation.Pages.Login;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BaseTest {
    protected static WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentSparkReporter report;
    protected String reportUrl = "src/test/resources/reports/my report2.html";
    protected String screenShotPath = "";
    protected Login loginPage;
    protected Dashboard dashboardPage;
    protected AddEmployee addEmployeePage;

    public void init(String browser, String url){

        try {
            driver = initializeWebDriver(browser,url);
            extent = new ExtentReports();
            report = new ExtentSparkReporter(reportUrl);
            extent.attachReporter(report);

            loginPage = new Login(driver);
            dashboardPage = new Dashboard(driver);
            addEmployeePage = new AddEmployee(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public WebDriver initializeWebDriver(String browser, String url) throws Exception{

        if (browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }else{
            throw new Exception("Browser not found");
        }

        driver.get(url);
        driver.manage().window().maximize();

        return driver;
    }

    public void createReport(TestInfo info){
        ExtentTest myTest = extent.createTest(info.getDisplayName());
        try{
            Thread.sleep(3000);
            String sourceFile = ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.BASE64);

//            if (isPass == true){
//                myTest.log(Status.PASS,"Test was successful. " + info.getDisplayName());
//            }else{
//                myTest.log(Status.FAIL,"Test for " + info.getDisplayName() + " failed");
//            }

            myTest.addScreenCaptureFromBase64String(sourceFile);

        }catch(InterruptedException ex){
            System.err.println(ex.getMessage());
        }
    }

    public void tearDown(){

        try {
            extent.flush();
            driver.quit();
            Desktop.getDesktop().browse(new File(reportUrl).toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
