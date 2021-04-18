package com.sample.test.demo.bean;

import com.sample.test.demo.utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PizzaOrderForm extends PageObject {

    public PizzaOrderForm(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "pizza1Pizza")
    private WebElement pizzaType;

    @FindBy(xpath = "//div[@id='pizza1']//select[@class='toppings1']")
    private WebElement pizzaToppings1;

    @FindBy(xpath = "//div[@id='pizza1']//select[@class='toppings2']")
    private WebElement pizzaToppings2;

    @FindBy(id = "pizza1Qty")
    private WebElement pizzaQuantity;

    @FindBy(id = "pizza1Cost")
    private WebElement pizzaCost;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "ccpayment")
    private WebElement radioCardPayment;

    @FindBy(id = "cashpayment")
    private WebElement radioCashPayment;

    @FindBy(id = "placeOrder")
    private WebElement placeOrderButton;

    @FindBy(id = "reset")
    private WebElement resetButton;

    @FindBy(id = "dialog")
    private WebElement dialog;

    @FindBy(xpath = "//div[@id='dialog']/p")
    private WebElement dialogText;

    public boolean isInitialized() {
        return pizzaType.isDisplayed();
    }

    public void enterPizzaDetails(String pizzaType, String pizzaToppings1, String pizzaToppings2, String pizzaQuantity, String pizzaCost) {
        this.pizzaType.sendKeys(pizzaType);

        this.pizzaToppings1.sendKeys(pizzaToppings1);

        this.pizzaToppings2.sendKeys(pizzaToppings2);

        this.pizzaQuantity.sendKeys(pizzaQuantity);

        this.pizzaCost.sendKeys(pizzaCost);
    }

    public void enterPickUpDetails(String email, String name, String phone) {
        this.email.sendKeys(email);

        this.name.sendKeys(name);

        this.phone.sendKeys(phone);
    }

    public void selectPaymentMode(Boolean radioCardPayment, Boolean radioCashPayment) {
        if (radioCardPayment) {
            this.radioCardPayment.click();
        }
        if (radioCashPayment) {
            this.radioCashPayment.click();
        }
    }

    public void placeOrder() {
        this.placeOrderButton.click();
    }

    public void reset() {
        this.resetButton.click();
    }

    public Boolean verifyOrderPlacedMessage() {
        System.out.println("dialog ..." + this.dialogText.getText());
        return this.dialogText.isDisplayed();
    }

    public Boolean verifyResetFunctioning() {
//        List<WebElement> listOfElements = driver.findElements(By.xpath("//*"));
        List<WebElement> listOfElements = driver.findElements(By.xpath("//input"));
        System.out.println("listOfElements ..." + listOfElements.size());
        for (WebElement element : listOfElements) {
            System.out.println("listOfElements ..." + element.getAttribute("id"));
            if (element.getAttribute("type").equals("radio")) {
                if (element.isSelected()) {
                    System.out.println(" The element not cleared");
                    return false;
                }
            } else {
                String value = element.getAttribute("value");
                if (!((value == null || value.isEmpty()) || (Integer.valueOf(value) == 0))) {
                    System.out.println("The element value not cleared ..." + value);
                    return false;
                }
            }
        }

        List<WebElement> listOfDivElements = driver.findElements(By.xpath("//select"));
        System.out.println("listDivOfElements ..." + listOfDivElements.size());
        for (WebElement element : listOfDivElements) {
            System.out.println("listOfDivElements ..." + element.getAttribute("id"));
            String value = element.getAttribute("value");
            if (!((value == null || value.isEmpty()) || (value.contains("$00.00")))) {
                System.out.println("The div element value not cleared ..." + value);
                return false;
            }
        }
        return true;
    }
}