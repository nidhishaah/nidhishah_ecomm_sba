package org.example.automationpractise.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.example.automationpractise.library.SelectBrowser;
import org.example.automationpractise.pages.HomePage;
import org.example.automationpractise.pages.SearchPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class Search_Feature_TestCases extends BaseTest{
    WebDriver driver;
    HomePage homePage;
    SearchPage searchPage;

    @BeforeClass
    public void SetUp(){
        driver = SelectBrowser.StartBrowser("EdgeExplore");
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test(priority = 2)
    public void tc05_searchTest(Method method) throws IOException {
        test = extent.createTest(method.getName(), "PASSED test case");
        homePage = new HomePage(driver);

        searchPage = homePage.fillSearchBox("printed chiffon dress");
        test.log(Status.PASS,"Searched with query - printed chiffon dress");
        String results = searchPage.getSearchResults();
        test.log(Status.PASS,"Search page with results displayed");
        //This asserts that search page returns 2 results for a "printed Chiffon Dre4ss"
        Assert.assertEquals(results,"2 results have been found.","Search page results yield incorrect results");

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0,500)");

      //  recordSuccess(driver);

//        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        //screenshot copied from buffer is saved at the mentioned path.
//        FileUtils.copyFile(f, new File("C:\\Users\\nidhi\\Documents\\ActivateIT\\Week 14-Selenium\\nidhishah_ecomm_sba\\test-output\\search1.png"));
//        test.addScreenCaptureFromPath("search1.png");


    }
    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

    @AfterMethod
    public void recordSuccess(ITestResult result) throws IOException {
        if(ITestResult.SUCCESS == result.getStatus()){
            TakesScreenshot camera = (TakesScreenshot)driver;
            File screenshot = ((TakesScreenshot) camera).getScreenshotAs(OutputType.FILE);
            Files.move(screenshot,new File("C:\\Users\\nidhi\\Documents\\ActivateIT\\Week 14-Selenium\\nidhishah_ecomm_sba\\test-output\\screenshots\\"+result.getName()+".png"));
            System.out.println(screenshot.getAbsolutePath());
            test.addScreenCaptureFromPath(result.getName()+".png");
        }
    }

}
