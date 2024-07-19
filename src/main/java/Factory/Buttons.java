package Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;


public class Buttons {
    private final WebDriver webDriver;
    private final WebDriverWait wait;

    // //i[@class='like far fa-heart fa-2x']
    //(xpath = "//*[contains(@class, 'like far fa-heart fa-2x')]")
    @FindBy(xpath = "//*[contains(@class, 'fa-heart fa-2x')]")
    private WebElement likeButton;

    @FindBy(xpath = "//i[contains(@class, 'thumbs-down')]")
    private WebElement dislikeButton;

    @FindBy(xpath = "//*[contains(@class, 'private')]")
    private WebElement privatePosts;

    @FindBy(xpath = "//*[contains(@class, 'private') and contains(@class, 'active')]")
    private WebElement privatePostsSelected;

    @FindBy(xpath = "//i[contains(@class, 'fa-trash-alt')]")
    private WebElement deletePost;

    @FindBy(xpath = "//button[contains(text(), 'Yes')]")
    private WebElement deleteYes;

    public Buttons(WebDriver driver) {
        this.webDriver = driver;
        this.wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        PageFactory.initElements(webDriver, this);
    }

    public void clickLikeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(likeButton));
        likeButton.click();
    }

    public void clickPrivatePosts() {
        wait.until(ExpectedConditions.elementToBeClickable(privatePosts));
        privatePosts.click();
    }
    public boolean isPrivatePostsSelected(){
        return privatePostsSelected.isEnabled();
    }

    //dislikeButton
    public void clickDislikeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(dislikeButton));
        dislikeButton.click();
    }

    //deletePost
    public void clickDeleteButton() {
        wait.until(ExpectedConditions.elementToBeClickable(deletePost));
        deletePost.click();
    }
    //deleteConfirmYes
    public void clickDeleteConfirmYes() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteYes));
        deleteYes.click();
    }

}