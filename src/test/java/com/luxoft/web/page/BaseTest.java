package com.luxoft.web.page;

import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait waitVar;

    @BeforeAll
    static void setUp() {
        //  сюда переносится инициализация драйвера из теста
        // в WebTest класс наследуется от Base Test
    }
    // тут можно реализовать и @AfterAll

}


