package org.example.automationpractise.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.example.automationpractise.library.SelectBrowser;
import org.example.automationpractise.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Registration_Login_TestCases {
    WebDriver driver;
    HomePage homePage;
    BlouseProductPage blouseProductPage;
    SignInPage signInPage;
    AccountPage accountPage;

    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;


    @BeforeSuite
    public void setUpReport(){

        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        //htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/MyOwnReport.html");
        htmlReporter =
                new ExtentHtmlReporter("C:\\Users\\nidhi\\Documents\\ActivateIT\\Week 14-Selenium\\nidhishah_ecomm_sba\\test-output\\registration_loginReport.html");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "DEKTOP-34GJ352");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Nidhi Shah");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting Demo Report");
        htmlReporter.config().setReportName("My Search Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);

    }

    @BeforeTest
    public void launchBrowser(){
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }


    @Test(priority = 2)
    public void tc01_seeSignInTab(){
        test = extent.createTest("tc01_seeSignInTab", "PASSED test case");
        homePage = new HomePage(driver);
        blouseProductPage = homePage.clickOnBlouse();
        blouseProductPage.clickAddTOCart();
        blouseProductPage.clickProceedToCheckout();

        Assert.assertEquals("02. Sign in",blouseProductPage.getTabInfo(),"Unable to get Tab Info");

    }

    @Test(priority = 2)
    public void tc02_invalidCreateAccount() {
        test = extent.createTest("tc02_invalidCreateAccount", "PASSED test case");
        homePage = new HomePage(driver);
        signInPage = homePage.clickSignInPage();
        signInPage.sendCreateEmail("nidhi.shaah@gmail.com");
        signInPage.submitCreateButton();
        Assert.assertEquals(signInPage.getError(),"An account using this email address has already been registered. Please enter a valid password or request a new one.","No error recieved");

    }

    @Test(priority = 2)
    public void tc03_validLogin() throws IOException {
        test = extent.createTest("tc03_validLogin", "PASSED test case");
        homePage = new HomePage(driver);
        signInPage = homePage.clickSignInPage();
        signInPage.sendEmail("nidhi.shaah@gmail.com");
        signInPage.sendPassword("abcde");
        accountPage = signInPage.clickSignIn();

        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshot copied from buffer is saved at the mentioned path.
        FileUtils.copyFile(f, new File("C:\\Users\\nidhi\\Documents\\ActivateIT\\Week 14-Selenium\\nidhishah_ecomm_sba\\test-output\\register1.png"));
        test.addScreenCaptureFromPath("register1.png");

        Assert.assertEquals(accountPage.givePageHeading(),"Welcome to your account. Here you can manage all of your personal information and orders.","My account page not loaded correctly");
        driver.findElement(By.linkText("Sign out")).click();
    }

    @Test(priority=2)
    public void tc04_viewProducts() throws InterruptedException {
        test = extent.createTest("tc04_viewProducts", "PASSED test case");

        homePage = new HomePage(driver);

        signInPage = homePage.clickSignInPage();
        signInPage.sendEmail("nidhi.shaah@gmail.com");
        signInPage.sendPassword("abcde");
        accountPage = signInPage.clickSignIn();
        accountPage.clickHomeIcon();

        Assert.assertEquals(driver.getTitle(),"My Store","My store did not load");

    }





    @AfterTest
    public void tearDown(){
        extent.flush();
        driver.quit();
    }
}
