package SwagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class loginPage {

    String BaseURL= "https://www.saucedemo.com/";
    WebDriver driver;
    String actualText;
    String expectedText;
    Boolean status;

    //Before test section
    @BeforeTest
    public void BeforeTestMethod(){
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("===========Login Functionality===========");
    }
    //TestCase section
    //TestCase- Login by correct credentials - TC-001
    @Test (priority = 1)
    public void correctCredentialLogin() throws InterruptedException {
    System.out.println("=====TC001- Testing the Login Functionality with the Correct Credentials =====");

    //Load the Login Page
    loadLoginPage();

    //Enter Correct Username
    enterCorrectUsername();

    //Enter Correct Password
    enterCorrectPassword();

    //Click the Login Button
    clickLoginButton();

    //Verify if the navigates to the products page
    expectedText = "Products";
    actualText= driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();

    if(expectedText.equals(actualText)){
        System.out.println("Page Navigated Pass");
    }
    else {
        System.out.println(" Page Navigated Fail");
    }
    }
    //TestCase- Login by incorrect credentials - TC-002
    @Test(priority = 2)
    public void incorrectCredentialLogin() throws InterruptedException {
    System.out.println("===== TC002-Testing the Login Functionality with the Incorrect Credentials =====");

    //Load the Login Page
    loadLoginPage();

    //Enter the username
    driver.findElement(By.id("user-name")).sendKeys("Admin");

    //Enter Password
    driver.findElement(By.id("password")).sendKeys("Admin123");

    //Click the Login Button
    clickLoginButton();

    //Verify if the navigates to the products page
    expectedText = "Epic sadface: Username and password do not match any user in this service";
    actualText= driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
    if(expectedText.equals(actualText)){
    System.out.println("Display Error message - Pass");
    }
    else{
    System.out.println("Display Error message - Fail");
    }
    }
    //TestCase- Login by only username - TC-003
    @Test(priority = 3)
    public void onlyUsername() throws InterruptedException {
    System.out.println("===== TC003-Testing the Login Functionality by only Username =====");

    //Load the Login Page
    loadLoginPage();

    //Enter Correct Username
    enterCorrectUsername();

    //Click the Login Button
    clickLoginButton();

    //Verify if the navigates to the products page
    expectedText = "Epic sadface: Password is required";
    actualText= driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
    if(expectedText.equals(actualText)){
    System.out.println("Display Error message - Pass");
        }
    else{
            System.out.println("Display Error message - Fail");
        }
    }
    //TestCase- Login by only Password - TC-004
    @Test(priority = 4)
    public void onlyPassword() throws InterruptedException {
    System.out.println("===== TC004-Testing the Login Functionality by only password =====");

    //Load the Login Page
    loadLoginPage();

    //Enter Correct Password
    enterCorrectPassword();

    //Click the Login Button
    clickLoginButton();

    //Verify if the navigates to the products page
    expectedText = "Epic sadface: Username is required";
    actualText = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]")).getText();
    if (expectedText.equals(actualText)) {
            System.out.println("Display Error message - Pass");
    } else {
    System.out.println("Display Error message - Fail");
        }
    }
    //TestCase- Login by null values - TC-005
    @Test(priority = 5)
    public void nullUsernamePasswordLogin() throws InterruptedException {
    System.out.println("===== TC005-Testing the Login Functionality by null password and username =====");

    //Load the Login Page
    loadLoginPage();

    //Click the Login button
    clickLoginButton();

    //Verify if the navigates to the products page
    expectedText = "Epic sadface: Username is required";
    actualText = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]")).getText();
    if (expectedText.equals(actualText)) {
            System.out.println("Display Error message - Pass");
    } else {
            System.out.println("Display Error message - Fail");
        }
    }

    //Supportive Method Section
    public void loadLoginPage() throws InterruptedException {

    //calling the Swag Lags Page
    driver.get(BaseURL);
    Thread.sleep(3000);
    }
    public void enterCorrectUsername() throws InterruptedException {
    //Identify the username box and send the value
    driver.findElement(By.id("user-name")).sendKeys("standard_user");
    Thread.sleep(3000);
    }

    public void enterCorrectPassword() throws InterruptedException {
    //Identify the password box and send the value
    driver.findElement(By.id("password")).sendKeys("secret_sauce");

    Thread.sleep(3000);
    }
    public void clickLoginButton() throws InterruptedException {
    //Click the login button
    driver.findElement(By.id("login-button")).click();

    Thread.sleep(3000);
    }
    }