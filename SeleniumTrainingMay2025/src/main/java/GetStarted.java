import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class GetStarted {
    public static void main(String[] args) {
        //instantiate a web driver object
        WebDriver driver = new EdgeDriver();
        //open a URL
        driver.get("https://www.google.com");
        driver.manage().window().maximize();

        System.out.println("Title: " + driver.getTitle());
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("Window ID: " + driver.getWindowHandle());

        System.out.println("=========================================");
        driver.navigate().to("https://www.ilabquality.com/");

        System.out.println("Title: " + driver.getTitle());
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("Window ID: " + driver.getWindowHandle());

        driver.navigate().back();

        driver.close();


    }
}
