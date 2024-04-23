package Factory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private final WebDriver webDriver;
    private final WebDriverWait wait;
    @FindBy(id = "nav-link-login")
    private WebElement loginLink;
    @FindBy(id = "nav-link-profile")
    private WebElement profilePageLink;
    @FindBy(id = "nav-link-new-post")
    private WebElement newPostLink;
    @FindBy(xpath = "//i[@class='fas fa-sign-out-alt fa-lg']")
    private WebElement signOutButton;


    public Header(WebDriver driver){
        this.webDriver = driver;
        this.wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }


    public void clickLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        loginLink.click();
    }
    public void clickNewPost(){
        wait.until(ExpectedConditions.elementToBeClickable(newPostLink));
        newPostLink.click();
    }

    public void clickProfile(){
        wait.until(ExpectedConditions.elementToBeClickable(profilePageLink));
        profilePageLink.click();
    }

    public void clickLogOut() {
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(signOutButton));
        logoutButton.click();
    }
}