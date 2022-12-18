package com.hepsiburada.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actions = new Actions(driver);
    }

    public WebElement findElement(By selector) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch (Exception e) {
            Assert.fail(selector + " element not found");
        }
        return null;
    }

    public List<WebElement> findElements(By selector) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
            return driver.findElements(selector);
        } catch (Exception e) {
            Assert.fail(selector + " element not found");
        }
        return null;
    }

    public void clickElement(By selector) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(selector)).click();
        } catch (Exception e) {
            Assert.fail(selector + " element is not clickable");
        }
    }

    public void clickElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            Assert.fail(element + " element is not clickable");
        }
    }

    public boolean isElementDisplayed(By selector) {
        try {
            return findElement(selector).isDisplayed();
        } catch (Exception e) {
            Assert.fail(selector + " element is not displayed");
        }
        return false;
    }

    public String getText(By selector) {
        return findElement(selector).getText();
    }

    public void hoverElement(By selector) {
        actions.moveToElement(findElement(selector)).perform();
    }

    public void hoverElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (Exception ignored) {

        }
    }

    public boolean checkUrlContainsText(String text) {
        return driver.getCurrentUrl().contains(text);
    }

    public void clickElementByListItem(List<WebElement> elements, String text) {
        boolean isElementFounded = false;
        for (WebElement element :
                elements) {
            if (element.getText().trim().equalsIgnoreCase(text)) {
                clickElement(element);
                isElementFounded = true;
                break;
            }
        }

        if (!isElementFounded)
            Assert.fail(text + " değerini içeren element bulunamadı");
    }

    public boolean elementListContainsText(List<WebElement> elements, String text) {
        for (WebElement element :
                elements) {
            if (element.getText().trim().equalsIgnoreCase(text)) {
                return true;
            }
        }
        return false;
    }

    public void jsClick(By selector) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", findElement(selector));
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void scrollToElement(By selector) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', inline: 'center', block: 'center'});", findElement(selector));
    }
}
