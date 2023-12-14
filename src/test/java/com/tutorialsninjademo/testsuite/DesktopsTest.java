package com.tutorialsninjademo.testsuite;

import com.tutorialsninjademo.customlisteners.CustomListeners;
import com.tutorialsninjademo.pages.DesktopPage;
import com.tutorialsninjademo.pages.HomePage;
import com.tutorialsninjademo.pages.ProductPage;
import com.tutorialsninjademo.pages.ShoppingCartPage;
import com.tutorialsninjademo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class DesktopsTest extends BaseTest {
    HomePage homePage;
    DesktopPage desktopPage;
    ProductPage productPage;
    ShoppingCartPage shoppingCartPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        desktopPage = new DesktopPage();
        productPage = new ProductPage();
        shoppingCartPage = new ShoppingCartPage();
    }

    @Test(groups = {"sanity", "regression"})
    public void verifyProductArrangeInAlphabeticalOrder() throws InterruptedException {
        //choose desktop dropdown
        Thread.sleep(2000);
        homePage.mouseHoverAndClickOnDesktop();
        //show all desktops
        Thread.sleep(2000);
        homePage.selectMenu("Desktops");
        desktopPage.clickOnSortByPosition();
        desktopPage.selectSortByZToA("Name (Z - A)");
        Assert.assertEquals(desktopPage.afterSorting(), desktopPage.beforeSorting(), "Product not sorted into Z to A order");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        homePage.chooseGBP();//homePage.mouseHoverAndClickOnDesktop();
        homePage.selectMenu("Desktops");//desktopPage.clickOnSortByPosition();
        desktopPage.selectSortByAToZ("Name (A - Z)");
        desktopPage.clickOnHPLP3065();
        String expectedProduct = "HP LP3065";
        Thread.sleep(1000);
        String actualProduct = productPage.getHPLP3065text();
        Assert.assertEquals(actualProduct, expectedProduct, "HP LP3065 not displayed");
        productPage.selectDate("30", "November", "2022");
        productPage.clearAndAddQuantity("1");
        productPage.clickAddToCart();
        Thread.sleep(1000);
        Assert.assertTrue(productPage.isSuccessMessageAppearing(), "Message Doesn't Appear");
        productPage.clickOnShoppingCart();
        Assert.assertTrue(shoppingCartPage.isShoppingCartAppearing(), "Shopping Cart Doesn't Appear");
        Assert.assertEquals(shoppingCartPage.getProductName(), "HP LP3065", "Product Name Doesn't appear");
        Assert.assertTrue(shoppingCartPage.isDeliveryDateAppearing("2022-11-30"), "Delivery Date Doesn't Appear");
        Assert.assertEquals(shoppingCartPage.getModelText(), "Product 21", "Model Name Doesn't appear");
        Assert.assertEquals(shoppingCartPage.getTotalText(), "£74.73", "Total Doesn't appear");
    }
}
