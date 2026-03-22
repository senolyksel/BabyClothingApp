# Bebek Giyim Asistanı - Dosya Listesi

Bu belge, projedeki tüm dosyaların konumunu ve açıklamasını içerir.

## 📁 Proje Dizin Yapısı

```
BabyClothingApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/babyclothingapp/
│   │   │   │   ├── MainActivity.kt                    # Ana aktivite
│   │   │   │   ├── adapters/
│   │   │   │   │   ├── ChatAdapter.kt                 # Sohbet adaptörü
│   │   │   │   │   └── ProductAdapter.kt              # Ürün adaptörü
│   │   │   │   ├── models/
│   │   │   │   │   └── Product.kt                     # Veri modelleri
│   │   │   │   ├── services/
│   │   │   │   │   ├── GeminiAIService.kt             # AI servisi
│   │   │   │   │   └── WebScrapingService.kt          # Web scraping servisi
│   │   │   │   └── viewmodels/
│   │   │   │       └── ChatViewModel.kt               # Sohbet ViewModel
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml              # Ana aktivite layout
│   │   │   │   │   ├── item_chat_message.xml          # Sohbet mesajı layout
│   │   │   │   │   └── item_product_card.xml          # Ürün kartı layout
│   │   │   │   ├── drawable/
│   │   │   │   │   ├── bg_user_message.xml            # Kullanıcı mesajı arka planı
│   │   │   │   │   ├── bg_ai_message.xml              # AI mesajı arka planı
│   │   │   │   │   ├── bg_input_field.xml             # Input alanı arka planı
│   │   │   │   │   ├── bg_send_button.xml             # Gönder butonu arka planı
│   │   │   │   │   ├── bg_product_card.xml            # Ürün kartı arka planı
│   │   │   │   │   ├── bg_discount_badge.xml          # İndirim etiketi arka planı
│   │   │   │   │   ├── ic_placeholder.xml             # Placeholder ikonu
│   │   │   │   │   └── ic_send.xml                    # Gönder ikonu
│   │   │   │   └── values/
│   │   │   │       ├── colors.xml                     # Renkler
│   │   │   │       ├── strings.xml                    # Metin kaynakları
│   │   │   │       └── styles.xml                     # Stiller
│   │   │   └── AndroidManifest.xml                    # Uygulama manifestosu
│   │   └── test/
│   ├── build.gradle                                   # App modülü Gradle yapılandırması
│   └── proguard-rules.pro                             # ProGuard kuralları
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties                  # Gradle wrapper özellikleri
├── build.gradle                                       # Proje Gradle yapılandırması
├── settings.gradle                                    # Gradle ayarları
├── gradlew                                            # Gradle wrapper (Linux/Mac)
├── gradlew.bat                                        # Gradle wrapper (Windows)
├── README.md                                          # Proje açıklaması
├── KURULUM_REHBERI.md                                 # Kurulum talimatları
├── KAYNAK_KOD_OZETI.md                                # Kaynak kod özeti
└── DOSYA_LISTESI.md                                   # Bu dosya
```

## 📄 Dosya Açıklamaları

### Kotlin Dosyaları

#### MainActivity.kt
- **Amaç:** Uygulamanın ana ekranı
- **Sorumluluğu:** 
  - UI bileşenlerini başlatma
  - ViewModel ile bağlantı kurma
  - Mesaj gönderme işlemini yönetme
  - Hata ve yükleme durumlarını gösterme

#### adapters/ChatAdapter.kt
- **Amaç:** Sohbet mesajlarını RecyclerView'de gösterme
- **Sorumluluğu:**
  - Mesajları bağlama
  - Kullanıcı/AI mesajlarını ayırt etme
  - Ürün kartlarını gösterme

#### adapters/ProductAdapter.kt
- **Amaç:** Ürünleri yatay listede gösterme
- **Sorumluluğu:**
  - Ürün bilgilerini bağlama
  - Görsel ve fiyat gösterme
  - İndirim bilgisini gösterme

#### models/Product.kt
- **Amaç:** Ürün veri modelini tanımlama
- **İçerik:**
  - `Product` data class
  - `ProductResponse` data class

#### services/GeminiAIService.kt
- **Amaç:** AI ile müşteri sorularını işleme
- **Sorumluluğu:**
  - Soruları analiz etme
  - Ürünleri filtreleme
  - AI yanıtı oluşturma

#### services/WebScrapingService.kt
- **Amaç:** enucuzbebekmarket.com'dan ürün çekme
- **Sorumluluğu:**
  - HTTP istekleri gönderme
  - HTML parse etme
  - Ürün bilgilerini çıkarma

#### viewmodels/ChatViewModel.kt
- **Amaç:** Sohbet mantığını yönetme
- **Sorumluluğu:**
  - Mesajları depolama
  - Ürünleri yönetme
  - Yükleme durumunu takip etme

### XML Layout Dosyaları

#### layout/activity_main.xml
- **Amaç:** Ana aktivitenin arayüzü
- **Bileşenler:**
  - Header (başlık)
  - RecyclerView (sohbet)
  - EditText (mesaj girdisi)
  - ImageButton (gönder)
  - ProgressBar (yükleme göstergesi)

#### layout/item_chat_message.xml
- **Amaç:** Sohbet mesajı kartı
- **Bileşenler:**
  - TextView (mesaj metni)
  - RecyclerView (ürünler)

#### layout/item_product_card.xml
- **Amaç:** Ürün kartı
- **Bileşenler:**
  - ImageView (ürün görseli)
  - TextView (ürün adı, fiyat, açıklama)

### XML Drawable Dosyaları

#### drawable/bg_user_message.xml
- **Amaç:** Kullanıcı mesajı arka planı
- **Stil:** Pembe yuvarlatılmış köşeli şekil

#### drawable/bg_ai_message.xml
- **Amaç:** AI mesajı arka planı
- **Stil:** Gri yuvarlatılmış köşeli şekil

#### drawable/bg_input_field.xml
- **Amaç:** Mesaj girdisi alanı arka planı
- **Stil:** Açık gri kenarlı yuvarlatılmış şekil

#### drawable/bg_send_button.xml
- **Amaç:** Gönder butonu arka planı
- **Stil:** Pembe yuvarlatılmış şekil

#### drawable/bg_product_card.xml
- **Amaç:** Ürün kartı arka planı
- **Stil:** Beyaz kenarlı yuvarlatılmış şekil

#### drawable/bg_discount_badge.xml
- **Amaç:** İndirim etiketi arka planı
- **Stil:** Kırmızı küçük yuvarlatılmış şekil

#### drawable/ic_placeholder.xml
- **Amaç:** Ürün görseli placeholder
- **Stil:** Plus ikonu

#### drawable/ic_send.xml
- **Amaç:** Gönder butonu ikonu
- **Stil:** Sağa işaret eden ok

### XML Değer Dosyaları

#### values/colors.xml
- **Amaç:** Uygulamada kullanılan renkler
- **Renkler:**
  - `colorPrimary`: #FF6B9D (pembe)
  - `colorPrimaryDark`: #FF5A8A (koyu pembe)
  - `colorAccent`: #FFD700 (sarı)
  - `white`: #FFFFFF
  - `black`: #000000
  - `gray`: #808080
  - `lightGray`: #F5F5F5

#### values/strings.xml
- **Amaç:** Uygulamada kullanılan metin kaynakları
- **Metinler:**
  - `app_name`: Bebek Giyim Asistanı
  - `send_button`: Gönder
  - `message_hint`: Sorunuzu yazın...

#### values/styles.xml
- **Amaç:** Uygulamanın tema ve stilleri
- **Tema:** Material Design Light

### Gradle Dosyaları

#### build.gradle (Proje)
- **Amaç:** Proje düzeyinde Gradle yapılandırması
- **İçerik:**
  - Plugin tanımları
  - Versiyon yönetimi

#### app/build.gradle
- **Amaç:** App modülü Gradle yapılandırması
- **İçerik:**
  - Bağımlılıklar
  - Build türleri
  - Compile seçenekleri

#### settings.gradle
- **Amaç:** Gradle ayarları
- **İçerik:**
  - Repository tanımları
  - Modül tanımları

#### gradle/wrapper/gradle-wrapper.properties
- **Amaç:** Gradle wrapper yapılandırması
- **İçerik:**
  - Gradle sürümü
  - Distribution URL

### Yapılandırma Dosyaları

#### AndroidManifest.xml
- **Amaç:** Uygulamanın manifestosu
- **İçerik:**
  - İzinler (INTERNET, ACCESS_NETWORK_STATE)
  - Aktiviteler
  - Tema

#### app/proguard-rules.pro
- **Amaç:** ProGuard obfüskasyon kuralları
- **İçerik:**
  - Retrofit kuralları
  - OkHttp kuralları
  - Gson kuralları
  - Model sınıfları

### Wrapper Dosyaları

#### gradlew (Linux/Mac)
- **Amaç:** Gradle wrapper script
- **Kullanım:** `./gradlew build`

#### gradlew.bat (Windows)
- **Amaç:** Gradle wrapper batch dosyası
- **Kullanım:** `gradlew.bat build`

### Dokümantasyon Dosyaları

#### README.md
- **Amaç:** Proje açıklaması
- **İçerik:**
  - Özellikler
  - Teknik bilgiler
  - Kurulum adımları
  - Kullanım talimatları

#### KURULUM_REHBERI.md
- **Amaç:** Detaylı kurulum talimatları
- **İçerik:**
  - Ön gereksinimler
  - Adım adım kurulum
  - Sorun giderme
  - SSS

#### KAYNAK_KOD_OZETI.md
- **Amaç:** Kaynak kod açıklaması
- **İçerik:**
  - Proje mimarisi
  - Temel bileşenler
  - Veri akışı
  - Kod kalitesi

#### DOSYA_LISTESI.md
- **Amaç:** Bu belge
- **İçerik:** Dosya yapısı ve açıklamaları

## 📊 Dosya İstatistikleri

| Kategori | Sayı |
|----------|------|
| Kotlin Dosyaları | 7 |
| XML Layout Dosyaları | 3 |
| XML Drawable Dosyaları | 8 |
| XML Değer Dosyaları | 3 |
| Gradle Dosyaları | 4 |
| Yapılandırma Dosyaları | 2 |
| Wrapper Dosyaları | 2 |
| Dokümantasyon Dosyaları | 4 |
| **Toplam** | **33** |

## 🔍 Dosya Boyutları (Tahmini)

| Dosya Türü | Ortalama Boyut |
|-----------|----------------|
| Kotlin Dosyası | 5-10 KB |
| XML Layout | 2-3 KB |
| XML Drawable | 1-2 KB |
| XML Değer | 1-2 KB |
| Gradle Dosyası | 2-5 KB |
| Dokümantasyon | 10-50 KB |

## 🔐 Önemli Dosyalar

| Dosya | Açıklama | Değiştirilmesi |
|------|----------|-----------------|
| AndroidManifest.xml | İzinler ve aktiviteler | Dikkatli |
| build.gradle | Bağımlılıklar | Dikkatli |
| GeminiAIService.kt | API anahtarı | Gerekli |
| WebScrapingService.kt | Scraping mantığı | Dikkatli |

## 📝 Dosya Değişikliği Rehberi

### Yeni Özellik Ekleme

1. **Model Ekle:** `models/` klasörüne yeni model ekle
2. **Servis Güncelle:** `services/` klasöründe ilgili servisi güncelle
3. **ViewModel Güncelle:** `viewmodels/` klasöründe ViewModel'i güncelle
4. **Adapter Ekle:** Gerekirse `adapters/` klasörüne yeni adapter ekle
5. **Layout Ekle:** `res/layout/` klasörüne yeni layout ekle
6. **MainActivity Güncelle:** Yeni bileşenleri MainActivity'ye ekle

### Stil Değişikliği

1. `res/values/colors.xml` dosyasında renkleri değiştir
2. `res/values/styles.xml` dosyasında stilleri değiştir
3. Drawable dosyalarını güncelle

### Bağımlılık Ekleme

1. `app/build.gradle` dosyasında `dependencies` bloğuna ekle
2. Gradle senkronizasyonunu çalıştır
3. Projeyi rebuild et

---

**Geliştirici:** Manus AI  
**Sürüm:** 1.0  
**Güncelleme Tarihi:** Mart 2026
