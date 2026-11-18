package com.testtask.ui.tests;

import com.testtask.ui.data.Users;
import com.testtask.ui.model.ProductSort;
import com.testtask.ui.pages.CartPage;
import com.testtask.ui.pages.LoginPage;
import com.testtask.ui.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductCartTest extends BaseTest {

    @Test
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
