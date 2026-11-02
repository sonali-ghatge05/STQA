import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;

public class objectCounter {
    public static void main (String[] args) throws Exception{
        WebDriver driver = new FirefoxDriver();

        driver.get("file:///C:/selenium/object.html");

        int link = driver.findElements(By.tagName("a")).size();
        int images = driver.findElements(By.tagName("img")).size();
        int buttons = driver.findElements(By.tagName("button")).size();
        int inputs = driver.findElements(By.tagName("input")).size();

        int total = link+images+buttons+inputs;

        System.out.println("=======================================");
        System.out.println("======   WEB PAGE OBJECT COUNT  =======");
        System.out.println("=======================================");

        System.out.println("Number of links: "+ link);
        System.out.println("Number of images: "+ images);
        System.out.println("Number of buttons: "+ buttons);
        System.out.println("Number of inputs: " +inputs);

        System.out.println("=======================================");
        System.out.println("======   TOTAL   =======");
        System.out.println("=======================================");
        System.out.println("Total Number Of Objects: "+ total);

        driver.quit();
    }
}
