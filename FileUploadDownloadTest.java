import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;

public class FileUploadDownloadTest {
    public static void main(String[] args) throws Exception {

        String basePath = "C:\\selenium";
        String downloadPath = basePath + "\\downloads";

        // Create downloads folder
        new File(downloadPath).mkdirs();


        // Chrome settings
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs",
                java.util.Map.of(
                        "download.default_directory", downloadPath,
                        "download.prompt_for_download", false,
                        "download.directory_upgrade", true
                )
        );

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Open page
        driver.get("http://localhost:8000/file_upload_download.html");

        // Upload
        String filePath = basePath + "\\upload.txt";
        driver.findElement(By.id("fileUpload")).sendKeys(filePath);
        driver.findElement(By.xpath("//button[text()='Upload']")).click();
        Thread.sleep(1000);

        String status = driver.findElement(By.id("uploadStatus")).getText();
        System.out.println("Upload Status: " + status);

        // Download
        driver.findElement(By.id("downloadLink")).click();
        System.out.println("Download started...");
        Thread.sleep(4000);

        // Check file
        File downloadedFile = new File(downloadPath + "\\sample.txt");
        if (downloadedFile.exists()) {
            System.out.println("PASS: File downloaded successfully.");
            System.out.println("File Path: " + downloadedFile.getAbsolutePath());
        } else {
            System.out.println("FAIL: Downloaded file not found.");
        }

        driver.quit();
        System.out.println("Test execution completed.");
    }
}