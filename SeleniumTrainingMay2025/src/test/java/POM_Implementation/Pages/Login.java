package POM_Implementation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends BasePage  {
    private final By txtUsername = By.cssSelector("input[placeholder='Username']");
    private final By txtPassword = By.cssSelector("input[placeholder='Password']");
    private final By btnLogin = By.cssSelector("button[type='submit']");

    public Login(WebDriver driver){
        super(driver);
    }

    public void enterLoginCredentials(String username, String password){
        driver.findElement(txtUsername).sendKeys(username);
        driver.findElement(txtPassword).sendKeys(password);
    }

    public void enterLoginCredentials(){
        driver.findElement(txtUsername).sendKeys("Admin");
        driver.findElement(txtPassword).sendKeys("admin123");
    }

    public Dashboard clickLogin(){
        driver.findElement(btnLogin).click();
        return new Dashboard(driver);
    }

//    public void clickLogin(){
//        driver.findElement(btnLogin).click();
//    }



}
