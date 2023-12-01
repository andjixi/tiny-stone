package week.first;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class FirstWeekAdvanced {


    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    WebDriver driver = null;

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(String browser){

        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:9010");

        driver = WebDriverFabric.startBrowser(browser);

    }


    @Test
    public void thirdTestHappyFlow(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeOnUsernameFieldXPath(USERNAME);
        loginPage.typeOnPasswordFieldCSS(PASSWORD);
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(driver);
        homePage.waitUntilPageTitleIsCorrect(5, HomePage.PAGE_TITLE);
        homePage.getCurrentUrl();

        TopMenu topMenu = new TopMenu(driver);

        // add product to basket
        homePage.addProductToCart("Sauce Labs Backpack");

        // save the current number of products in the basket
        int cartItemCountBeforeCheckout = topMenu.getCartItemCount();

        // finish shopping
        homePage.finishPurchase();

        // check if success
        Assert.assertTrue(homePage.isOrderCompleted(), "Order was not completed successfully.");

        // check reset in basket
        int cartItemCountAfterCheckout = topMenu.getCartItemCount();
        Assert.assertEquals(cartItemCountAfterCheckout, 0,
                "Number of items in the cart is not reset after completing the purchase.");

        Assert.assertTrue(loginPage.isLoginButtonVisible(), "Login button is not visible after completing the purchase.");

    }


    @AfterMethod
    public void teardown(){
        if (driver != null) {
            driver.quit();
        }
    }
}






