package com.luxoft.web;

import com.luxoft.web.page.HomePage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@ExtendWith(TestWatcher.class)
@Epic("Test Epic")
@Story("test story")
public class WebTest {
    static WebDriver driver;
    static WebDriverWait waitVar;

    @BeforeAll
    static void setUp(){
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true); // ignore incorrect sertificates
        options.setHeadless(false);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MICROSECONDS); // генеральный таймаут
        //  driver.get("https://www.luxoft.com/");
        driver.navigate().to("https://www.luxoft.com/"); // the same as prev line
//        driver.manage().window().maximize();
        waitVar = new WebDriverWait(driver, 5);

    }

    @Test
    @Description("test description")
     void openHomePage(){
//        driver.findElements(By.className("actions"));
//        driver.findElements(By.className("actions")).get(3).click();
        driver.findElement(By.id("menu-search")).click();
//        Assertions.assertTrue(driver.findElement(By.id("menu-search"))
//                .findElement(By.className("search-switcher__text")).getText()
//                            .contains("Search"));
        System.out.println(driver.findElement(By.id("menu-search"))
                .findElement(By.className("search-switcher__text")).getText());

        driver.findElement(By.id("query-search")).sendKeys("covid-19"+Keys.ENTER);
      //  driver.findElementById("query-search").sendKeys(Keys.ENTER);
      //  driver.navigate().refresh();
      //  driver.navigate().back();
//        driver.close(); //  закрытие
//        driver.quit(); // безусловное закрв=ытие
    }

    @Test
    void testSearch() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.searchFor("covid-19");
        waitVar.until(ExpectedConditions.presenceOfElementLocated(By.linkText("https://www.dxc.technology/covid-19/")));
        homePage.isResultLinkDisplayed();
    }

    @Test
    void arrowTest() throws InterruptedException {
        HomePage homePage = new HomePage((driver));
        homePage.clickLinkByText("INSIGHTS");
        homePage.moveMouseToElementNum(1);
        homePage.checkAssowsSize(1);
    }

    @Test
    void dd() throws InterruptedException {
        driver.navigate().to("https://the-internet.herokuapp.com/drag_and_drop");
        Actions builder = new Actions(driver);
        builder.dragAndDrop(driver.findElement(By.id("column-a")),
             driver.findElement(By.id("column-b")))
                .build().perform();
        Thread.sleep(5000);
    }

    @Test
    void newTab(){
        new HomePage(driver).openNewTab();
        }

    @AfterAll
    static void tearDown() {
        driver.quit(); // безусловное закрытие всех вкладок
    }
}
