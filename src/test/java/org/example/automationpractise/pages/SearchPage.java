package org.example.automationpractise.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {
    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    By searchResults = By.xpath("//*[@id=\"center_column\"]/h1/span[2]");

    public String getSearchResults(){
        return driver.findElement(searchResults).getText();
    }

}
