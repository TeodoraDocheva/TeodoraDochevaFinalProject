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
    @FindBy(xpath = "//h3[text()='Post a picture to share with your awesome followers']")
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
        PageFactory.initElements(webDriver, this);
    }

    public boolean isNewPostLoaded() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(newPostTitle));
        return newPostTitle.isDisplayed();
    }

    public void uploadPicture(File file) {
        ////*[@class='form-group']//div/input[@type='file'] - hidden
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        uploadFile.sendKeys(file.getAbsolutePath());
    }

    public boolean isImageUploaded(String fileName) {
        String actualText = uploadPictureText.getAttribute("placeholder");
        if (actualText.equals(fileName)) {
            return true;
        }
        return false;
    }

    public String uploadedImageText() {
        return uploadPictureText.getAttribute("placeholder");
    }

    public void typePostCaption(String text) {
        postCaption.sendKeys(text);
    }

    public void clickPrivatePost() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(radioButton));
        radioButton.click();

    }

    public boolean IsButtonPublic(){
        return radioButton.getAttribute("class").contains("active");
    }

    public void SetRadioButtonValue(String toggleValue){
        switch(toggleValue.toLowerCase()) {
            case "public":
                if(IsButtonPublic() == false){
                    radioButton.click();
                }
                break;
            case "private":
                if(IsButtonPublic()){
                    radioButton.click();
                }
                break;
            default:
                throw new InvalidArgumentException(toggleValue + " toggle value is not supported");
        }
    }
    public void clickCreatePost() throws InterruptedException {
        Thread.sleep(4000); // Sleep for 4 seconds
        createPostButton.isEnabled();
        createPostButton.click();
    }
}
