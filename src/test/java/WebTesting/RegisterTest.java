package WebTesting;

import Factory.*;

import Factory.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends TestObject {

    @Test
    public void registerSuccessTest() {

        HomePage homePage = new HomePage(webDriver);
        RegisterPage registerPage = new RegisterPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);

        homePage.navigateTo();
        homePage.isURLLoaded();
        Assert.assertTrue(homePage.isURLLoaded(), "Home page is not loaded");

        loginPage.navigateTo();
        loginPage.isUrlLoaded();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Login page is not loaded");

        registerPage.navigateToReg();
        Assert.assertTrue(registerPage.isRegPageLoaded(),"Registration page is not loaded");

        String randomString = registerPage.generateRandomString();

        registerPage.enterUsername(randomString);
        registerPage.enterEmail(randomString + "@gmail.com");
        registerPage.enterPassword(randomString + "123");
        registerPage.confirmPassword(randomString + "123");

        registerPage.registrationButton();
        Assert.assertTrue(homePage.isURLLoaded(), "User is not successfully registered");

    }
}
