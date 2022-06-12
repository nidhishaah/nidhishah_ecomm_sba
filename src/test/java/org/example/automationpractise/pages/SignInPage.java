package org.example.automationpractise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {

    WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    By email= By.id("email");
    public void sendEmail(String arg){
        driver.findElement(email).sendKeys(arg);
    }

    By password=By.id("passwd");
    public void sendPassword(String arg){
        driver.findElement(password).sendKeys(arg);

    }

    By signInButton = By.id("SubmitLogin");
    public AccountPage clickSignIn(){
        driver.findElement(signInButton).click();
        return new AccountPage(driver);
    }

    By createEmail = By.id("email_create");
    public void sendCreateEmail(String arg){
        driver.findElement(createEmail).sendKeys(arg);
    }

    By submitCreate = By.id("SubmitCreate");
    public void submitCreateButton(){
        driver.findElement(submitCreate).click();
    }

    By errorMsg = By.xpath("//*[@id=\"create_account_error\"]/ol/li");
    public String getError(){
        return driver.findElement(errorMsg).getText();
    }




}
