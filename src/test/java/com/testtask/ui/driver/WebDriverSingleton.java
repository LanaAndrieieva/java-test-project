package com.testtask.ui.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public final class WebDriverSingleton {

    private static WebDriver driver;

    private WebDriverSingleton() {
    }

    public static synchronized WebDriver getInstance() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        }
        return driver;
    }

    public static synchronized void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
