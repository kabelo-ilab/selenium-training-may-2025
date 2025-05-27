import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Login {
    public static void main(String[] args) {
        WebDriver driver = new EdgeDriver();

        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

        //locate the Form Authentication link
        WebElement linkFormAuth = driver.findElement(By.cssSelector("a[href=\"/login\"]"));
        linkFormAuth.click();

        //validate that the login page is loaded
        boolean isLoaded = driver.getCurrentUrl().equals("https://the-internet.herokuapp.com/login");
        System.out.println(isLoaded);

        WebElement txtUsername = driver.findElement(By.cssSelector("input[id=\"username\"]"));
        txtUsername.sendKeys("tomsmith");

        WebElement txtPassword = driver.findElement(By.cssSelector("input[id=\"password\"]"));
        txtPassword.sendKeys("SuperSecretPassword!");

        WebElement btnLogin = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
        btnLogin.click();

    }
}
