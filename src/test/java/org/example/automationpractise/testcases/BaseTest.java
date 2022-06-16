package org.example.automationpractise.testcases;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;
import io.netty.handler.codec.http.DefaultHttpResponse;
import org.example.automationpractise.library.SelectBrowser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    private static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    private ITestResult result;


    @BeforeSuite
    public void setUpReport(){

        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        //htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/MyOwnReport.html");
        htmlReporter =
                new ExtentHtmlReporter("C:\\Users\\nidhi\\Documents\\ActivateIT\\Week 14-Selenium\\nidhishah_ecomm_sba\\test-output\\screenshots\\ecommReport.html");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "DEKTOP-34GJ352");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Nidhi Shah");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Capstone Report");
        htmlReporter.config().setReportName("Capstone Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);

    }

    @AfterSuite
    public void tearDown(){
        extent.flush();
        //Testing Jenkins automation
        //driver.quit();
    }
//    @AfterMethod
//    public void recordSuccess(ITestResult result, WebDriver driver) throws IOException {
//        if(ITestResult.SUCCESS == result.getStatus()){
//            TakesScreenshot camera = (TakesScreenshot)driver;
//            File screenshot = ((TakesScreenshot) camera).getScreenshotAs(OutputType.FILE);
//            Files.move(screenshot,new File("C:\\Users\\nidhi\\Documents\\ActivateIT\\Week 14-Selenium\\nidhishah_ecomm_sba\\src\\main\\resources\\screenshots\\"+result.getName()+".png"));
//            System.out.println(screenshot.getAbsolutePath());
//            test.addScreenCaptureFromPath("C:\\Users\\nidhi\\Documents\\ActivateIT\\Week 14-Selenium\\nidhishah_ecomm_sba\\src\\main\\resources\\screenshots\\"+result.getName()+".png");
//        }
//    }

}
