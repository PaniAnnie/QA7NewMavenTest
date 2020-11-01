package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.HorizontalMenuPageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;
import ru.stqa.selenium.util.DataProviders;

public class LoginTests extends TestBase {
HomePageHelper homePage;
LoginPageHelper loginPage;
HorizontalMenuPageHelper horizontalMenu;

    @BeforeMethod
    public void initTests() {
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        horizontalMenu = PageFactory.initElements(driver, HorizontalMenuPageHelper.class);

        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded();
    }

    @Test
    public void loginNegativeLoginEmpty() {
        loginPage.loginNotAtlassianUser("", PASSWORD);
        Assert.assertEquals(loginPage.getErrorMessage(),
                "Missing email", "Message is not correct: ");
    }

    @Test
    public void loginNegativeWrongLogin() {
        loginPage.loginNotAtlassianUser("neti", PASSWORD);
        Assert.assertEquals(loginPage.getWrongLoginErrorMessage(),
                "Incorrect email address and / or password. Do you need help logging in?",
                "Message is not correct: ");
    }

    @Test
    public void loginNegativeWrongPassword() {
        loginPage.loginAtlassianUser(LOGIN, "1234");
        Assert.assertEquals(loginPage.getAtlassianErrorMessage(),
                "Incorrect email address and / or password.\n" +
                "Do you need help logging in?",
                "Message is not correct: ");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderFirst")
    public void loginPositive(String login, String password) {
        loginPage.loginAtlassianUser(login, password);
        //boardsPage.waitUntilPageIsLoaded();
        horizontalMenu.waitUntilPageIsLoaded();
        Assert.assertTrue(horizontalMenu.getBoardsIconName().equals("Boards"),
                "The text of the button is not 'Boards': ");
    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "dataProviderLoginNeg")
    public void loginNegative(String login, String password, String message) {
        loginPage.loginNotAtlassianUser(login, password);
        Assert.assertEquals(loginPage.getErrorMessage(), message,
                "Message is not correct: ");
    }
}

