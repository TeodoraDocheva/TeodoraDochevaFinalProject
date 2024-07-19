package Factory;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PostPage {

    private final WebDriver webDriver;
    private final WebDriverWait wait;

   // @FindBy(xpath = "//h3[text()='Post a picture to share with your awesome followers']")  //по-прости локатори тук и на лайка- прегледай и другите
   @FindBy(xpath = "//*[@class='text-center']")
   private WebElement newPostTitle;
    @FindBy(xpath = "//input[@class='form-control input-lg'][@type='text']")
    private WebElement uploadPictureText;
    @FindBy(name = "caption")
    private WebElement postCaption;
    @FindBy(id = "create-post")
    private WebElement createPostButton;
    @FindBy(xpath = "//*[@class='form-group']/input[@type='file']")
    private WebElement uploadFile;
    @FindBy(css = "label.custom-control-label")
    private WebElement radioButton;

    public PostPage(WebDriver driver) {
        this.webDriver = driver;
        this.wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        PageFactory.initElements(webDriver, this);
    }

    public boolean isNewPostLoaded() {
        wait.until(ExpectedConditions.visibilityOf(newPostTitle));
        return newPostTitle.isDisplayed();
    }

    public void uploadPicture(File file) {
        uploadFile.sendKeys(file.getAbsolutePath());
    }

    public boolean isImageUploaded(String fileName) {
        String actualText = uploadPictureText.getAttribute("placeholder");
        return actualText.equals(fileName);
    }

    public String uploadedImageText() {
        return uploadPictureText.getAttribute("placeholder");
    }

    public void typePostCaption(String text) {
        postCaption.sendKeys(text);
    }

    public void clickPrivatePost() {
        wait.until(ExpectedConditions.elementToBeClickable(radioButton));
        radioButton.click();
    }

    public boolean IsButtonPublic() {
        return radioButton.getAttribute("class").contains("active");
    }

    public void SetRadioButtonValue(String toggleValue) {
        switch (toggleValue.toLowerCase()) {
            case "public":
                if (!IsButtonPublic()) {
                    radioButton.click();
                }
                break;
            case "private":
                if (IsButtonPublic()) {
                    radioButton.click();
                }
                break;
            default:
                throw new IllegalArgumentException(toggleValue + " toggle value is not supported");
        }
    }

    public void clickCreatePost() {
        createPostButton.isEnabled();
        createPostButton.click();
    }
}