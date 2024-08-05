package SwagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

public class viewProductPage {

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
        System.out.println("=========== View Products Page Functionality===========");

        //Enter the username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        //Enter the password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        //Click the login button
        driver.findElement(By.id("login-button")).click();
    }
    @Test(priority = 1)
    public void clickOnTheProductName() throws InterruptedException {
        System.out.println("===== Test 001- Testing the view Product Page by clicking on the Product Name =====");

        //Verify the Product Name.
        expectedText= driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();
        Thread.sleep(3000);

        //Click on the product name
        driver.findElement(By.id("item_4_title_link")).click();

        //Verify whether the clicked product is displayed
        actualText= driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")).getText();

        Thread.sleep(3000);
        if(expectedText.equals(actualText)){
            System.out.println("Page Navigated- PASS");
        }
        else{
            System.out.println("Page Navigated- FAIL");
        }
        //Click the button to go to previous page
        clickBackButton();
    }
    @Test(priority = 2)
    public void clickOnProductImage() throws InterruptedException {
        System.out.println("===== Test 002- Testing the view Product Page by clicking on the Product Image =====");

        //Verify the Product name
        expectedText=driver.findElement(By.id("item_4_title_link")).getText();

        //click on the product image
        driver.findElement(By.xpath("//*[@id=\"item_4_img_link\"]/img")).click();

        //verify whether the actual product is displayed
        actualText=driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")).getText();

        Thread.sleep(3000);
        if(expectedText.equals(actualText)){
            System.out.println("Page Navigated- PASS");
        }
        else{
            System.out.println("Page Navigated- FAIL");
        }
        //Click the button to go to previous page
        clickBackButton();
    }
    @Test(priority = 3)
    public void checkItemDetails() throws InterruptedException {
        System.out.println("===== Test 003- Testing the view Product Page to check item details =====");

        //Verify the Product name
        String expectedName=driver.findElement(By.id("item_4_title_link")).getText();

        //Verify the product description
        String expectedDescription= driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[1]/div")).getText();

        //Verify the product price
        String expectedPrice= driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div")).getText();

        //Click on the product name
        driver.findElement(By.id("item_4_title_link")).click();

        Thread.sleep(3000);
        //Verify the actual product name
        String actualName=driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")).getText();

        //Verify the actual product description
        String actualDescription=driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[2]")).getText();

        //Verify the actual product price
        String actualPrice=driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[3]")).getText();

        if (actualName.equals(expectedName) && actualDescription.equals(expectedDescription) && actualPrice.equals(expectedPrice)){
            System.out.println("Page Navigated- PASS");
        }
        else{
            System.out.println("Page Navigated- FAIL");
        }
        //Click the button to go to previous page
        clickBackButton();
    }
    //Supportive Methods
    public void clickBackButton(){driver.findElement(By.id("back-to-products")).click();
    }}

