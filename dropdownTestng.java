import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class dropdownTestng {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dropdown");
    }

    @Test
    public void dropdownTest() {

        List<WebElement> options =
                driver.findElements(By.cssSelector("#dropdown option"));

        System.out.println("Total options: " + options.size());

        for (WebElement option : options) {
            if (option.isSelected()) {
                System.out.println("Default value: " + option.getText());
            }
        }

        for (WebElement option : options) {
            if (option.getText().equals("Option 1")) {
                option.click();
                break;
            }
        }

        String selected = "";

        for (WebElement option : options) {
            if (option.isSelected()) {
                selected = option.getText();
            }
        }

        System.out.println("After selection: " + selected);

        Assert.assertEquals(selected, "Option 1");

        System.out.println("Validation passed");
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}