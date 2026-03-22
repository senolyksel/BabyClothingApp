# Bebek Giyim Asistanı - Kurulum ve APK Oluşturma Rehberi

Bu rehber, Bebek Giyim Asistanı uygulamasını Android Studio'da kurmanız ve APK dosyasını oluşturmanız için adım adım talimatlar içerir.

## 📋 Ön Gereksinimler

Başlamadan önce aşağıdaki yazılımların kurulu olduğundan emin olun:

1. **Android Studio** (Jellyfish 2023.3.1 veya daha yeni)
   - İndirme: https://developer.android.com/studio
   - Kurulum sırasında "Android SDK" seçeneğini işaretleyin

2. **Java Development Kit (JDK)** 11 veya daha yeni
   - Android Studio içinde gelen JDK kullanabilirsiniz
   - Veya: https://www.oracle.com/java/technologies/downloads/

3. **Android SDK**
   - API Level 34 (Android 14)
   - Minimum API Level 24 (Android 7.0)

## 🚀 Adım Adım Kurulum

### Adım 1: Projeyi Android Studio'da Açın

1. **Android Studio'yu başlatın**
2. "Open" seçeneğine tıklayın
3. `BabyClothingApp` klasörünü seçin
4. "OK" butonuna tıklayın

### Adım 2: Gradle Senkronizasyonu

1. Proje açıldıktan sonra, Android Studio otomatik olarak Gradle senkronizasyonunu başlatacak
2. Sağ üst köşede "Sync Now" mesajı görünürse, tıklayın
3. Senkronizasyon tamamlanmasını bekleyin (1-2 dakika sürebilir)

### Adım 3: Android SDK'yı Kontrol Edin

1. **Tools** → **SDK Manager** seçin
2. **SDK Platforms** sekmesinde aşağıdakilerin kurulu olduğundan emin olun:
   - Android 14 (API Level 34)
   - Android 7.0 (API Level 24)
3. **SDK Tools** sekmesinde:
   - Android SDK Build-Tools (34.0.0 veya daha yeni)
   - Android Emulator
   - Android SDK Platform-Tools

### Adım 4: Emülatör Kurun (Opsiyonel)

Fiziksel cihazınız yoksa emülatör kullanabilirsiniz:

1. **Tools** → **Device Manager** seçin
2. **Create Device** butonuna tıklayın
3. **Pixel 5** seçin ve "Next" tıklayın
4. **Android 14 (API Level 34)** seçin ve "Next" tıklayın
5. "Finish" butonuna tıklayın

## 📱 Uygulamayı Çalıştırma

### Seçenek 1: Emülatörde Çalıştırma

1. Emülatörü başlatın (Device Manager'dan)
2. Android Studio'da **Run** → **Run 'app'** seçin (Shift + F10)
3. Emülatörü seçin ve "OK" tıklayın
4. Uygulama emülatörde başlayacak

### Seçenek 2: Fiziksel Cihazda Çalıştırma

1. **USB Debugging'i etkinleştirin:**
   - Cihazda: Settings → Developer Options → USB Debugging (ON)
   - Geliştirici Seçenekleri yoksa: Settings → About Phone → Build Number (7 kez tıklayın)

2. USB kablosu ile cihazı bilgisayara bağlayın

3. Android Studio'da **Run** → **Run 'app'** seçin (Shift + F10)

4. Cihazınızı seçin ve "OK" tıklayın

## 📦 APK Dosyası Oluşturma

### Adım 1: Release APK Oluştur

1. Android Studio'da **Build** menüsüne gidin
2. **Build Bundle(s) / APK(s)** → **Build APK(s)** seçin
3. Derleme başlayacak (2-5 dakika sürebilir)
4. Tamamlandığında, sağ alt köşede "Build successful" mesajı görünecek

### Adım 2: APK Dosyasını Bulun

APK dosyası şu konumda oluşturulur:

```
BabyClothingApp/app/build/outputs/apk/release/app-release.apk
```

### Adım 3: APK'yı Cihaza Yükleyin

**Terminal/Komut İsteminde:**

```bash
# Proje dizinine gidin
cd BabyClothingApp

# APK'yı cihaza yükleyin
adb install app/build/outputs/apk/release/app-release.apk
```

**Veya Android Studio'da:**

1. **Run** → **Run 'app'** seçin
2. Cihazı seçin
3. APK otomatik olarak yüklenecek

## 🔧 Sorun Giderme

### Gradle Senkronizasyonu Başarısız

**Çözüm:**
```bash
cd BabyClothingApp
./gradlew clean
./gradlew sync
```

### "Build Tools not found" Hatası

**Çözüm:**
1. Tools → SDK Manager
2. SDK Tools sekmesine gidin
3. "Show Package Details" işaretleyin
4. Android SDK Build-Tools 34.0.0 veya daha yenisini seçin
5. "OK" tıklayın

### Emülatör Başlamıyor

**Çözüm:**
1. Device Manager'ı açın
2. Emülatörü seçin
3. "Wipe Data" tıklayın
4. Tekrar başlatın

### APK Yükleme Başarısız

**Çözüm:**
```bash
# Önceki sürümü kaldırın
adb uninstall com.example.babyclothingapp

# Yeniden yükleyin
adb install app/build/outputs/apk/release/app-release.apk
```

## 📊 Uygulama Özellikleri

Kurulum tamamlandıktan sonra, uygulamada aşağıdaki özellikleri kullanabilirsiniz:

### 1. Sohbet Arayüzü
- Müşteri sorularını yazın
- AI asistanı otomatik cevap verecek
- Önerilen ürünleri göreceksiniz

### 2. Ürün Önerileri
- Yaş grubu: 0-3 Ay, 3-6 Ay, 6-12 Ay, 1-2 Yaş, 2-5 Yaş
- Cinsiyet: Erkek, Kız, Unisex
- Kategori: Elbise, Tulum, Takım, Çorap, Ayakkabı
- Bütçe: Fiyat aralığı belirleyin

### 3. Gerçek Zamanlı Veriler
- enucuzbebekmarket.com'dan güncel ürün bilgileri
- Canlı fiyatlar ve indirimler
- Ürün açıklamaları ve özellikleri

## 🔐 Güvenlik Ayarları

### Gemini API Anahtarı Ekleyin

1. [Google AI Studio](https://makersuite.google.com/) sitesine gidin
2. "Get API Key" butonuna tıklayın
3. "Create API key in new project" seçin
4. API anahtarını kopyalayın
5. `GeminiAIService.kt` dosyasında aşağıdaki satırı bulun:
   ```kotlin
   // API anahtarınızı buraya ekleyin
   ```
6. API anahtarını ekleyin

## 📝 Sık Sorulan Sorular

**S: APK dosyasını başka cihazlara yükleyebilir miyim?**
C: Evet, `app-release.apk` dosyasını başka Android cihazlara yükleyebilirsiniz.

**S: Uygulama internet bağlantısı gerektiriyor mu?**
C: Evet, enucuzbebekmarket.com'dan ürün çekmek ve Gemini AI kullanmak için internet bağlantısı gereklidir.

**S: Uygulamayı Google Play Store'da yayınlayabilir miyim?**
C: Evet, ancak gerekli izinleri ve lisansları almanız gerekir. Detaylar için [Google Play Console](https://play.google.com/console) sitesini ziyaret edin.

## 📞 Destek

Sorularınız veya sorunlarınız için lütfen iletişime geçin.

---

**Son Güncelleme:** Mart 2026  
**Sürüm:** 1.0
