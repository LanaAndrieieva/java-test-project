package com.testtask.ui.tests;

import com.testtask.ui.data.Users;
import com.testtask.ui.model.ProductSort;
import com.testtask.ui.pages.CartPage;
import com.testtask.ui.pages.LoginPage;
import com.testtask.ui.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Step;

public class ProductCartTest extends BaseTest {

    @Step("Sort products and add to the cart")
    @Test(groups = {"ui"})
    public void sortedProductIsPresentInCart() {
        ProductsPage productsPage = new LoginPage()
                .open()
                .login(Users.STANDARD)
                .open();

        productsPage.applySort(ProductSort.PRICE_LOW_TO_HIGH);
        Assert.assertTrue(productsPage.isSortedBy(ProductSort.PRICE_LOW_TO_HIGH));

        String productName = productsPage.getProductNames().get(0);
        productsPage.addToCart(productName);

        CartPage cartPage = new CartPage().open();
        Assert.assertTrue(cartPage.containsProduct(productName));
    }
}
