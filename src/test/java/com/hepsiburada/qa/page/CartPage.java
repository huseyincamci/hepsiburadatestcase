package com.hepsiburada.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    By completeShoppingBtn = By.id("continue_step_btn");
    By productNames = By.cssSelector(".product_name_3Lh3t");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickContinueStepBtn() {
        clickElement(completeShoppingBtn);
    }

    public boolean checkContainsCartListProduct(String productTitle) {
        return elementListContainsText(findElements(productNames), productTitle);
    }
}
