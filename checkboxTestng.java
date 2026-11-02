import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class checkboxTestng {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }

    @Test
    public void checkboxTest() {

        List<WebElement> checkboxes =
                driver.findElements(By.cssSelector("input[type='checkbox']"));

        int total = checkboxes.size();
        int checked = 0;
        int unchecked = 0;

        for (WebElement cb : checkboxes) {
            if (cb.isSelected())
                checked++;
            else
                unchecked++;
        }

        System.out.println("Total: " + total);
        System.out.println("Checked: " + checked);
        System.out.println("Unchecked: " + unchecked);

        for (WebElement cb : checkboxes) {
            if (!cb.isSelected()) {
                cb.click();
                break;
            }
        }

        int newchecked = 0;

        for (WebElement cb : checkboxes) {
            if (cb.isSelected())
                newchecked++;
        }

        Assert.assertEquals(newchecked, checked + 1);

        System.out.println("Validation passed");
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}