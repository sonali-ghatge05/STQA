
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class htmlReport {

    WebDriver driver;

    // Runs before every test case
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    // Test 1: Verify page title
    @Test(priority = 1, groups = {"smoke"})
    public void verifyTitle() {
        String title = driver.getTitle();

        Assert.assertEquals(title, "Swag Labs");

        System.out.println("Title test passed");
    }

    // Test 2: Verify valid login
    @Test(priority = 2, groups = {"smoke"})
    public void validLogin() {
        driver.findElement(By.id("user-name"))
                .sendKeys("standard_user");

        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        driver.findElement(By.id("login-button"))
                .click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory")
        );

        System.out.println("Valid login test passed");
    }

    // Test 3: Verify invalid login
    @Test(priority = 3, groups = {"regression"})
    public void invalidLogin() {
        driver.findElement(By.id("user-name"))
                .sendKeys("wrong_user");

        driver.findElement(By.id("password"))
                .sendKeys("wrong_password");

        driver.findElement(By.id("login-button"))
                .click();

        String errorMessage = driver.findElement(
                By.cssSelector("[data-test='error']")
        ).getText();

        Assert.assertTrue(
                errorMessage.contains("Username and password do not match")
        );

        System.out.println("Invalid login test passed");
    }

    // Runs after every test case
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
