package com.hepsiburada.qa.base;

import com.hepsiburada.qa.page.CartPage;
import com.hepsiburada.qa.page.HomePage;
import com.hepsiburada.qa.page.LoginPage;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class BaseTest {

    protected WebDriver driver;
    protected static String browser = System.getProperty("browser");
    protected HomePage homepage;
    protected LoginPage loginpage;
    protected CartPage cartpage;
    protected HashMap<String, String> dataMap;

}
