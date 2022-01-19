package com.luxoft.web.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InputField {
    private WebDriver driver;
    private String identifier;

    @FindBy(id = "query-search")
    WebElement search;

    //searchButton
    //error field
    // ...

    public InputField(WebDriver driver, String identifier) {
        this.driver = driver;
        this.identifier = identifier;
        PageFactory.initElements(driver, this);
    }

    // methods for input string
}
