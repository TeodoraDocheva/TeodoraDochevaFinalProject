package WebTesting;

import Factory.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class PostTest extends TestObject {

    ChromeDriver webDriver;
    protected WebDriver getWebDriver() {
        return webDriver;
    }

    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        File postPicture = new File("src\\test\\resources\\upload\\upload.jpg");
        String caption = "Testing upload file";
        return new Object[][]{
                {"t@k.com","t@k.com", "5563", postPicture, caption},
        };
    }
    @Test(dataProvider = "getUser")
    public void testCreatePublicPost(String username, String password, String userId, File postPicture, String caption) {
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        PostPage postPage = new PostPage(webDriver);
        ToastContainer toastContainer = new ToastContainer(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");
        loginPage.completeSingIn(username,password);

        String toastMessage = toastContainer.getToastMessage();
        Assert.assertEquals(toastMessage, "Successful login!");

        Assert.assertTrue(toastContainer.isToastContainerHidden(), "Toast message does not disappear.");;

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page in not profile page for " + userId + " user");

        header.clickNewPost();
        Assert.assertTrue(postPage.isNewPostLoaded(), "The new post form is not loaded!");

        postPage.uploadPicture(postPicture);
        String actualImageText = postPage.uploadedImageText();
        Assert.assertTrue(postPage.isImageUploaded("upload.jpg"), "Image is not uploaded!");
        Assert.assertEquals(actualImageText, "upload.jpg", "Incorrect image is uploaded!");

        postPage.typePostCaption(caption);
        Assert.assertTrue(toastContainer.isToastContainerHidden(), "Toast message does not disappear.");
        postPage.clickCreatePost();

        toastMessage = toastContainer.getToastMessage();
        Assert.assertEquals(toastMessage, "Post created!");

        Assert.assertTrue(toastContainer.isToastContainerHidden(), "Toast message does not disappear.");

        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page in not profile page for " + userId + " user");
        }

    @Test(dataProvider = "getUser")
    public void testCreatePrivatePost(String username, String password, String userId, File postPicture, String caption) {
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        PostPage postPage = new PostPage(webDriver);
        ToastContainer toastContainer = new ToastContainer(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");
        loginPage.completeSingIn(username,password);

        String toastMessage = toastContainer.getToastMessage();
        Assert.assertEquals(toastMessage, "Successful login!");

        Assert.assertTrue(toastContainer.isToastContainerHidden(), "Toast message does not disappear.");

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page in not profile page for " + userId + " user");

        header.clickNewPost();
        Assert.assertTrue(postPage.isNewPostLoaded(), "The new post form is not loaded!");

        postPage.uploadPicture(postPicture);
        String actualImageText = postPage.uploadedImageText();
        Assert.assertTrue(postPage.isImageUploaded("upload.jpg"), "Image is not uploaded!");
        Assert.assertEquals(actualImageText, "upload.jpg", "Incorrect image is uploaded!");

        postPage.typePostCaption(caption);

        postPage.SetRadioButtonValue("public");
        Assert.assertTrue(postPage.IsButtonPublic(), "Post is not private");
        postPage.clickPrivatePost();

        postPage.clickCreatePost();

        toastMessage = toastContainer.getToastMessage();
        Assert.assertEquals(toastMessage, "Post created!");

        Assert.assertTrue(toastContainer.isToastContainerHidden(), "Toast message does not disappear.");

        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page in not profile page for " + userId + " user");
        }
    }

