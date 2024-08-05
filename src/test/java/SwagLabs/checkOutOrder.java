package SwagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class checkOutOrder {
    String BaseURL = "https://www.saucedemo.com/";
    WebDriver driver;
    String actualText;
    String expectedText;
    List <Double> doubleListPrice= new ArrayList<>();

    //Before test section
    @BeforeTest
    public void BeforeTestMethod() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BaseURL);
        System.out.println("=========== Check Out and Place Order Functionality ===========");
        //Enter the username and password
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(3000);
        //Click the login button
        driver.findElement(By.id("login-button")).click();

        //Add item to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        //click the cart icon
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]")).click();
        //Click the checkout button
        driver.findElement(By.id("checkout")).click();
        // Enter the first name
        driver.findElement(By.id("first-name")).sendKeys("Bushra");
        //Enter the last name
        driver.findElement(By.id("last-name")).sendKeys("Ajmal");
        //Enter the postal code
        driver.findElement(By.id("postal-code")).sendKeys("6000");
        Thread.sleep(3000);
        //click the continue button
        driver.findElement(By.id("continue")).click();
}
    @Test(priority = 1)
    public void checkProductName(){
        System.out.println("===== Test- 001 Verify whether the correct Product Name is displayed =====");
        //Verify the product name
        expectedText="Sauce Labs Backpack";
        actualText=driver.findElement(By.id("item_4_title_link")).getText();
        if(expectedText.equals(actualText)){
            System.out.println("Correct Product Name is displayed - PASS");
        } else {
            System.out.println("Correct Product Name is displayed- FAIL");
        }
    }
    @Test(priority = 2)
    public void totalPriceCalculation() {
        System.out.println("===== Test- 002 Verify the Total Price Calculated =====");

        //Get the product Price
        String productPrice = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[6]")).getText();

        //Using regex to extract the double numbers from the product price string and assign to list
        doubleListPrice = extractDoubleNumbers(productPrice);
        System.out.println("Product Price: " + doubleListPrice.get(0));

        //Get tax amount
        String taxAmount = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]")).getText();

        //Using regex to extract the double numbers from the product tax string and assign to list
        doubleListPrice = extractDoubleNumbers(taxAmount);
        System.out.println("Tax Amount: " + doubleListPrice.get(1));

        //expected total price
        double expectedTotalPrice = doubleListPrice.get(0) + doubleListPrice.get(1);
        System.out.println("Expected Total Price:  " + expectedTotalPrice);

        //Actual total price
        String actualTotalPrice = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]")).getText();

        //Using regex to extract the double numbers from the Total Price string and assign to list
        doubleListPrice = extractDoubleNumbers(actualTotalPrice);
        System.out.println("Total Price: " + doubleListPrice.get(2));

        //Compare expected prices with actual prices
        if (Objects.equals(expectedTotalPrice, doubleListPrice.get(2))) {
            System.out.println("Displayed Accurate Total Price- PASS");
        } else {
            System.out.println("Displayed Accurate Total Price- FAIL");
        }
    }
    @Test(priority = 3)
    public void clickFinishButton(){
        System.out.println("===== Test- 003 Verify whether after clicking the Finish Button, navigated to Complete Page =====");
            driver.findElement(By.id("finish")).click();

    //Verify it navigates to CheckOut Completed Page
    expectedText="Checkout: Complete!";
    actualText=driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        if(expectedText.equals(actualText)){
            System.out.println("Navigated to CheckOut Completed Page! - PASS");
        } else {
            System.out.println("Navigated to CheckOut Completed Page!- FAIL");
        }
        }
    @Test(priority = 4)
    public void clickBackToHome(){
        System.out.println("===== Test- 004 Verify whether after clicking the Back To Home Btn, navigated to Products Page =====");
        //Click the Back to Home Button
        driver.findElement(By.id("back-to-products")).click();

        //Verify it navigates to Products Page
        expectedText= "Products";
        actualText= driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        if(expectedText.equals(actualText)){
            System.out.println("Navigated to Products Page! - PASS");
        } else {
            System.out.println("Navigated to Products Page!- FAIL");
        }
    }
    //supportive methods
    public List<Double> extractDoubleNumbers(String input) {
        Pattern pattern= Pattern.compile("\\b\\d+\\.\\d+\\b");
        Matcher matcher= pattern.matcher(input);

        //Find all matches and store them in a list
        while ((matcher.find())){
            //Add the matched double number to the list
            doubleListPrice.add(Double.valueOf(matcher.group()));
        }
        return doubleListPrice;
    }
    }

