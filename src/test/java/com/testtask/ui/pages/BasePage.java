package com.testtask.ui.pages;

import com.testtask.ui.driver.WebDriverSingleton;
import com.testtask.ui.utils.PropertyUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public abstract class BasePage {

    private static final Properties PAGE_PATHS = PropertyUtils.loadPagePaths();

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final String BASE_URL = "https://www.saucedemo.com";

    protected BasePage() {
        this.driver = WebDriverSingleton.getInstance();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void openPage(String path) {
        driver.get(String.format("%s%s", BASE_URL, path));
    }

    protected String pagePath(String key) {
        String path = PAGE_PATHS.getProperty(key);
        if (path == null) {
            throw new IllegalArgumentException(String.format("Page path '%s' is not defined", key));
        }
        return path;
    }
}
