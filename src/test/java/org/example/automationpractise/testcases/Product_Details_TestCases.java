package org.example.automationpractise.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.example.automationpractise.library.SelectBrowser;
import org.example.automationpractise.pages.HomePage;
import org.example.automationpractise.pages.PrintedChiffonDressPage;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Product_Details_TestCases {

    WebDriver driver;
    HomePage homePage;
    PrintedChiffonDressPage printedChiffonDressPage;

    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;


    @BeforeSuite
    public void setUpReport(){

        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        //htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/MyOwnReport.html");
        htmlReporter =
                new ExtentHtmlReporter("C:\\Users\\nidhi\\Documents\\ActivateIT\\Week 14-Selenium\\nidhishah_ecomm_sba\\test-output\\product_detailsReport.html");
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


    @BeforeClass
    public void SetUp(){
        driver = SelectBrowser.StartBrowser("EdgeExplore");
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test(priority = 1)
    public void tc06_seeProductPage(){
        test = extent.createTest("tc06_seeProductPage","PASSED this test case");
        homePage = new HomePage(driver);
        printedChiffonDressPage = homePage.clickOnChiffonDress();
        Assert.assertEquals(driver.getTitle(),"Printed Chiffon Dress - My Store","Product page did not load");
    }


    @Test(priority = 1)
    public void tc07_selectSizeandColor(){
        test = extent.createTest("tc07_selectSizeandColor","PASSED this test case");
        printedChiffonDressPage.selectSize("L");
        printedChiffonDressPage.selectColorGreen();
        Assert.assertEquals("L",printedChiffonDressPage.getSelectedSize(),"Selected size did not match");
    }


    @Test(priority = 1)
    public void tc08_changeQty() throws InterruptedException, IOException {

        test = extent.createTest("tc08_changeQty","PASSED this test case");
        printedChiffonDressPage.increaseQuantity(2);
        test.log(Status.PASS,"Screen shot shows quantity increased to 3");
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //screenshot copied from buffer is saved at the mentioned path.
        FileUtils.copyFile(f, new File("C:\\Users\\nidhi\\Documents\\ActivateIT\\Week 14-Selenium\\nidhishah_ecomm_sba\\test-output\\quantityScreenShot.png"));
        test.addScreenCaptureFromPath("quantityScreenShot.png");
    }


    @Test(priority = 1)
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
    public void tearDown(){
        extent.flush();
        driver.quit();
    }

}
