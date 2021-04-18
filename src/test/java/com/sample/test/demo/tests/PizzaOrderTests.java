package com.sample.test.demo.tests;

import com.sample.test.demo.TestBase;
import com.sample.test.demo.bean.PizzaOrderForm;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PizzaOrderTests extends TestBase {

    private final String MISSING_REQUIRED_NAME = "Missing name";
    private final String MISSING_REQUIRED_PHONE = "Missing phone number";
    private final String MISSING_PIZZA_QUANTITY = "Thank you for your order! TOTAL: 0";

    @Test(testName = "Submit Pizza Order", priority = 1)
    public void validatePizzaOrder() {
        PizzaOrderForm pizzaOrderForm = new PizzaOrderForm(driver);
        assertTrue(pizzaOrderForm.isInitialized());

        pizzaOrderForm.enterPizzaDetails("Small 6 Slices - no toppings $6.75", "Mushrooms", "Olives", "1", "6.75");
        pizzaOrderForm.enterPickUpDetails("testabc@gmail.com", "Test Abc", "1234567890");
        pizzaOrderForm.selectPaymentMode(true, false);
        pizzaOrderForm.placeOrder();

        assertTrue(pizzaOrderForm.verifyOrderPlacedMessage());
    }

    @Test(testName = "Clear Pizza Order", priority = 0)
    public void validateReset() {
        PizzaOrderForm pizzaOrderForm = new PizzaOrderForm(driver);
        assertTrue(pizzaOrderForm.isInitialized());

        pizzaOrderForm.enterPizzaDetails("Small 6 Slices - no toppings $6.75", "Mushrooms", "Olives", "1", "6.75");
        pizzaOrderForm.enterPickUpDetails("testabc@gmail.com", "Test Abc", "1234567890");
        pizzaOrderForm.selectPaymentMode(true, false);
        pizzaOrderForm.reset();

        assertTrue(pizzaOrderForm.verifyResetFunctioning());
    }

    @Test(testName = "Missing Pizza Quantity", priority = 2)
    public void validateFlowWithMissingPizzaQty() {
        PizzaOrderForm pizzaOrderForm = new PizzaOrderForm(driver);
        assertTrue(pizzaOrderForm.isInitialized());

        pizzaOrderForm.enterPizzaDetails("Small 6 Slices - no toppings $6.75", "Mushrooms", "Olives", "0", "6.75");
        pizzaOrderForm.enterPickUpDetails("testabc@gmail.com", "Test Abc", "1234567890");
        pizzaOrderForm.selectPaymentMode(true, false);
        pizzaOrderForm.placeOrder();

        assertTrue(pizzaOrderForm.verifyOrderPlacedMessage());
        assertEquals(MISSING_PIZZA_QUANTITY, driver.findElement(By.xpath("//div[@id='dialog']/p")).getText());
    }

    @Test(testName = "Missing Customer Name", priority = 3)
    public void validateRequiredPickUpInfo_Name() {
        PizzaOrderForm pizzaOrderForm = new PizzaOrderForm(driver);
        assertTrue(pizzaOrderForm.isInitialized());

        pizzaOrderForm.enterPizzaDetails("Small 6 Slices - no toppings $6.75", "Mushrooms", "Olives", "1", "6.75");
        pizzaOrderForm.enterPickUpDetails("testabc@gmail.com", "", "1234567890");
        pizzaOrderForm.selectPaymentMode(true, false);
        pizzaOrderForm.placeOrder();

        assertTrue(pizzaOrderForm.verifyOrderPlacedMessage());
        assertTrue(MISSING_REQUIRED_NAME.equalsIgnoreCase(driver.findElement(By.xpath("//div[@id='dialog']/p")).getText()));
    }

    @Test(testName = "Missing Customer PhoneNo", priority = 4)
    public void validateRequiredPickUpInfo_Phone() {
        PizzaOrderForm pizzaOrderForm = new PizzaOrderForm(driver);
        assertTrue(pizzaOrderForm.isInitialized());

        pizzaOrderForm.enterPizzaDetails("Small 6 Slices - no toppings $6.75", "Mushrooms", "Olives", "1", "6.75");
        pizzaOrderForm.enterPickUpDetails("testabc@gmail.com", "Test Abc", "");
        pizzaOrderForm.selectPaymentMode(true, false);
        pizzaOrderForm.placeOrder();

        assertTrue(pizzaOrderForm.verifyOrderPlacedMessage());
        assertTrue(MISSING_REQUIRED_PHONE.equalsIgnoreCase(driver.findElement(By.xpath("//div[@id='dialog']/p")).getText()));
    }

    @AfterMethod
    public void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        driver.findElements(By.xpath("//*")).clear();
    }
}
