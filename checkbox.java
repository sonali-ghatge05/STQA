import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class checkbox {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://the-internet.herokuapp.com/checkboxes");
            List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox"));

            int total = checkboxes.size();
            int checked = 0;
            int unchecked = 0;

            for (WebElement checkbox : checkboxes) {
                if (checkbox.isSelected()) {
                    checked++;
                } else {
                    unchecked++;
                }
            }

            System.out.println("Total checkboxes: " + total);
            System.out.println(("Checked checkboxes: " + checked));
            System.out.println("Unchecked checkboxes: " + unchecked);

            for (WebElement checkbox : checkboxes) {
                if (!checkbox.isSelected()) {
                    checkbox.click();
                    break;
                }
            }
            int newchecked = 0;
            for (WebElement checkbox : checkboxes) {
                if (checkbox.isSelected()) {
                    newchecked++;
                }
            }
            System.out.println("checked after selection: " + newchecked);

            if (newchecked == checked + 1) {
                System.out.println("Validation passed");
            } else {
                System.out.println("Validation failed");
            }
        } finally {
            driver.quit();
        }
    }
}