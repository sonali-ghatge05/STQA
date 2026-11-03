
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.List;

public class apachePOI {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();

        String file = "Students.xlsx";

        // Open Excel file
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        fis.close();

        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter formatter = new DataFormatter();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        try {

            // Open student web application
            driver.get("https://demoqa.com/webtables");

            // Read 10 student records from Excel
            for (int i = 1; i <= 10; i++) {

                Row row = sheet.getRow(i);

                String firstName = formatter.formatCellValue(row.getCell(0));
                String lastName = formatter.formatCellValue(row.getCell(1));
                String email = formatter.formatCellValue(row.getCell(2));
                String age = formatter.formatCellValue(row.getCell(3));
                String salary = formatter.formatCellValue(row.getCell(4));
                String department = formatter.formatCellValue(row.getCell(5));

                // Clear search box
                WebElement search =
                        driver.findElement(By.id("searchBox"));

                search.clear();

                // Click Add button
                driver.findElement(By.id("addNewRecordButton")).click();

                // Enter student details
                driver.findElement(By.id("firstName")).sendKeys(firstName);
                driver.findElement(By.id("lastName")).sendKeys(lastName);
                driver.findElement(By.id("userEmail")).sendKeys(email);
                driver.findElement(By.id("age")).sendKeys(age);
                driver.findElement(By.id("salary")).sendKeys(salary);
                driver.findElement(By.id("department")).sendKeys(department);

                // Save student record
                driver.findElement(By.id("submit")).click();

                // Search for the added student
                search = driver.findElement(By.id("searchBox"));
                search.clear();
                search.sendKeys(email);

                // Verify student details
                final String mail = email;

                wait.until(d -> {
                    List<WebElement> rows =
                            d.findElements(By.cssSelector(".rt-tr-group"));

                    return rows.stream().anyMatch(r ->
                            r.getText().contains(mail));
                });

                List<WebElement> tableRows =
                        driver.findElements(By.cssSelector(".rt-tr-group"));

                boolean found = false;

                for (WebElement tableRow : tableRows) {

                    String data = tableRow.getText();

                    if (data.contains(email)
                            && data.contains(firstName)
                            && data.contains(lastName)
                            && data.contains(department)) {

                        found = true;
                        break;
                    }
                }

                // Write result into Excel
                if (found) {
                    row.createCell(6).setCellValue("PASS");
                    System.out.println(firstName + " : PASS");
                } else {
                    row.createCell(6).setCellValue("FAIL");
                    System.out.println(firstName + " : FAIL");
                }
            }

        } finally {

            // Save updated Excel file
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);

            fos.close();
            workbook.close();

            driver.quit();
        }
    }
}
