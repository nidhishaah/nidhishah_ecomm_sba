package org.example.automationpractise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By searchBox = By.id("search_query_top");
    public SearchPage fillSearchBox(String arg){
        driver.findElement(searchBox).sendKeys(arg+ Keys.ENTER);
        return new SearchPage(driver);
    }

    By blouseImage = By.linkText("Blouse");
    public BlouseProductPage clickOnBlouse(){
        driver.findElement(blouseImage).click();
        return new BlouseProductPage(driver);
    }

    By signInPageLink = By.linkText("Sign in");

    public SignInPage clickSignInPage(){
         driver.findElement(signInPageLink).click();
         return new SignInPage(driver);
    }

    By printedChiffonDress = By.linkText("Printed Chiffon Dress");
    public PrintedChiffonDressPage clickOnChiffonDress(){
        driver.findElement(printedChiffonDress).click();
        return new PrintedChiffonDressPage(driver);
    }

}
