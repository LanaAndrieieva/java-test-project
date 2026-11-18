package com.testtask.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    private static final String TITLE = "Your Cart";

    @FindBy(css = ".title")
    private WebElement title;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> productNames;

    public CartPage open() {
        openPage(pagePath("cart.path"));
        wait.until(ExpectedConditions.visibilityOf(title));
        return this;
    }

    public boolean containsProduct(String productName) {
        wait.until(ExpectedConditions.textToBePresentInElement(title, TITLE));
        return productNames.stream()
                .map(WebElement::getText)
                .anyMatch(productName::equals);
    }
}
