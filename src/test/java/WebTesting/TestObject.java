package WebTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class TestObject {
    public static final String TEST_RESOURCES_DIR = "src\\test\\resources\\";
    public static final String SCREENSHOTS_DIR = TEST_RESOURCES_DIR.concat("screenshots\\");
    public static final String REPORTS_DIR = TEST_RESOURCES_DIR.concat("reports\\");
    protected WebDriver webDriver;
    @BeforeSuite
    protected final void setupTestSuite() throws  IOException{
        cleanDirectory(SCREENSHOTS_DIR);
        cleanDirectory(REPORTS_DIR);
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    protected final void setUpTest(){
        this.webDriver = new ChromeDriver(configChromeOptions());
        this.webDriver.manage().window().maximize();
        this.webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    protected final void tearDownTest(ITestResult testResult){
        takeScreenshot(testResult);
        quitDriver();
    }
    @AfterSuite
    private void quitDriver() {
        if (this.webDriver != null){
            this.webDriver.quit();
        }
    }

    protected WebDriver getWebDriver(){
        return webDriver;
    }

    private ChromeOptions configChromeOptions(){
       Map<String, Object> prefs = new HashMap<>();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("disable-popup-blocking");

        return chromeOptions;
    }

    private void cleanDirectory(String directoryPath) throws IOException{
        File directory = new File(directoryPath);
        Assert.assertTrue(directory.isDirectory(), "Invalid directory!");

        FileUtils.cleanDirectory(directory);
        String[] fileList = directory.list();
        if (fileList != null && fileList.length == 0){
            System.out.printf("All file are deleted in Directory: %s%n", directoryPath);
        }else {
            System.out.printf("Unable to delete the files in Directory: %s%n", directoryPath);
        }
    }

    private void takeScreenshot(ITestResult testResult){
        if(ITestResult.FAILURE == testResult.getStatus()){
            try{
                TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
                File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                String testName = testResult.getName();
                FileUtils.copyFile(screenshot, new File(SCREENSHOTS_DIR.concat(testName).concat(".jpg")));
            }catch (IOException e){
                System.out.println("Unable to create a screenshot file: " + e.getMessage());
            }
        }
    }

}
