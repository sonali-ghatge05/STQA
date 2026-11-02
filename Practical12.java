import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URI;

public class Practical12 {
    public static void main(String[] args) throws Exception {
        test("Chrome", new ChromeOptions());
        test("Firefox", new FirefoxOptions());
        test("Edge", new EdgeOptions());
    }

    static void test(String browser, Capabilities options) throws Exception {
        long start = System.currentTimeMillis();
        WebDriver driver = new RemoteWebDriver(URI.create("http://localhost:4444").toURL(), options);

        try {
            driver.manage().window().maximize();
            driver.get("https://www.google.com");
            System.out.println("\nBrowser: " + browser);
            System.out.println("Title: " + driver.getTitle());
            System.out.println("URL: " + driver.getCurrentUrl());
            System.out.println("Execution Time: " + (System.currentTimeMillis() - start) + " ms");
            System.out.println("Result: PASS");
        } catch (Exception e) {
            System.out.println("Result: FAIL\n" + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}