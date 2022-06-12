package org.example.automationpractise.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelectBrowser {

    static WebDriver driver;

    public static WebDriver StartBrowser(String browsername)
    {

        String fileUrl = System.getProperty("user.dir");

        // ---If the browser is Firefox----
        if(browsername.equalsIgnoreCase("Firefox"))
        {
            // Set the path for geckodriver.exe
            System.setProperty("webdriver.gecko.driver",fileUrl+"\\src\\main\\resources\\Drivers\\geckodriver.exe ");
            driver = new FirefoxDriver();
        }
        //---- If the browser is Chrome--
        else if(browsername.equalsIgnoreCase("Chrome"))
        {
            // Set the path for chromedriver.exe
            System.setProperty("webdriver.chrome.driver" , fileUrl+"\\src\\main\\resources\\Drivers\\chromedriver1.exe");
            driver = new ChromeDriver();
        }
        // ----If the browser is  EdgeIE----
        else if(browsername.equalsIgnoreCase("EdgeExplore"))
        {
            // Set the path for IEdriver
            System.setProperty("webdriver.edge.driver", fileUrl+"\\src\\main\\resources\\Drivers\\msedgedriver2.exe");
            // Instantiate a EdgeDriverclass.
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
        }
        driver.manage().window().maximize();
        return driver;
    }

}
