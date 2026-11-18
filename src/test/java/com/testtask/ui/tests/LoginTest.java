package com.testtask.ui.tests;

import com.testtask.ui.data.Users;
import com.testtask.ui.pages.LoginPage;
import com.testtask.ui.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void standardUserReachesProductsPageWithProducts() {
        ProductsPage productsPage = new LoginPage()
                .open()
                .login(Users.STANDARD);

        Assert.assertTrue(productsPage.isOpened());
        Assert.assertTrue(productsPage.hasProducts());
    }
}
