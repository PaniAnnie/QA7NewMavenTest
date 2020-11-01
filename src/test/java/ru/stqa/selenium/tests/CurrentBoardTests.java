package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;
import ru.stqa.selenium.util.DataProviders;


public class CurrentBoardTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHaifa7currentBoard;

        @BeforeMethod
        public void initTests() {
            homePage = PageFactory.initElements(driver, HomePageHelper.class);
            loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
            boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
            qaHaifa7currentBoard = new CurrentBoardPageHelper(driver, "QA Haifa7");

            homePage.waitUntilPageIsLoaded()
                    .openLoginPage();
            loginPage.waitUntilPageIsLoaded()
                    .loginAtlassianUser(LOGIN, PASSWORD);
            boardsPage.waitUntilPageIsLoaded()
                    .openCurrentBoardPage("QA Haifa7");
            qaHaifa7currentBoard.waitUntilPageIsLoaded();
        }

        @Test
        public void isCorrectCurrentBoard() {
            System.out.println("Header of the current board: " + qaHaifa7currentBoard.getCurrentBoardHeader());
            Assert.assertEquals(qaHaifa7currentBoard.getCurrentBoardHeader(), "QA Haifa7", "Header of the current board is wrong: ");
        }

        @Test
        public void isCorrectCurrentBoard2() {
            Assert.assertTrue(qaHaifa7currentBoard.isCorrectCurrentBoard(), "Header of the current board is wrong: ");
        }

        @Test (dataProviderClass = DataProviders.class, dataProvider = "dataProviderCreateList")
        public void loginPositiveAndAddList(String nameList) {
            int quantityOfListsInTheBeginning = qaHaifa7currentBoard.getQuantityOfListsInThisBoard();
            qaHaifa7currentBoard.addNewList(nameList);
            int quantityOfListsInTheEnd = qaHaifa7currentBoard.getQuantityOfListsInThisBoard();
            Assert.assertEquals(quantityOfListsInTheEnd, quantityOfListsInTheBeginning+1, "Something goes wrong: ");
        }

        @Test
        public void loginPositiveAndDeleteList() {
            if (qaHaifa7currentBoard.getQuantityOfListsInThisBoard() == 0) {
                qaHaifa7currentBoard.addNewList("nameList");
            }
                int quantityOfListsInTheBeginning = qaHaifa7currentBoard.getQuantityOfListsInThisBoard();
                qaHaifa7currentBoard.openFirstListExtraMenu()
                                    .archiveThisList();
                int quantityOfListsInTheEnd = qaHaifa7currentBoard.getQuantityOfListsInThisBoard();
                Assert.assertEquals(quantityOfListsInTheEnd, quantityOfListsInTheBeginning-1, "Something goes wrong: ");
        }
}