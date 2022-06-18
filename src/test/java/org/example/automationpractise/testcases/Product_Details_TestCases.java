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
import org.example.automationpractise.pages.PrintedChiffonDressPage;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Product_Details_TestCases extends BaseTest {

    WebDriver driver;
    HomePage homePage;
    PrintedChiffonDressPage printedChiffonDressPage;

    @BeforeClass
    public void SetUp(){
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(priority = 3)
    public void tc06_seeProductPage(){
        test = extent.createTest("tc06_seeProductPage","PASSED this test case");
        homePage = new HomePage(driver);
        printedChiffonDressPage = homePage.clickOnChiffonDress();
        Assert.assertEquals(driver.getTitle(),"Printed Chiffon Dress - My Store","Product page did not load");
    }


    @Test(priority = 3)
    public void tc07_selectSizeandColor(){
        test = extent.createTest("tc07_selectSizeandColor","PASSED this test case");
        printedChiffonDressPage.selectSize("L");
        printedChiffonDressPage.selectColorGreen();
        Assert.assertEquals("L",printedChiffonDressPage.getSelectedSize(),"Selected size did not match");
    }


    @Test(priority = 3)
    public void tc08_changeQty() throws InterruptedException, IOException {

        test = extent.createTest("tc08_changeQty","PASSED this test case");
        printedChiffonDressPage.increaseQuantity(2);
        test.log(Status.PASS,"Screen shot shows quantity increased to 3");
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshot copied from buffer is saved at the mentioned path.
        FileUtils.copyFile(f, new File("C:\\Users\\nidhi\\Documents\\ActivateIT\\Week 14-Selenium\\nidhishah_ecomm_sba\\test-output\\quantityScreenShot.png"));
        test.addScreenCaptureFromPath("quantityScreenShot.png");
    }


    @Test(priority = 3)
    public void tc09_addTOCart() throws IOException {
        test = extent.createTest("tc09_addTOCart","PASSED this test case");
        printedChiffonDressPage.addToCart();
        printedChiffonDressPage.swicthToOverlay();
       String heading =  printedChiffonDressPage.getQty();
       Assert.assertEquals(heading,"Your shopping cart contains: 3 Products","Not succesful");

        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshot copied from buffer is saved at the mentioned path.
        FileUtils.copyFile(f, new File("C:\\Users\\nidhi\\Documents\\ActivateIT\\Week 14-Selenium\\nidhishah_ecomm_sba\\test-output\\product1.png"));
        test.addScreenCaptureFromPath("product1.png");
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
