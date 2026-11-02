import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;

public class dataExtraction {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        try {
            driver.get("file:///C:/Selenium/student_marks.html");

            // Get all rows (including header)
            List<WebElement> rows = driver.findElements(
                    By.xpath("//table[@id='studentTable']//tr"));

            int total = 0;
            int above60 = 0;

            System.out.println("==============================================");
            System.out.println("    STUDENT SCORE ANALYSIS");
            System.out.println("==============================================");

            // Start from index 1 to skip the header row
            for (int i = 1; i < rows.size(); i++) {
                List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

                String name = cols.get(0).getText();
                int java = Integer.parseInt(cols.get(1).getText());
                int dbms = Integer.parseInt(cols.get(2).getText());

                total++;
                boolean isAbove = (java > 60 || dbms > 60);

                if (isAbove) {
                    above60++;
                }

                System.out.println("Student: " + name +
                        " | Java: " + java +
                        " | DBMS: " + dbms +
                        " | Above 60: " + isAbove);
            }

            double percentage = (total > 0) ? (above60 * 100.0) / total : 0;

            System.out.println("==============================================");
            System.out.println("Total Students        : " + total);
            System.out.println("Students Above 60     : " + above60);
            System.out.printf("Percentage            : %.2f%%%n", percentage);
            System.out.println("==============================================");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}