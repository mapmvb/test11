package com.luxoft.web.page;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage {

    public static WebDriver driver;
//
//    /// ----- first approach -- use By ---
//
//    public HomePage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    By searchButton = By.id("menu-search");
//    By searchField = By.id("query-search");
//    By resultLink = By.linkText("https://www.dxc.technology/covid-19/");
//
//    public void searchFor(String text){
//        driver.findElement(searchButton).click();
//        driver.findElement(searchField).sendKeys(text + Keys.ENTER);
//    }
//
//    public void isResultLinkDisplayed(){
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        assertThat(driver.findElement(resultLink).isDisplayed(), equalTo(true));
//    }

    // ---- 2-nd approach ---
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "menu-search")
    WebElement searchButton;

    @FindBys({
            @FindBy( id = "query-search"),
            @FindBy( tagName = "input")
    }
    )

    @FindBy(id = "query-search")
    WebElement query;

    @FindBy(linkText = "https://www.dxc.technology/covid-19/")
    List<WebElement> links;

    @FindBy(className = "lux-insights__item-img-container")
    List<WebElement> insights;

    @Step(" Search for {text}")
    public void searchFor(String text){
        searchButton.click();
        query.sendKeys(text + Keys.ENTER);
        assertEquals("text", query.getAttribute("type"));

    }

    @Step("description")
    public void isResultLinkDisplayed() throws InterruptedException {

//        Thread.sleep(3000);
        assertThat(links.get(0).isDisplayed(), equalTo(true));
    }

//    public void waitForLinkPresence(String lnkText) {
//        waitVar.until(ExpectedConditions.presenceOfElementLocated(By.linkText()));
//
//    }

    @Step("click link {0}")
    public void clickLinkByText(String text) {
            driver.findElement(By.linkText(text)).click();
    }

    @Step("move To {num}")
    public void moveMouseToElementNum(int num) throws InterruptedException {
        Actions builder = new Actions(driver);
        builder.moveToElement(insights.get(num)).build().perform();
        Thread.sleep(5000);
    }

    @Step("check arrows {num}")
    public void checkAssowsSize(int num) {
        List<WebElement> arrows = insights.get(num).findElements(By.xpath("./following-sibling::div[2]/i"));
        assertThat(arrows.size(), equalTo(3));
    }

    @Step("open new tab")
    public void openNewTab() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript("allert('ALLEERT!!')");
//        driver.switchTo().alert().dismiss(); // закрыть аллерт
        jse.executeScript("window.open()");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles()); // получили список handles всех открытых окон
        // и переключаемся между ними
        driver.switchTo().window(tabs.get(1));

        driver.get("https://www.luxoft-training.ru/");

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        screenshot.getScreenshotAs(OutputType.FILE);
        }


// ----- third approach -----------

//    public WebElement getElement(String name) {
//        WebElement obj = null;
//        switch (name){
//            case "searchButton":
//                obj = driver.findElement(By.id("")); break;
//
//            case "":
//                break;
//
//        }
//        return obj;
//    }
//
}