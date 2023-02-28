package com.hydl.project;

import io.cucumber.java.Before;
import io.cucumber.java.After;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class loginSteps {

    public WebDriver driver;
    public static String url = "http://www.hudl.com";
    public static final String PAGE_TITLE = "Hudl • Connected solutions for high-performance video and data analysis";
    public static final String PAGE_ONE_HEADER = "Log in";
    public static final String PAGE_TWO_HEADER = "Sign up";
    public static final String ERROR_MESSAGE = "We didn't recognize that email and/or password.Need help?";
    public static final String HOME_PAGE = "Home";

    @FindBy(xpath = "//a[@class='mainnav__btn mainnav__btn--primary']")
    WebElement logIn;
    @FindBy(xpath = "//a[@class='uni-link uni-link--default styles_signUpLink_1CPc8TbK9yDyBdJiSpUOZV']")
    WebElement signUp;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(xpath = "//button[@id='logIn']")
    WebElement logInBtn;

    @FindBy(xpath = "//p[@class='uni-text']")
    WebElement textMessage;

    @FindBy(xpath="//span[normalize-space()='Home']")
    WebElement homePage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webDriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    @Given("^User navigates to hudl website$")
    public void userNavigatesToHudlWebsite() {
        assertEquals("Page Title ", PAGE_TITLE, driver.getTitle());
        assertEquals("Log In Header shows ", PAGE_ONE_HEADER, logIn.getText());
    }

    @And("^User clicks on LogIn button$")
    public void userClicksOnLogInButton() {
        assertTrue(logIn.isDisplayed());
        logIn.click();
    }

    @And("^User should see signIn button$")
    public void userShouldSeeSignInButton() {
        assertEquals("Sign Up Header shows", PAGE_TWO_HEADER, signUp.getText());
    }

    @And("User enters valid {string}, password")
    public void userEntersValidPassword(String email) {
        emailField.sendKeys(email);
        passwordField.sendKeys("HappyNewYear23");
        assertTrue(logInBtn.isDisplayed());
        assertTrue(logInBtn.isEnabled());
        logInBtn.click();
    }

    @Then("Login is success with {string}")
    public void loginIsSuccessWith(String response) throws InterruptedException {
        switch (response) {
            case "VALID":
                assertEquals("hudl home page is displayed ",HOME_PAGE , homePage.getText());
                break;
            case "INVALID":
                Thread.sleep(3000);
                assertTrue(logInBtn.isDisplayed());
                assertEquals("Either username/password has issue",ERROR_MESSAGE, textMessage.getText());
                break;
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}

