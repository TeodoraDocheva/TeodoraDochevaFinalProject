package WebTesting;

import Factory.Header;
import Factory.HomePage;
import Factory.LoginPage;
import Factory.ProfilePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends TestObject {

    @DataProvider(name = "getUser")
    public Object[][] getUsers() {
        return new Object[][]{
                {"t@k.com", "t@k.com", "5563"},
        };
    }
    @DataProvider(name = "wrongUser")
    public Object[][] wrongUsers() {
        return new Object[][]{
                {"t@kk.com", "t@k.com"},
        };
    }

    @DataProvider(name = "wrongPassword")
    public Object[][] wrongPassword() {
        return new Object[][]{
                {"t@kk.com", "t@k.comm"},
        };
    }

    @Test(dataProvider = "getUser")
    public void loginTest(String username, String password, String userID) {

        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isURLLoaded(), "Home page is not loaded");

        header.clickLogin();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password);
        loginPage.clickRememberMe();
        Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me checkbox is not checked.");

        loginPage.clickSignIn();
        header.clickProfile();

        Assert.assertTrue(profilePage.isUrlLoaded(userID), "Current page in not profile page for " + userID + " user");
        Assert.assertTrue(loginPage.isSuccessLoginMessageShown(), "Success Login message is not shown");

    }

    @Test(dataProvider = "wrongUser")
    public void wrongUserLoginTest (String username, String password) {
        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);

        homePage.navigateTo();
        header.clickLogin();
        header.clickLogin();
        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password);
        loginPage.clickRememberMe();
        loginPage.clickSignIn();
        Assert.assertTrue(loginPage.isUserNotFoundShown(), "User not found message is not shown");
        Assert.assertTrue(homePage.isURLLoaded(), "Home page is not loaded");
        // home + login
    }

    @Test(dataProvider = "wrongPassword")
    public void wrongPasswordTest (String username, String password) {
        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);

        homePage.navigateTo();
        header.clickLogin();
        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password);
        loginPage.clickRememberMe();
        loginPage.clickSignIn();
        Assert.assertTrue(loginPage.isUserNotFoundShown(), "User not found message is not shown");
        Assert.assertTrue(homePage.isURLLoaded(), "Home page is not loaded");

    }
}

