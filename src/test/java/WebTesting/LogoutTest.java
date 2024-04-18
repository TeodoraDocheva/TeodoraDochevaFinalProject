package WebTesting;

import Factory.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LogoutTest extends TestObject {

    @DataProvider(name = "getUser")
    public Object[][] getUsers() {
        return new Object[][]{
                {"t@k.com", "t@k.com"},
        };
    }
    @Test(dataProvider = "getUser")
    public void logoutTest(String username, String password) {

        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ToastContainer toastContainer = new ToastContainer(webDriver);

        homePage.navigateTo();

        header.clickLogin();

        loginPage.completeSingIn(username, password);

        header.clickLogOut();
        String toastMessage = toastContainer.getToastMessage();
        Assert.assertEquals(toastMessage, "Successful logout!");

        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page in not login page ");
    }
}
