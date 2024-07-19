package WebTesting;

import Factory.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class ActionsFromProfilePage extends TestObject {

    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        File postPicture = new File("src\\test\\resources\\upload\\upload.jpg");
        String caption = "Testing upload file";
        return new Object[][]{
                {"t@k.com","t@k.com", "5563", postPicture, caption},
        };
    }

    @Test(dataProvider = "getUser")
    public void likeFromProfilePage(String username, String password, String userId, File postPicture, String caption) {
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        PostPage postPage = new PostPage(webDriver);
        ToastContainer toastContainer = new ToastContainer(webDriver);
        Buttons buttons = new Buttons(webDriver);

        // Log in
        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");
        loginPage.completeSingIn(username, password);

        // Validate log in
        String toastMessage = toastContainer.getToastMessage();
        Assert.assertEquals(toastMessage, "Successful login!");
        Assert.assertTrue(toastContainer.isToastContainerHidden(), "Toast message does not disappear.");

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page is not the profile page for user " + userId);

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
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page is not the profile page for user " + userId);

        buttons.clickPrivatePosts();
        Assert.assertTrue(buttons.isPrivatePostsSelected(), "Private posts are not loaded");

        profilePage.selectLastPost();


        buttons.clickLikeButton();
        Assert.assertTrue(toastContainer.waitForToastMessage("Post liked"), "Toast message 'Post liked' did not appear.");
       // toastMessage = toastContainer.getToastMessage();
       // Assert.assertEquals(toastMessage, "Post liked");
        Assert.assertTrue(toastContainer.isToastContainerHidden(), "Toast message does not disappear.");

        buttons.clickDislikeButton();
       //Assert.assertTrue(toastContainer.waitForToastMessage("Post disliked"), "Toast message 'Post disliked' did not appear.");
        toastMessage = toastContainer.getToastMessage();
        Assert.assertEquals(toastMessage, "Post disliked");
        Assert.assertTrue(toastContainer.isToastContainerHidden(), "Toast message does not disappear.");


        buttons.clickDeleteButton();
        buttons.clickDeleteConfirmYes();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page is not the profile page for user " + userId);
    //    Assert.assertTrue(toastContainer.waitForToastMessage("Post deleted!"), "Toast message 'Post deleted!' did not appear.");
 //      Assert.assertTrue(toastContainer.isToastContainerHidden(), "Toast message does not disappear.");
        toastMessage = toastContainer.getToastMessage();

        System.out.println(toastMessage);
        Assert.assertTrue(toastContainer.waitForToastMessage("Post deleted!"), "Toast message 'Post deleted!' did not appear.");
        //   Assert.assertEquals(toastMessage, "Post deleted!");
        //    Assert.assertTrue(toastContainer.isToastContainerHidden(), "Toast message does not disappear.");

    }
}