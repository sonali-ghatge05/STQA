import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class dropdown {
    public static void main(String[] args){
        WebDriver driver = new ChromeDriver();

        try{
            driver.get("https://the-internet.herokuapp.com/dropdown");

            Select select = new Select(driver.findElement(By.id("dropdown")));
            System.out.println("Total options" + select.getOptions().size());

            String defaultValue = select.getFirstSelectedOption().getText();
            System.out.println("Default selected value:"+ defaultValue);
            System.out.println(defaultValue.equals("Please select an option")
                                ? "Defalut option validated successfully"
                                : "Defalut option validated unsuccessfull");

            select.selectByVisibleText("Option 1");
            String selectedValue = select.getFirstSelectedOption().getText();
            System.out.println("After selection: "+ selectedValue);
            System.out.println(selectedValue.equals("Option 1") ? "Selection validation passed" : "selection validation failed");
        }
        finally {
            driver.quit();
        }
    }
}