package SwagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class userDetails {
    String BaseURL = "https://www.saucedemo.com/";
    WebDriver driver;
    String actualText;
    String expectedText;

    //Before test section
    @BeforeTest
    public void BeforeTestMethod() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BaseURL);
        System.out.println("=========== Enter User Details for Checkout Functionality===========");
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
        Thread.sleep(3000);
    }
    @Test(priority = 1 )
    public void verifyYourInfoPage() throws InterruptedException {
        System.out.println("===== Test- 001 Verify whether the user can navigate to Your Info page after clicking the checkout btn =====");

        //Click the checkout button
        driver.findElement(By.id("checkout")).click();
        //Verify whether the page is navigated to Checkout: Your Information
        expectedText="Checkout: Your Information";
        actualText= driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        if(expectedText.equals(actualText)){
            System.out.println("Navigated to Your Information Page - PASS");
        } else {
            System.out.println("Navigated to Your Information Page- FAIL");
        }
    }
    @Test(priority = 2)
    public void enterAllDetails() throws InterruptedException {
        System.out.println("===== Test- 002 Verify whether the user can fill the details and navigate to the next page =====");
        //click the first name
        enterFname();
        //enter the last name
        enterLname();
        //enter the postal code
        enterPostCode();
        //click the continue button
        clickContinueButton();

        expectedText= "Checkout: Overview";
        actualText= driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        if(expectedText.equals(actualText)){
            System.out.println("Navigated to Your Checkout Overview Page - PASS");
        } else {
            System.out.println("Navigated to Your Checkout Overview Page- FAIL");
        }
        //click the cancel button
        backToUserDetailsPage();
    }
    @Test(priority = 3)
    public void nullFname() throws InterruptedException {
        System.out.println("===== Test- 003 Verify whether an error message is displayed for the null Fname =====");
        //enter the last name
        enterLname();

        //enter the postal code
        enterPostCode();

        //click the continue button
        clickContinueButton();

        expectedText="Error: First Name is required";
        actualText=driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3")).getText();
        if(expectedText.equals(actualText)){
            System.out.println(" Display Error Message  - PASS");
        } else {
            System.out.println(" Display Error Message- FAIL");
        }
        //back to user details page
        backToUserDetailsPage();
    }
    @Test(priority = 4)
    public void nullLname() throws InterruptedException {
        System.out.println("===== Test- 004 Verify whether an error message is displayed for the null Lname =====");
        //enter the First name
        enterFname();

        //enter the postal code
        enterPostCode();

        //click the continue button
        clickContinueButton();
        expectedText="Error: Last Name is required";
        actualText= driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3")).getText();
        if(expectedText.equals(actualText)){
            System.out.println(" Display Error Message  - PASS");
        } else {
            System.out.println(" Display Error Message- FAIL");
        }
        //back to user details page
        backToUserDetailsPage();
    }
    @Test(priority = 5)
    public void nullPostalCode() throws InterruptedException {
        System.out.println("===== Test- 005 Verify whether an error message is displayed for the null Postal Code =====");
        //click the first name
        enterFname();
        //enter the Last name
        enterLname();
        //click the continue button
        clickContinueButton();
        expectedText="Error: Postal Code is required";
        actualText= driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3")).getText();
        if(expectedText.equals(actualText)){
            System.out.println(" Display Error Message  - PASS");
        } else {
            System.out.println(" Display Error Message- FAIL");
        }
        //back to user details page
        backToUserDetailsPage();
    }
    //Supportive test methods
    public void enterFname() throws InterruptedException {
        driver.findElement(By.id("first-name")).sendKeys("Bushra");
        Thread.sleep(2000);
    }

    public void enterLname() throws InterruptedException {
        driver.findElement(By.id("last-name")).sendKeys("Ajmal");
        Thread.sleep(2000);
    }

    public void enterPostCode() throws InterruptedException {
        driver.findElement(By.id("postal-code")).sendKeys("60000");
        Thread.sleep(2000);
    }
    public void clickContinueButton() throws InterruptedException {
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
    }
    public void backToUserDetailsPage() throws InterruptedException {
        driver.findElement(By.id("cancel")).click();
        //click the cart icon
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]")).click();
        //Click the checkout button
        driver.findElement(By.id("checkout")).click();

        Thread.sleep(3000);
    }
}
