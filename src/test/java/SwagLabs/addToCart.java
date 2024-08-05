package SwagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class addToCart {

    String BaseURL = "https://www.saucedemo.com/";
    WebDriver driver;
    String actualText;
    String expectedText;

    //Before test section
    @BeforeTest
    public void BeforeTestMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BaseURL);
        System.out.println("=========== Add to Cart Functionality===========");
        //Enter the username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        //Enter the password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //Click the login button
        driver.findElement(By.id("login-button")).click();
    }
    @Test(priority =1)
    public void itemAddToCartNotification() throws InterruptedException {
        System.out.println("===== Test- 001 Testing the Item Add to Cart Notification =====");

        clickAddToCartButton();
        Thread.sleep(3000);
    //Verify the item notification
    int expectedCartItem= 1;
    int actualCartItem= Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText());
    if (Objects.equals(actualCartItem, expectedCartItem)){
        System.out.println("Item Notified - PASS");
    }
    else{
        System.out.println("Item Notified- FAIL");
    }}
    @Test(priority = 2)
    public void changeToRemoveButton() {
        System.out.println("===== Test- 002 Testing the Change to Remove Button =====");

        expectedText = "Remove";
        actualText = driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]")).getText();
        if (expectedText.equals(actualText)) {
            System.out.println("Item Removed - PASS");
        } else {
            System.out.println("Item Removed- FAIL");
        }}
    @Test (priority = 3)
    public void checkCartDetails(){
        System.out.println("===== Test- 003 Check whether the product details are correct after adding it to the cart =====");
        //click on the notification  icon
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

        //Verify the page name
        String expectedPageName= "Your Cart";
        String actualPageName= driver.findElement(By.className("title")).getText();

        //Verify the product name
        String expectedName="Sauce Labs Backpack";
        String actualName= driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]")).getText();

        //Verify the product description
        String expectedDescription= "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
        String actualDescription= driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[1]")).getText();

        //Verify the product price
        String expectedPrice="$29.99";
        String actualPrice= driver.findElement(By.className("inventory_item_price")).getText();

        if(expectedPageName.equals(actualPageName) && expectedName.equals(actualName) && expectedDescription.equals(actualDescription) && expectedPrice.equals(actualPrice)){
            System.out.println(" Displayed Correct Details - PASS");
        } else {
            System.out.println("Displayed Correct Details- FAIL");
        }
        }
    @Test(priority = 4)
    public void removeCartItem() throws InterruptedException {
        System.out.println("===== Test- 004 Testing whether after clicking the Remove btn, the item is removed from the cart  =====");
        clickAddToCartButton();
        Thread.sleep(3000);
        //clickRemoveButton();
        //OR the code below can be used to  execute this,ensure the page is fully loaded and element is visible before trying to click it.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement removeButton;
        removeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory")));
        removeButton.click();

        //Check the item is removed
        //If the product item is not listed,it is removed successfully.
        //Find the element and assign it to the list and check whether the list is empty, if empty no such element, then it is removed
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
        if (elements.isEmpty()) {
            System.out.println(" Item Removed from the cart-PASS");
        } else {
            System.out.println(" Item Removed from the cart-FAIL");
        }
        Thread.sleep(2000);}
    @Test(priority = 5)
    public void itemRemoveCartNotification() throws InterruptedException {
        System.out.println("===== Test- 005 Testing if the cart icons removes the notification after the product item is removed  =====");
        clickAddToCartButton();
        Thread.sleep(3000);
        //clickRemoveButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement removeButton;
        removeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory")));
        removeButton.click();
        //if the span tag for product count is not available, the added item has been removed
        List <WebElement> sets= driver.findElements(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
        if (sets.isEmpty()) {
            System.out.println(" Cart Icon removed notification- PASS");
        } else {
            System.out.println(" Cart Icon removed notification- FAIL");
        }
    }
    //Supportive cases
    public void clickAddToCartButton() throws InterruptedException {
     //Click on the add to cart button
     driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
     Thread.sleep(3000);
    }
     public void clickRemoveButton() throws InterruptedException {
        //Click on the Remove button
        driver.findElement(By.className("btn_secondary")).click();
        Thread.sleep(3000);}}

