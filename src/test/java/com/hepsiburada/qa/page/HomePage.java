package com.hepsiburada.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {

    By myCart = By.id("shoppingCart");
    By logo = By.cssSelector("[title='Hepsiburada']");
    By cerezleriKabulEtBtn = By.id("onetrust-accept-btn-handler");
    By elektronikMenu = By.xpath("//*[@class='sf-MenuItems-WulWXvlfIAwNiOUGY7FP']//span[contains(text(),'Elektronik')]");
    By bilgisayarTablet = By.xpath("//*[@class='sf-ChildMenuItems-a4G0z3YJJWkQs7qKKuuj']//li//a[contains(text(),'Bilgisayar/Tablet')]");
    By noteBook = By.xpath("//*[contains(@class,'navigationContainer')]//*[@class='sf-ChildMenuItems-xEXsQvaJD0cslMw9rBZM']//li//span[text()='Notebook']");
    By markalarFilter = By.cssSelector("#markalar [data-test-id='filterbox-content-children'] label div[class^='moria-Checkbox']");
    By sortingBox = By.cssSelector(".horizontalSortingBar-vJPnTMeyShn8REmw3_On [data-test-id='dropdown-container']");
    By sortingBarList = By.cssSelector("[class^='moria-Dropdown'] a label");
    By productContainer = By.cssSelector(".moria-ProductCard-joawUM");
    By productTitle = By.cssSelector("[data-test-id='product-card-name']");
    By addToCartBtn = By.cssSelector("[data-test-id='product-info-button']");
    By toastMessageText = By.cssSelector(".hb-toast-text");
    By liveChatXBtn = By.id("zendeskCustomLauncherClose");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void cerezleriKabulEt() {
        if (isElementDisplayed(cerezleriKabulEtBtn)) {
            clickElement(cerezleriKabulEtBtn);
        }
    }

    public boolean isLogoDisplayed() {
        return isElementDisplayed(logo);
    }

    public void selectNotebook() {
        hoverElement(elektronikMenu);
        hoverElement(bilgisayarTablet);
        clickElement(noteBook);
    }

    public void selectBrandByName(String brandName) {
        waitForPageLoad();
        scrollToElement(markalarFilter);
        wait(4);
        List<WebElement> elements = findElements(markalarFilter);
        clickElementByListItem(elements, brandName);
        wait(5);
    }

    public void selectSortingFilter(String filter) {
        clickElement(sortingBox);
        List<WebElement> elements = findElements(sortingBarList);
        clickElementByListItem(elements, filter);
    }

    public String selectProductByIndex(int index) {
        wait(4);
        WebElement p = findElements(productContainer).get(index);
        String pTitle = findElements(productTitle).get(index).getText();
        hoverElement(p);
        clickElement(p.findElement(addToCartBtn));
        return pTitle;
    }

    public boolean checkToastMessage(String toastMessage) {
        WebElement element = findElement(toastMessageText);
        return element.getText().contains(toastMessage);
    }

    public void clickMyCart() {
        clickElement(myCart);
    }

    public void clickIfExistLiveChat() {
        try {
            if (driver.findElement(liveChatXBtn) != null) {
                clickElement(liveChatXBtn);
            }
        } catch (Exception ignored) {

        }
    }
}
