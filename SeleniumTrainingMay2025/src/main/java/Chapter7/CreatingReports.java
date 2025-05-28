package Chapter7;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class CreatingReports {
    public static void main(String[] args) {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("src/main/resources/reports/spark.html");

        //configure the spark report
        spark.config().setDocumentTitle("Test Summary");
        spark.config().setReportName("The Spark Report");

        extent.attachReporter(spark);

        //Create tests
        ExtentTest test1 = extent.createTest("Test 1");
        test1.fail("This test failed");

        ExtentTest test2 = extent.createTest("Test 2");
        test2.log(Status.PASS,"This test has passed");

        ExtentTest test3 = extent.createTest("Test 3");
        test3.warning("Just a warning");

        ExtentTest test4 = extent.createTest("Test 4");
        test4.info("This is just info");

        extent.flush();

    }

}
