Feature: Hepsiburada.com

  Scenario: Hepsiburada.com sitesinde uye olmadan alisveris yapilir
    Given "Edge" tarayıcısı açılır
    When Hepsiburada.com sitesi açılır
    Then Hepsiburada.com sitesinin açıldığı doğrulanır
    When Menüden Elektronik BilgisayarTablet Notebook seçilir
    And Çıkan listeleme ekranından Marka "Apple" seçilir
    And Gelen ürünler "Çok satanlar" göre sıralama yaptırılır
    When 2. ürün sepete eklenir
    Then "Ürün sepete eklendi" uyarı mesajı görülür
    And Sepete gidilir
    Then Eklenen ürünün sepette olduğu doğrulanır
    When Alışverisi tamamla butonuna basılır
    Then Login ekranına yönlendirildiği kontrol edilir
