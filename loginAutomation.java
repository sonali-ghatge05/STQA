import  java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class loginAutomation {
    public static void main(String[] args){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        try {
            driver.get("https://www.saucedemo.com");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            String expectedUrl = "https://www.saucedemo.com/inventory.html";
            String actualURl = driver.getCurrentUrl();
            System.out.println("URL Validation: "+
                    (expectedUrl.equals(actualURl)? "PASS" : "FAIL"));
            if (!expectedUrl.equals(actualURl)){
                System.out.println("Expected URL: "+ expectedUrl);
                System.out.println("Actual URL: " + actualURl);
            };

            String title = driver.findElement(By.className("title")).getText();
            System.out.println("Page title validation: "+
                    ("Products".equals(title)? "PASS" : "FAIL"));
            if (!"Products".equals(title)){
                System.out.println("Expected Title: "+"Products");
                System.out.println("Actual title is:"+ title);
            }

            driver.findElement(By.id("react-burger-menu-btn")).click();
            Thread.sleep(1000);
            boolean logoutVisibility = driver.findElement(By.id("logout_sidebar_link")).isDisplayed();
            System.out.println("Logout validation: "+
                    (logoutVisibility ? "PASS":"FAIL"));

            System.out.println("--------------------------------");
            System.out.println("LOGIN TEST COMPLETED");
            System.out.println("--------------------------------");
        } catch (Exception e) {
            System.out.println("--------------------------------");
            System.out.println("LOGIN TEST FAILED");
            System.out.println("--------------------------------");
        }
        finally {
            driver.quit();
        }
    }

}