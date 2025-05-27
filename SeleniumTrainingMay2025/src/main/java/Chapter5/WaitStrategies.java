package Chapter5;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

public class WaitStrategies {

    static WebDriver driver;
    public static void main(String[] args) {

        driver = new EdgeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

        //implicitWait();
       // explicitWait();
        theFluentWait();

    }

    public static void implicitWait(){

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.cssSelector("button[ng-click='customer()']")).click();
    }

    public static void explicitWait(){

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[ng-click='customer()']")))
                .click();
    }

    public static void theFluentWait(){

       driver.findElement(By.cssSelector("a[href=\"/dynamic_loading\"]")).click();
       driver.findElement(By.cssSelector("a[href=\"/dynamic_loading/1\"]")).click();
       driver.findElement(By.cssSelector("div[id='start'] button")).click();

        Wait driverWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[id='finish'] h4")));

    }
}
