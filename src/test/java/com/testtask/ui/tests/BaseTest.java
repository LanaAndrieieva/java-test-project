package com.testtask.ui.tests;

import com.testtask.ui.driver.WebDriverSingleton;
import org.testng.annotations.AfterMethod;

public abstract class BaseTest {

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.quit();
    }
}
