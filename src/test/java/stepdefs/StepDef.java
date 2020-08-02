package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.openqa.selenium.By.*;
import static org.openqa.selenium.By.xpath;

public class StepDef {
    private WebDriver driver;
    private Actions actions;

    private final By SUBCATHEGORY = xpath(".//a[@class='submenu-lvl2__list-item-link']");
    private final By MENU_ITEMS = xpath(".//a[@class ='submenu-lvl1__link']");
    private final By MIN_PRICE = id("attr-from-price");
    private final By MAX_PRICE = id("attr-to-price");
    private final By BLANC_SPACE = xpath(".//span[contains(@class, 'sort-label')]");
    private final By ITEM_COUNT = xpath(".//span[contains(@class, '-count-label')]//b");

    @Given("we on 1a.lv home page")
    public void get_page() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/simon/Desktop/dr/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://1a.lv");
        actions = new Actions(driver);
    }

    @Given("we hover {string}")
    public void hover(String category) {
        List<WebElement> menuItems = driver.findElements(MENU_ITEMS);
        for (WebElement elementMenu : menuItems) {
            String elementName = elementMenu.getText();
            if ((elementName.equals(category))) {
                actions.moveToElement(elementMenu).perform();
                break;
            }
        }
    }

    @Given("click on {string}")
    public void click(String subcategory) {
        List<WebElement> subCathegories = driver.findElements(SUBCATHEGORY);
        for (WebElement subcathegory : subCathegories) {
            String subName = subcathegory.getText();
            if (subName.equals(subcategory)) {
                actions.moveToElement(subcathegory).click().build().perform();
                break;
            }
        }
    }

    @When("I put {string} and {string}")
    public void put_price(String minPrice, String maxPrice) {
        WebElement min = driver.findElement(MIN_PRICE);
        WebElement max = driver.findElement(MAX_PRICE);

        String[] value = min.getAttribute("value").split("");
        for (String s : value){
            min.sendKeys(Keys.BACK_SPACE);
        }
        min.sendKeys(minPrice);

        //element not clickable
        driver.findElement(BLANC_SPACE).click();
        //----
        max.clear();
        max.sendKeys(maxPrice);
        driver.findElement(BLANC_SPACE).click();
    }

    @Then("Items on page equals {string}")
    public void number_of_items(String number) {
        String itemCount = driver.findElement(ITEM_COUNT).getText();
        System.out.println(itemCount);
//        Assert.assertEquals(number, itemCount, "Incorrect number count");

    }

}
