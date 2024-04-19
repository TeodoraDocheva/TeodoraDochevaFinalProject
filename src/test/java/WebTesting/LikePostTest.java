package WebTesting;

import Factory.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class LikePostTest extends TestObject {

    @DataProvider(name = "getUser")
    public Object[][] getUsers() {
        return new Object[][]{
                {"t@k.com", "t@k.com"},
        };
    }
    @Test(dataProvider = "getUser")
    public void LikeButton(String username, String password) throws InterruptedException {

        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ToastContainer toastContainer = new ToastContainer(webDriver);
        Buttons buttons = new Buttons(webDriver);

        homePage.navigateTo();
        header.clickLogin();
        loginPage.completeSingIn(username, password);
        buttons.clickLikeButtonOnMostRecentPost();
        String toastMessage = toastContainer.getToastMessage();
        Assert.assertEquals(toastMessage, "Post liked");

    }
}

