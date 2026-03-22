# Bebek Giyim Asistanı - Android Uygulaması

Gemini AI ve enucuzbebekmarket.com entegrasyonu ile müşteri sorularına cevap veren ve ürün önerileri yapan akıllı bebek giyim uygulaması.

## 📋 Özellikler

- **AI Sohbet:** Gemini AI ile müşteri sorularına akıllı cevaplar
- **Gerçek Zamanlı Ürün Verisi:** enucuzbebekmarket.com'dan güncel ürün bilgileri
- **Akıllı Ürün Önerme:** Yaş grubu, bütçe ve tercihler doğrultusunda ürün önerileri
- **Fiyat Bilgisi:** Güncel fiyatlar ve indirim bilgileri
- **Ürün Açıklamaları:** Detaylı ürün açıklamaları ve özellikleri

## 🛠️ Teknik Bilgiler

### Kullanılan Teknolojiler
- **Dil:** Kotlin
- **API:** Google Generative AI (Gemini)
- **Web Scraping:** JSoup
- **Networking:** Retrofit + OkHttp
- **Async:** Coroutines
- **UI:** RecyclerView, Material Design

### Proje Yapısı
```
BabyClothingApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/babyclothingapp/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── adapters/
│   │   │   │   │   ├── ChatAdapter.kt
│   │   │   │   │   └── ProductAdapter.kt
│   │   │   │   ├── models/
│   │   │   │   │   └── Product.kt
│   │   │   │   ├── services/
│   │   │   │   │   ├── WebScrapingService.kt
│   │   │   │   │   └── GeminiAIService.kt
│   │   │   │   └── viewmodels/
│   │   │   │       └── ChatViewModel.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── drawable/
│   │   │   │   └── values/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
└── README.md
```

## 🚀 Kurulum ve Derleme

### Gereksinimler
- Android Studio Jellyfish veya daha yeni
- Java 11 veya daha yeni
- Android SDK 34 (API Level 34)
- Minimum SDK: 24 (API Level 24)

### Adımlar

1. **Projeyi Aç**
   ```bash
   cd BabyClothingApp
   ```

2. **Android Studio'da Aç**
   - Android Studio'yu açın
   - "Open" seçeneğini tıklayın
   - BabyClothingApp klasörünü seçin

3. **Bağımlılıkları Yükle**
   - Gradle senkronizasyonu otomatik olarak başlayacak
   - Tamamlanmasını bekleyin

4. **Uygulamayı Çalıştır**
   - Emülatörü başlatın veya fiziksel cihaz bağlayın
   - "Run" butonuna tıklayın (Shift + F10)

## 📦 APK Oluşturma

### Release APK Oluştur
1. Android Studio'da "Build" menüsüne gidin
2. "Build Bundle(s) / APK(s)" → "Build APK(s)" seçin
3. Derleme tamamlandığında, APK dosyası oluşturulur
4. Dosya konumu: `app/build/outputs/apk/release/app-release.apk`

### APK'yı Cihaza Yükle
```bash
adb install app/build/outputs/apk/release/app-release.apk
```

## 🔧 Yapılandırma

### Gemini API Anahtarı
1. [Google AI Studio](https://makersuite.google.com/) sitesine gidin
2. API anahtarı oluşturun
3. `GeminiAIService.kt` dosyasında API anahtarını ekleyin

### Web Scraping
- `WebScrapingService.kt` dosyası enucuzbebekmarket.com'dan ürün çeker
- Otomatik olarak fiyat, indirim ve ürün bilgilerini parse eder

## 💬 Kullanım

1. **Uygulamayı Başlat**
   - Uygulamayı açın
   - Bebek giyim asistanı sizi karşılayacak

2. **Soru Sor**
   - Örnek: "2 yaşındaki erkek bebeğim için yazlık giyim arıyorum, bütçem 300 ₺"
   - Mesaj yazıp gönder butonuna tıklayın

3. **Önerileri Görüntüle**
   - AI, sorunuza göre ürün önerileri sunacak
   - Fiyat, açıklama ve diğer detayları görebilirsiniz

4. **Ürün Satın Al**
   - Önerilen ürünlere tıklayarak detayları görüntüleyin
   - enucuzbebekmarket.com'da satın alın

## 🔐 Güvenlik

- HTTPS bağlantıları kullanılır
- API anahtarları güvenli şekilde saklanır
- Kullanıcı verileri gizli tutulur
- ProGuard ile kod obfüskasyonu uygulanır

## 📝 Lisans

Bu proje eğitim amaçlı oluşturulmuştur.

## 📧 İletişim

Sorularınız için lütfen iletişime geçin.

## 🎯 Gelecek Özellikler

- [ ] Favorilere ekleme
- [ ] Sepete ekleme
- [ ] Satın alma geçmişi
- [ ] Kullanıcı hesabı
- [ ] Bildirimler
- [ ] Ürün karşılaştırması
- [ ] Yorum ve değerlendirmeler

---

**Geliştirici:** Manus AI  
**Sürüm:** 1.0  
**Güncelleme Tarihi:** Mart 2026
