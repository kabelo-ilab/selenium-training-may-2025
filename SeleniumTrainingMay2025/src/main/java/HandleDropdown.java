import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HandleDropdown {
    public static void main(String[] args) {
        WebDriver driver = new EdgeDriver();

        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

        WebElement linkDropDown = driver.findElement(By.xpath("//a[@href=\"/dropdown\"]"));
        linkDropDown.click();

        WebElement dropDownOptions = driver.findElement(By.xpath("//select[@id=\"dropdown\"]"));
        Select options = new Select(dropDownOptions);

        options.selectByVisibleText("Option 1");

        MultiSelectDropDown();

    }

    public static void MultiSelectDropDown(){

        WebDriver driver = new EdgeDriver();
        driver.get("https://ironspider.ca/forms/dropdowns.htm");
        driver.manage().window().maximize();

        WebElement dropDownCoffee = driver.findElement(By.name("coffee2"));
        Select coffeeOptions = new Select(dropDownCoffee);

        coffeeOptions.selectByValue("skim");
        coffeeOptions.selectByValue("honey");
        coffeeOptions.selectByValue("muffin");
        coffeeOptions.deselectByValue("cream");

    }
}
