package Factory;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver webDriver;

    public static final String PAGE_URL_REG = "http://training.skillo-bg.com:4200/users/register";

    @FindBy(id = "nav-link-login")
    private WebElement loginLink;
    @FindBy(xpath = "//form//p//a[text()='Register']")
    private WebElement registrationLink; //
    @FindBy(xpath = "//*[@name='username']")
    private WebElement usernameTextField;

    @FindBy(xpath = "//*[@type='email']")
    private WebElement emailTextField;

    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement passwordTextField;

    @FindBy(id = "defaultRegisterPhonePassword")
    private WebElement confirmPasswordTextField;

    @FindBy(id = "sign-in-button")
    private WebElement registerButton;


    public RegisterPage(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }

    public String generateRandomString() {
        return RandomStringUtils.randomAlphanumeric(4);
    }
    public void enterUsername(String username) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(usernameTextField));
        usernameTextField.sendKeys(username);
    }

    public void enterEmail(String email) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(emailTextField));
        emailTextField.sendKeys(email);
    }
    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(passwordTextField));
        passwordTextField.sendKeys(password);
    }

    public void confirmPassword(String password) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordTextField));
        confirmPasswordTextField.sendKeys(password);
    }
        public void registrationButton () {
            registerButton.click();
        }


        public boolean isRegPageLoaded () {
            WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
            return wait.until(ExpectedConditions.urlToBe(PAGE_URL_REG));
        }

        public void navigateToReg () {
            this.webDriver.get(PAGE_URL_REG);
        }

    }

