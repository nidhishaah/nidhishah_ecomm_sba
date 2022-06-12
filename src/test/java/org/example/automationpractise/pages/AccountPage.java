package org.example.automationpractise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {

    WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    By heading = By.xpath("//*[@id=\"center_column\"]/p");
    public String givePageHeading(){
        return driver.findElement(heading).getText();
    }

    By homeIcon = By.xpath("//*[@id=\"columns\"]/div[1]/a");
    public void clickHomeIcon(){
        driver.findElement(homeIcon).click();
    }
}
