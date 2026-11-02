import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class practical4SeleniumGrid {
    public static void main(String[] args) throws Exception{
        String browser = "chrome";

        WebDriver driver;

        if(browser.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();

            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444"),
                    options);
        }
        else {
            FirefoxOptions options = new FirefoxOptions();

            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444"),
                    options);
        }
        driver.get("https://google.com");

        System.out.println("Browser: "+ browser);
        System.out.println("Title: "+ driver+ driver.getTitle());

        driver.quit();
    }
}
