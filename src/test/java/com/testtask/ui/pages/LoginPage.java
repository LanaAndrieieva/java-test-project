package com.testtask.ui.pages;

import com.testtask.ui.model.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(css = "[data-test='username']")
    private WebElement usernameInput;

    @FindBy(css = "[data-test='password']")
    private WebElement passwordInput;

    @FindBy(css = "[data-test='login-button']")
    private WebElement loginButton;

    public LoginPage open() {
        openPage(pagePath("login.path"));
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        return this;
    }

    public ProductsPage login(User user) {
        usernameInput.sendKeys(user.username());
        passwordInput.sendKeys(user.password());
        loginButton.click();
        return new ProductsPage();
    }
}
