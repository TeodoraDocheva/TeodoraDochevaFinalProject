package Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    public static final String HOME_URL = "http://training.skillo-bg.com:4200/posts/all";

    private final WebDriver webDriver;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.webDriver = driver;
        this.wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
    }

    public void navigateTo() {
        this.webDriver.get(HOME_URL);
    }

    public boolean isURLLoaded() {
        return wait.until(ExpectedConditions.urlToBe(HOME_URL));
    }
}
