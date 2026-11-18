package com.testtask.ui.tests;

import com.testtask.ui.data.Users;
import com.testtask.ui.pages.LoginPage;
import com.testtask.ui.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Step;

public class LoginTest extends BaseTest {

    @Step("Login as standard user")
    @Test(groups = {"ui"})
    public void standardUserReachesProductsPageWithProducts() {
        ProductsPage productsPage = new LoginPage()
                .open()
                .login(Users.STANDARD);

        Assert.assertTrue(productsPage.isOpened());
        Assert.assertTrue(productsPage.hasProducts());
    }
}
