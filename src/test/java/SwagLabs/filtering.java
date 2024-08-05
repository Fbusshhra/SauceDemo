package SwagLabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class filtering {
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
    public void priceLowToHigh() {
        System.out.println("===== Test 004 - Testing whether the price is sorted from Low to High =====");

        // 1. Before filter, locate all the price elements
        List<WebElement> beforeFilterPrice = driver.findElements(By.xpath("//*[@id=\"inventory_container\"]/div/div/div[2]/div[2]/div"));
        // 1.1 iterate over the list of price elements to remove the $ and convert the string to double,
        // then store them in the beforeFilterPriceList
        List<Double> beforeFilterPriceList = new ArrayList<>();
        for (WebElement p : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.valueOf(p.getText().replace("$", "")));
        }

        // 2. Filter the prices from the dropdown
        Select dropDown = new Select(driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select")));
        dropDown.selectByVisibleText("Price (low to high)");

        // 3. To wait for 10s until the page is updates with the sorted prices. (after filter)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Adjust this value based on the actual lowest price expected
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div"), "$7.99"));

        // 4. Locate all the price elements after applying the filter
        List<WebElement> afterFilterPrice = driver.findElements(By.xpath("//*[@id=\"inventory_container\"]/div/div/div[2]/div[2]/div"));
        // 4.1 iterate over the list of price elements to remove the $ and convert the string to double,
        // then store them in the afterFilterPriceList
        List<Double> afterFilterPriceList = new ArrayList<>();
        for (WebElement p : afterFilterPrice) {
            //p.getText----get the price. replace-----remove the $, assign to double and add to afterFilterPriceList
            afterFilterPriceList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        // 5. Compare the values / Assert the values.
        // 5.1 Sort the values in the beforeFilterPriceList
        Collections.sort(beforeFilterPriceList);

        // Assert that the prices are sorted from low to high
        assertEquals(afterFilterPriceList, beforeFilterPriceList, "The price is not sorted from Low to High");
        System.out.println("The price is sorted from Low to High");
    }
    @Test (priority = 2)
    public void priceHighToLow(){
        System.out.println("===== Test 005 - Testing whether the price is sorted from High to Low =====");

        // 1. Before filter, locate all the price elements
        List<WebElement> beforeFilterPrice= beforeFilterPrice();
        List<Double> beforeFilterPriceList = beforeFilterPriceList(beforeFilterPrice);

        // 2. Filter the prices from the dropdown
        Select dropDown = new Select(driver.findElement(By.className("product_sort_container")));
        dropDown.selectByVisibleText("Price (high to low)");

        // 3. Wait for 10s until the page updates with the sorted prices (after filter)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Adjust this value based on the actual highest price expected
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div"), "$49.99"));

        List<WebElement> afterFilterPrice= afterFilterPrice();
        List<Double> afterFilterPriceList= afterFilterPriceList(afterFilterPrice);

        // 5. Compare the values / Assert the values
        // 5.1 Sort the values in the beforeFilterPriceList
        Collections.sort(beforeFilterPriceList); // default this will do the sort in ascending order
        Collections.reverse(beforeFilterPriceList); // reverse to high to low order

        assertEquals(afterFilterPriceList, beforeFilterPriceList, "The price is not sorted from High to Low");
        System.out.println("The price is sorted from High to Low");
    }
    @Test(priority = 3)
    public void priceFromAToZ(){
        System.out.println("===== Test 006 - Testing whether the price is sorted from A To Z =====");

        // 1. Before filter, locate all the price elements
        List<WebElement> beforeFilterName= driver.findElements(By.cssSelector(".inventory_item_name"));

        //2. Extract the test from the web elements and store them in a list
        List<String> beforeFilterNameList= new ArrayList<>();
        for(WebElement p: beforeFilterName){
            beforeFilterNameList.add(p.getText());
        }
        // 3. Filter the prices from the dropdown
        Select dropDown= new Select(driver.findElement(By.className("product_sort_container")));
        dropDown.selectByVisibleText("Name (A to Z)");

        // 4. To wait for 10s until the page is updates with the sorted prices. (after filter)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"item_4_title_link\"]"), "Sauce Labs Backpack"));

        // 5. Locate all the product name elements after applying the filter
        List<WebElement> afterFilterName= driver.findElements(By.cssSelector(".inventory_item_name"));
        //5.1 Extract the test from the web elements and store them in a list
        List<String> afterFilterNameList= new ArrayList<>();
        for (WebElement p: afterFilterName){
            afterFilterNameList.add(p.getText());
        }
        //Compare the values / Assert the values.
        //Sort the values in the beforeFilterNameList
        Collections.sort(beforeFilterNameList);

        assertEquals(afterFilterNameList.size(), beforeFilterNameList.size(), "The list is not in the same size");
        assertEquals(beforeFilterNameList,afterFilterNameList, "The Price is not sorted from A To Z");
        System.out.println("The Price is sorted from A To Z");
    }
    @Test(priority = 4)
    public void priceFromZToA(){
        System.out.println("===== Test 006 - Testing whether the price is sorted from Z To A =====");
        List<WebElement> beforeFilterName= driver.findElements(By.cssSelector(".inventory_item_name"));

        List<String> beforeFilterNameList= new ArrayList<>();
        for (WebElement p: beforeFilterName){
            beforeFilterNameList.add(p.getText());  }

        Select dropDown= new Select(driver.findElement(By.className("product_sort_container")));
        dropDown.selectByVisibleText("Name (Z to A)");
        WebDriverWait Wait =new WebDriverWait(driver, Duration.ofSeconds(10));
        Wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"item_3_title_link\"]"),"Test.allTheThings() T-Shirt (Red)"));

        List<WebElement> afterFilterName= driver.findElements(By.cssSelector(".inventory_item_name"));
        List<String> afterFilterNameList= new ArrayList<>();
        for(WebElement p: afterFilterName){
            afterFilterNameList.add(p.getText());
        }
        Collections.sort(beforeFilterNameList);
        Collections.reverse(beforeFilterNameList);
        assertEquals(afterFilterNameList, beforeFilterNameList, "The Price is not sorted from Z To A");
        System.out.println("The price is sorted from Z To A");
    }
    //Supporting methods
    public List<WebElement> beforeFilterPrice() {
        // 1. Before filter, locate all the price elements
        List<WebElement> beforeFilterPrice = driver.findElements(By.xpath("//*[@id=\"inventory_container\"]/div/div/div[2]/div[2]/div"));
        return beforeFilterPrice;
    }
    public List<Double> beforeFilterPriceList(List<WebElement> beforeFilterPrice) {
        // 1.1 iterate over the list of price elements to remove the $ and convert the string to double,
        // then store them in the afterFilterPriceList
        List<Double> beforeFilterPriceList = new ArrayList<>();
        for (WebElement p : beforeFilterPrice) {
            beforeFilterPriceList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        return beforeFilterPriceList;
    }
    public List<WebElement> afterFilterPrice(){
        // 4. Locate all the price elements after applying the filter
        List<WebElement> afterFilterPrice = driver.findElements(By.xpath("//*[@id=\"inventory_container\"]/div/div/div[2]/div[2]/div"));
        return afterFilterPrice;
    }
    public List<Double> afterFilterPriceList(List<WebElement> afterFilterPrice){
        // 4.1 Iterate over the list of price elements to remove the $ and convert the string to double,
        // then store them in the afterFilterPriceList
        List<Double> afterFilterPriceList = new ArrayList<>();
        for (WebElement p : afterFilterPrice) {
            afterFilterPriceList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        return afterFilterPriceList;
    }}
