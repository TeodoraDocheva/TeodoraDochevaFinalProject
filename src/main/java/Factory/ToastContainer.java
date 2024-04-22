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

    public String getToastMessage()  {
        wait.until(ExpectedConditions.visibilityOf(toastContainer));
        return toastMessage.getText();
    }

    public boolean isToastContainerHidden () {
        return wait.until(ExpectedConditions.invisibilityOf(toastMessage));
    }
    public boolean waitForToastMessage(String message) {
        return wait.until(ExpectedConditions.textToBePresentInElement(toastMessage, message));
    }
}
