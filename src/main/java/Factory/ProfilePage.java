package Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProfilePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4200/users/";
    private final WebDriver webDriver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//div[@class='container']//app-post")
    private List<WebElement> allPosts;
    public ProfilePage(WebDriver driver) {
        this.webDriver = driver;
        this.wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        PageFactory.initElements(webDriver, this);
    }

    public boolean isUrlLoaded(String userId) {
        return wait.until(ExpectedConditions.urlToBe(PAGE_URL + userId));
    }

    public WebElement mostRecentPost() {
        if (!allPosts.isEmpty()) {
            return allPosts.get(allPosts.size() - 1);
        } else {
            return null;
        }
    }
    public void selectLastPost() {
        mostRecentPost().click();
    }

}