package POM_Implementation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Dashboard extends BasePage {

    //private final By menuPIM = By.cssSelector(".oxd-main-menu-item.active");
    private final By menuPIM = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a");
    //private final By menuPIM = By.xpath("/body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > aside:nth-child(1) > nav:nth-child(1) > div:nth-child(2) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1) > span:nth-child(2)");
    public String url = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

    public Dashboard(WebDriver myDriver){
        super(myDriver);
    }
    public void clickPIM_Menu(){
        driver.findElement(menuPIM).click();

    }


}
