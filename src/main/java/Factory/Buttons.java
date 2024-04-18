package Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;


public class Buttons {
    private final WebDriver webDriver;

    @FindBy(xpath = "//i[contains(@class, 'far fa-heart fa-2x')]")
    private WebElement likeButton;

    @FindBy(xpath = "//i[contains(@class, 'far') and contains(@class, 'fa-heart') and contains(@class, 'liked')]")
    private WebElement likedButtonState;

    public Buttons(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLikeButtonOnMostRecentPost() {
        try {
            Thread.sleep(3000); // Sleep for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));


        wait.until(ExpectedConditions.elementToBeClickable(likeButton));

        likeButton.click();
    }
}


