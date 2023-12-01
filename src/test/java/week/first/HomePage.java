package week.first;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){

        super(driver);
    }

    public static final String PAGE_TITLE = "Swag Labs";

    By logOutButton = By.id("logout_sidebar_link");



    public void clickLogoutButton(){

        driver.findElement(logOutButton).click();
    }

    public void addProductToCart(String productName) {

        WebElement product = driver.findElement(By.xpath("//div[text()='" + productName + "']"));
        product.click();

        WebElement addToCartButton = driver.findElement(By.xpath("//button[text()='ADD TO CART']"));
        addToCartButton.click();
    }


    public void finishPurchase() {

        WebElement cartIcon = driver.findElement(By.id("shopping_cart_container"));
        cartIcon.click();

        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

        WebElement firstNameField = driver.findElement(By.id("first-name"));
        firstNameField.sendKeys("Andjela");

        WebElement lastNameField = driver.findElement(By.id("last-name"));
        lastNameField.sendKeys("Jovanovic");

        WebElement zipCodeField = driver.findElement(By.id("postal-code"));
        zipCodeField.sendKeys("12345");

        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();
    }

    public boolean isOrderCompleted() {
        try {
            WebElement confirmationMessage = driver.findElement(By.xpath("//div[contains(text(), 'Order Completed')]"));
            return confirmationMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
