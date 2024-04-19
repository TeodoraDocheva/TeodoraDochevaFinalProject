package Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ToastContainer {

    private final WebDriver webDriver;

    private final WebDriverWait wait;
    @FindBy(xpath = "//div[@id='toast-container']")
    private WebElement toastContainer;

    @FindBy (xpath = "//div[@role='alertdialog']")
    private WebElement toastMessage;

    public ToastContainer (WebDriver driver){
        this.webDriver = driver;
        this.wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(20));
        PageFactory.initElements(this.webDriver, this);
    }

    public String getToastMessage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(toastContainer));
        Thread.sleep(500); // Sleep for 3 seconds
        return toastMessage.getText();
    }

}
