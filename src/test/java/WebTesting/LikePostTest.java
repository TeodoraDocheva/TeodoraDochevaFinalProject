package WebTesting;

import Factory.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LikePostTest extends TestObject {

    @DataProvider(name = "getUser")
    public Object[][] getUsers() {
        return new Object[][]{
                {"t@k.com", "t@k.com"},
        };
    }
    @Test(dataProvider = "getUser")
    public void LikeButton(String username, String password)  {

        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ToastContainer toastContainer = new ToastContainer(webDriver);
        LikeButton buttons = new LikeButton(webDriver);

        homePage.navigateTo();
        header.clickLogin();
        loginPage.completeSingIn(username, password);

        String toastMessage = toastContainer.getToastMessage();
        Assert.assertEquals(toastMessage, "Successful login!");
        Assert.assertTrue(toastContainer.isToastContainerHidden(), "Toast message does not disappear.");

        buttons.clickLikeButtonOnMostRecentPost();
        Assert.assertTrue(toastContainer.waitForToastMessage("Post liked"), "Toast message 'Post liked' did not appear.");
        Assert.assertTrue(toastContainer.isToastContainerHidden(), "Toast message does not disappear.");

    }
}

