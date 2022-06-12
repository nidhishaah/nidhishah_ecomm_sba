package org.example.automationpractise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Set;

public class PrintedChiffonDressPage {
    WebDriver driver;

    public PrintedChiffonDressPage(WebDriver driver) {
    this.driver = driver;
    }

    By sizeSelector = By.id("group_1");
    public void selectSize(String arg){
        Select select1 = new Select(driver.findElement(sizeSelector));
        select1.selectByVisibleText(arg);

    }

    public String getSelectedSize(){
        Select select1 = new Select(driver.findElement(sizeSelector));
        return select1.getAllSelectedOptions().get(0).getText();

    }

    By plusSign = By.xpath("//*[@id=\"quantity_wanted_p\"]/a[2]");
    public void increaseQuantity(int index){
        for(int i=0;i<index;i++)
        driver.findElement(plusSign).click();
    }


    public String getQuantity(){
    String qty = driver.findElement(By.name("qty")).getText().toString();
        System.out.println("quantity returned"+qty);
        return qty;

    }

    By greenSelector = By.xpath("//*[@id=\"color_15\"]");
    public void selectColorGreen(){
        driver.findElement(greenSelector).click();
    }


    By addTOCart = By.name("Submit");
    public void addToCart(){
        driver.findElement(addTOCart).click();

    }


    By proceedToCheckout = By.linkText("Proceed to checkout");

    public void swicthToOverlay(){
        //window handle of parent window
        String m = driver.getWindowHandle();

        // store window handles in Set
        Set<String> w = driver.getWindowHandles();
        String hd=" ";
        // iterate window handles
        for (String h: w) {
            // switching to each window
            driver.switchTo().window(h);
            driver.findElement(proceedToCheckout).click();
        }
    }

    By heading = By.xpath("//*[@id=\"cart_title\"]/span");
    public String getQty(){
        return driver.findElement(heading).getText().trim().toString();
    }

}
