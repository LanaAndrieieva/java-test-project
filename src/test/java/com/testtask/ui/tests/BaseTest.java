package com.testtask.ui.tests;

import com.testtask.ui.driver.WebDriverSingleton;
import org.testng.annotations.AfterMethod;
import org.testng.ITestResult;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;

public abstract class BaseTest {

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && WebDriverSingleton.hasInstance()) {
            byte[] screenshot = ((TakesScreenshot) WebDriverSingleton.getInstance()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failure Screenshot", "image/png",
                    new ByteArrayInputStream(screenshot), "png");
        }
        WebDriverSingleton.quit();
    }
}
