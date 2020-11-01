package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HorizontalMenuPageHelper extends PageBase {
    @FindBy(xpath = "//button[@data-test-id='header-boards-menu-button']")
    WebElement boardsButton;

    @FindBy(xpath = "//button[@data-test-id='header-member-menu-button']")
    WebElement memberMenuIcon;

    public HorizontalMenuPageHelper (WebDriver driver){
        super(driver);
    }

    public HorizontalMenuPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(boardsButton, 20);
        return this;
    }

    public String getBoardsIconName(){
        return boardsButton.getText();
    }

    public String getTitleOfMenuIcon() {
        String titleOfMenuIcon = memberMenuIcon.getAttribute("title");
        return titleOfMenuIcon;
    }

    public HorizontalMenuPageHelper openDropMenuPage() {
        waitUntilElementIsClickable(memberMenuIcon,10);
        memberMenuIcon.click();
        return this;
    }


}
