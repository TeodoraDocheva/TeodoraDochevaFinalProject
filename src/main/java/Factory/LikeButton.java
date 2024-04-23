package Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;


public class LikeButton {
    private final WebDriver webDriver;

    @FindBy(xpath = "//i[contains(@class, 'far fa-heart fa-2x')]")
    private WebElement likeButton;

    @FindBy(xpath = "//i[contains(@class, 'far') and contains(@class, 'fa-heart') and contains(@class, 'liked')]")
    private WebElement likedButtonState;

    public LikeButton(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLikeButtonOnMostRecentPost() {

        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(likeButton));

        likeButton.click();
    }
}