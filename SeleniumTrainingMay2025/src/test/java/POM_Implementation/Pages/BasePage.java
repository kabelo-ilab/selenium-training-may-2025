package POM_Implementation.Pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver myDriver){
        this.driver = myDriver;
    }
}
