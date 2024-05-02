package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     * 
     * @throws InterruptedException
     */
    WebDriver driver;
    static final Logger log = Logger.getLogger(AppTest.class);
    ExtentReports reports;
    ExtentTest test;

    @Test
    public void shouldAnswerWithTrue() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mayoclinic.org/");
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(
                "C:\\Users\\ASUS\\OneDrive\\Desktop\\st_modellab\\ExtentReports\\report.html");
        reports = new ExtentReports();
        reports.attachReporter(extentSparkReporter);
        Thread.sleep(1000);
        test = reports.createTest("MayOClinic Test");
        PropertyConfigurator.configure("src/main/config/log4j.properties");
        log.info("url opened");
        test.log(Status.PASS, "Url Opened");
        WebElement element = driver.findElement(
                By.xpath("//*[@id='header__content-inner-container']/div[1]/ul/li[5]/div[1]/div/button/span"));
        element.click();
        Thread.sleep(2000);
        WebElement element2 = driver.findElement(
                By.xpath("//*[@id=\"button-d87139392b\"]/span/span/span[1]/span"));
        element2.click();
        log.info("Give now clicked");
        test.log(Status.PASS, "Give now Button Clicked");
        Thread.sleep(2000);
        WebElement element3 = driver.findElement(
                By.xpath("//*[@id=\"amounts\"]/label[3]"));
        element3.click();
        Thread.sleep(2000);
        WebElement drop1 = driver.findElement(
                By.xpath("//*[@id=\"designation\"]"));
        Select dropdown = new Select(drop1);
        dropdown.selectByVisibleText("Medical Education");
        Thread.sleep(2000);
        log.info("Selected Medical Education");
        test.log(Status.PASS, "Selected Medical Education");
        WebElement element4 = driver.findElement(
                By.xpath("//*[@id=\"adfWrapper\"]/fieldset[2]/div[1]/label"));
        element4.click();
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
        Thread.sleep(2000);
        WebElement drop2 = driver.findElement(
                By.xpath("//*[@id=\"personalTitle\"]"));
        Select dropdown2 = new Select(drop2);
        dropdown2.selectByVisibleText("Mr.");
        log.info("Selected Mr.");
        test.log(Status.PASS, "Selected Mr.");
        Thread.sleep(2000);
        FileInputStream fs = new FileInputStream("C:\\Users\\ASUS\\OneDrive\\Desktop\\ModellabExcel.xlsx");
        XSSFWorkbook w = new XSSFWorkbook(fs);
        XSSFSheet s = w.getSheet("Sheet1");
        XSSFRow r = s.getRow(0);
        String firstname = r.getCell(0).getStringCellValue();
        driver.findElement(By.xpath("//*[@id='personalFirstName']")).sendKeys(firstname);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"personalLastName\"]")).sendKeys("TesterLast");
        Thread.sleep(2000);
        WebElement drop3 = driver.findElement(
                By.xpath("//*[@id=\"personalCountry\"]"));
        Select dropdown3 = new Select(drop3);
        dropdown3.selectByVisibleText("India");
        Thread.sleep(2000);
        WebElement drop4 = driver.findElement(
                By.xpath("//*[@id=\"personalState\"]"));
        Select dropdown4 = new Select(drop4);
        dropdown4.selectByVisibleText("NA");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"personalAddress\"]")).sendKeys("1/66C");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"personalCity\"]")).sendKeys("Coimbatore");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"personalZip\"]")).sendKeys("641008");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"personalPhone\"]")).sendKeys("9417652849");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"personalEmail\"]")).sendKeys("r@gmail.com");
        log.info("Updated Address");
        test.log(Status.PASS, "Updated Address");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"adfSubmit\"]")).click();
        log.info("Button Clicked");
        test.log(Status.PASS, "Button clicked");
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "C:\\Users\\ASUS\\OneDrive\\Desktop\\st_modellab\\ExtentReports\\mayoclinic.png";
        FileUtils.copyFile(screenshot, new File(screenshotPath));
        test.log(Status.PASS, "Test Passed Screenshot",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        Thread.sleep(5000);

    }

    @AfterTest
    public void finish() {
        reports.flush();
        driver.quit();
    }
}
