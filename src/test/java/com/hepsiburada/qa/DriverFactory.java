package com.hepsiburada.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.time.Duration;
import java.util.Collections;

public class DriverFactory {

    public static WebDriver getDriver(String browser) {

        WebDriver driver = null;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches",
                Collections.singletonList("enable-automation"));



        if (browser != null) {
            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxProfile fprofile = new FirefoxProfile();
                fprofile.setPreference("security.fileuri.strict_origin_policy", false);

                FirefoxOptions foptions = new FirefoxOptions();
                foptions.setProfile(new FirefoxProfile());
                foptions.addPreference("dom.webnotifications.enabled", false);
                driver = new FirefoxDriver(foptions);
            } else if (browser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            }
        } else {
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        return driver;
    }
}
