
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class POM {

    WebDriver driver;
    LoginPage loginPage;

    // Page Object Model class
    class LoginPage {

        WebDriver driver;

        // Locators
        By username = By.id("user-name");
        By password = By.id("password");
        By loginButton = By.id("login-button");

        // Constructor
        LoginPage(WebDriver driver) {
            this.driver = driver;
        }

        // Methods
        public void enterUsername(String user) {
            driver.findElement(username).sendKeys(user);
        }

        public void enterPassword(String pass) {
            driver.findElement(password).sendKeys(pass);
        }

        public void clickLogin() {
            driver.findElement(loginButton).click();
        }

        public void login(String user, String pass) {
            enterUsername(user);
            enterPassword(pass);
            clickLogin();
        }
    }

    // Open browser before test
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
    }

    // Test case
    @Test
    public void validLoginTest() {

        loginPage.login("standard_user", "secret_sauce");

        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains("inventory"));

        System.out.println("Login Successful");
    }

    // Close browser after test
    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
