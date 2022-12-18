package com.hepsiburada.qa.tests;

import com.hepsiburada.qa.base.BaseTest;
import com.hepsiburada.qa.DriverFactory;
import com.hepsiburada.qa.page.CartPage;
import com.hepsiburada.qa.page.HomePage;
import com.hepsiburada.qa.page.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.HashMap;

public class CartTest extends BaseTest {

    @Before
    public void setup() {
        dataMap = new HashMap<>();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("{string} tarayıcısı açılır")
    public void tarayici_acilir(String browser) {
        driver = DriverFactory.getDriver(browser);
    }

    @Given("Hepsiburada.com sitesi açılır")
    public void hepsiburada_com_sitesi_acilir() {
        homepage = new HomePage(driver);
        driver.get("https://www.hepsiburada.com");
        homepage.cerezleriKabulEt();
    }

    @Then("Hepsiburada.com sitesinin açıldığı doğrulanır")
    public void hepsiburada_com_sitesinin_acildigi_dogrulanir() {
        Assert.assertTrue(homepage.isLogoDisplayed(), "Hepsiburada.com logosu görüntülenemedi.");
    }

    @When("Menüden Elektronik BilgisayarTablet Notebook seçilir")
    public void menuden_notebook_secilir() {
        homepage.selectNotebook();
    }

    @When("Çıkan listeleme ekranından Marka {string} seçilir")
    public void cikan_listeleme_ekranindan_marka_secilir(String brandName) {
        homepage.selectBrandByName(brandName);
    }

    @When("Gelen ürünler {string} göre sıralama yaptırılır")
    public void gelen_urunler_gore_siralama_yaptirilir(String filter) {
        homepage.clickIfExistLiveChat();
        homepage.selectSortingFilter(filter);
        Assert.assertTrue(homepage.checkUrlContainsText("coksatan"), "Çok satanlara göre sıralama yapılamadı");
    }

    @When("{int}. ürün sepete eklenir")
    public void urun_sepete_eklenir(int index) {
        dataMap.put("productTitle", homepage.selectProductByIndex(--index));
    }

    @Then("{string} uyarı mesajı görülür")
    public void uyari_mesaji_gorulur(String text) {
        Assert.assertTrue(homepage.checkToastMessage(text), "Ürün sepete eklenmiştir mesajı görüntülenemedi");
    }

    @Then("Sepete gidilir")
    public void sepete_gidilir() {
        homepage.clickMyCart();
    }

    @Then("Eklenen ürünün sepette olduğu doğrulanır")
    public void eklenen_urunun_sepette_oldugu_dogrulanir() {
        cartpage = new CartPage(driver);
        cartpage.checkContainsCartListProduct(dataMap.get("productTitle"));
    }

    @When("Alışverisi tamamla butonuna basılır")
    public void alisverisi_tamamla_butonuna_basilir() {
        cartpage.clickContinueStepBtn();
    }

    @Then("Login ekranına yönlendirildiği kontrol edilir")
    public void login_ekranina_yonlendirildigi_kontrol_edilir() {
        loginpage = new LoginPage(driver);
        Assert.assertTrue(loginpage.isLoginBtnDisplayed(), "Giriş Yap butonu görüntülenemedi");
        Assert.assertTrue(loginpage.isEmailTxtDisplayed(), "E-Posta adresi veya telefon numarası textboxı görüntülenemedi");
    }
}
