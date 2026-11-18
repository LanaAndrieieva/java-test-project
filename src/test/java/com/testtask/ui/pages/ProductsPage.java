package com.testtask.ui.pages;

import com.testtask.ui.model.ProductSort;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductsPage extends BasePage {

    private static final String TITLE = "Products";

    @FindBy(css = ".title")
    private WebElement title;

    @FindBy(css = ".inventory_item")
    private List<WebElement> products;

    @FindBy(css = ".product_sort_container")
    private WebElement sortContainer;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> productPrices;

    public ProductsPage open() {
        openPage(pagePath("products.path"));
        wait.until(ExpectedConditions.visibilityOf(title));
        return this;
    }

    public void applySort(ProductSort sort) {
        wait.until(ExpectedConditions.visibilityOf(sortContainer));
        new Select(sortContainer).selectByValue(sort.value());
        wait.until(driver -> sort.value().equals(new Select(sortContainer).getFirstSelectedOption().getDomAttribute("value")));
    }

    public boolean isSortedBy(ProductSort sort) {
        return switch (sort) {
            case NAME_ASC -> isSorted(getProductNames(), Comparator.naturalOrder());
            case NAME_DESC -> isSorted(getProductNames(), Comparator.reverseOrder());
            case PRICE_LOW_TO_HIGH -> isSorted(getPrices(), Comparator.naturalOrder());
            case PRICE_HIGH_TO_LOW -> isSorted(getPrices(), Comparator.reverseOrder());
        };
    }

    public List<String> getProductNames() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productNames));
        return productNames.stream().map(WebElement::getText).toList();
    }

    public void addToCart(String productName) {
        WebElement product = products.stream()
                .filter(item -> productName.equals(item.findElement(By.cssSelector(".inventory_item_name")).getText()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Product '%s' was not found", productName)));
        product.findElement(By.cssSelector("button.btn_inventory")).click();
    }

    public boolean isOpened() {
        return TITLE.equals(getTitle());
    }

    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOf(title));
        return title.getText();
    }

    public boolean hasProducts() {
        wait.until(ExpectedConditions.visibilityOf(title));
        return !products.isEmpty();
    }

    private List<BigDecimal> getPrices() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));
        return productPrices.stream()
                .map(price -> new BigDecimal(price.getText().replace("$", "")))
                .toList();
    }

    private <T> boolean isSorted(List<T> actual, Comparator<T> comparator) {
        List<T> expected = new ArrayList<>(actual);
        expected.sort(comparator);
        return actual.equals(expected);
    }
}
