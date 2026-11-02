import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class DynamicWebTable {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            driver.get("https://datatables.net/examples/basic_init/zero_configuration.html");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("example")));

            // Print table data
            System.out.println("--- TABLE DATA ---");
            List<WebElement> rows = driver.findElements(By.cssSelector("#example tbody tr"));
            rows.forEach(row -> System.out.println(
                    row.findElements(By.tagName("td")).stream()
                            .map(WebElement::getText).collect(Collectors.toList())));

            // Sort by Age
            driver.findElement(By.cssSelector("#example thead th:nth-child(4)")).click();
            wait.until(d -> "ascending".equals(
                    d.findElement(By.cssSelector("#example thead th:nth-child(4)")).getAttribute("aria-sort")));

            // Validate sorting
            rows = driver.findElements(By.cssSelector("#example tbody tr"));
            List<Integer> ages = rows.stream()
                    .map(r -> Integer.parseInt(r.findElements(By.tagName("td")).get(3).getText()))
                    .collect(Collectors.toList());

            System.out.println("\n--- SORTING VALIDATION ---");
            System.out.println("Extracted Ages: " + ages);
            List<Integer> sorted = new ArrayList<>(ages);
            Collections.sort(sorted);
            System.out.println(ages.equals(sorted) ? "PASS: Age sorting is correct." : "FAIL: Age sorting is incorrect.");

            // Filter by Tokyo
            WebElement search = driver.findElement(By.cssSelector("input[type='search']"));
            search.clear();
            search.sendKeys("Tokyo");

            wait.until(d -> {
                List<WebElement> filtered = d.findElements(By.cssSelector("#example tbody tr"));
                return !filtered.isEmpty() && filtered.stream()
                        .allMatch(r -> r.findElements(By.tagName("td")).get(2).getText().equals("Tokyo"));
            });

            // Print filtered results + validate
            rows = driver.findElements(By.cssSelector("#example tbody tr"));
            System.out.println("\n--- FILTERED RESULTS ---");
            boolean valid = true;
            for (WebElement row : rows) {
                List<String> data = row.findElements(By.tagName("td")).stream()
                        .map(WebElement::getText).collect(Collectors.toList());
                System.out.println(data);
                if (!data.get(2).equals("Tokyo")) valid = false;
            }
            System.out.println(valid ? "PASS: Filtering is correct." : "FAIL: Filtering is incorrect.");

        } finally {
            driver.quit();
        }
    }
}