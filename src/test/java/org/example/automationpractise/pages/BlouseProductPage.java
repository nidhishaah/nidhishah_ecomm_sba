package org.example.automationpractise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Set;

public class BlouseProductPage {
    WebDriver driver;

    public BlouseProductPage(WebDriver driver) {
    this.driver = driver;
    }

    By addTOCartButton = By.xpath("//*[@id=\"add_to_cart\"]/button/span");
    public void clickAddTOCart(){
        driver.findElement(addTOCartButton).click();
    }


    By proceedToCheckout = By.linkText("Proceed to checkout");
    public void clickProceedToCheckout(){
        //window handle of parent window
        String m = driver.getWindowHandle();

        // store window handles in Set
        Set<String> w = driver.getWindowHandles();

        // iterate window handles
        for (String h: w)
        {
            // switching to each window
            driver.switchTo().window(h);
            String s= driver.getTitle();

            // checking specific window title
            if(s.equalsIgnoreCase("Blouse - My Store"))
            {
                System.out.println("Window title : "+ driver.getTitle());
                driver.findElement(proceedToCheckout).click();
            }
        }

        driver.findElement(By.linkText("Proceed to checkout")).click();

    }

    By signIn = By.xpath("//*[@id=\"order_step\"]/li[2]/span");
    public String getTabInfo(){
        return driver.findElement(signIn).getText();
    }


}
