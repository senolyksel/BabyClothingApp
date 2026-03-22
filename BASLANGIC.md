# 🚀 Bebek Giyim Asistanı - Başlangıç Rehberi

Hoş geldiniz! Bu rehber, Bebek Giyim Asistanı uygulamasını hızlı bir şekilde başlatmanız için tasarlanmıştır.

## ⚡ Hızlı Başlangıç (5 Dakika)

### 1. Projeyi Android Studio'da Açın
```bash
# Terminal/Komut İsteminde:
cd BabyClothingApp
# Ardından Android Studio'da: File → Open → BabyClothingApp klasörünü seçin
```

### 2. Gradle Senkronizasyonunu Bekleyin
- Android Studio otomatik olarak Gradle senkronizasyonunu başlatacak
- Sağ üst köşede "Sync Now" görünürse tıklayın
- Tamamlanmasını bekleyin (1-2 dakika)

### 3. Emülatör Başlatın veya Cihaz Bağlayın
- **Emülatör:** Device Manager → Create Device → Pixel 5 + Android 14
- **Fiziksel Cihaz:** USB ile bağlayın, Developer Options'da USB Debugging açın

### 4. Uygulamayı Çalıştırın
```
Shift + F10 (veya Run → Run 'app')
```

### 5. Uygulamayı Test Edin
- "2 yaşındaki kız bebeğim için yazlık giyim arıyorum, bütçem 300 ₺" yazın
- Gönder butonuna tıklayın
- AI önerileri ve ürünleri göreceksiniz

## 📚 Detaylı Rehberler

| Rehber | Açıklama |
|--------|----------|
| [README.md](README.md) | Proje özeti ve özellikler |
| [KURULUM_REHBERI.md](KURULUM_REHBERI.md) | Adım adım kurulum talimatları |
| [KAYNAK_KOD_OZETI.md](KAYNAK_KOD_OZETI.md) | Kaynak kod mimarisi ve açıklaması |
| [DOSYA_LISTESI.md](DOSYA_LISTESI.md) | Tüm dosyaların açıklaması |

## 🎯 Temel Özellikler

### 1. AI Sohbet
- Müşteri sorularına akıllı cevaplar
- Doğal dil işleme
- Bağlamı anlama

### 2. Gerçek Zamanlı Ürün Verisi
- enucuzbebekmarket.com'dan güncel ürünler
- Canlı fiyatlar
- İndirim bilgileri

### 3. Akıllı Ürün Önerme
- Yaş grubuna göre filtreleme
- Bütçe kontrolü
- Cinsiyet ve kategori eşleştirmesi

### 4. Kullanıcı Dostu Arayüz
- Sohbet benzeri tasarım
- Ürün kartları
- Kolay navigasyon

## 🔧 Yapılandırma

### Gemini API Anahtarı Ekleme (Opsiyonel)

Eğer Google Generative AI'ı kullanmak istiyorsanız:

1. [Google AI Studio](https://makersuite.google.com/) sitesine gidin
2. "Get API Key" → "Create API key in new project"
3. API anahtarını kopyalayın
4. `app/src/main/java/com/example/babyclothingapp/services/GeminiAIService.kt` dosyasını açın
5. API anahtarını ekleyin

## 📱 Uygulamayı Kullanma

### Örnek Sorular

1. **Yaş Grubu Belirtme:**
   - "3 aylık bebeğim için ürün arıyorum"
   - "1-2 yaş arası erkek çocuk giyim"

2. **Bütçe Belirtme:**
   - "500 ₺'ye kadar ürün göster"
   - "En ucuz seçenekler nelerdir?"

3. **Kategori Belirtme:**
   - "Tulum arıyorum"
   - "Yazlık elbise önerebilir misin?"

4. **Kombinasyon:**
   - "2 yaşındaki kız bebeğim için 300 ₺'ye yazlık giyim"
   - "6 aylık erkek bebek için uygun çorap var mı?"

## 🐛 Sık Karşılaşılan Sorunlar

### Gradle Senkronizasyonu Başarısız
```bash
./gradlew clean
./gradlew sync
```

### Emülatör Başlamıyor
- Device Manager'da emülatörü seçin
- "Wipe Data" tıklayın
- Tekrar başlatın

### APK Yükleme Başarısız
```bash
adb uninstall com.example.babyclothingapp
adb install app/build/outputs/apk/release/app-release.apk
```

## 📦 APK Oluşturma

### Release APK
```
Build → Build Bundle(s) / APK(s) → Build APK(s)
```

APK dosyası şu konumda oluşturulur:
```
app/build/outputs/apk/release/app-release.apk
```

## 🚀 Sonraki Adımlar

### 1. Uygulamayı Test Edin
- Farklı sorular deneyin
- Ürün önerilerini kontrol edin
- Performansı değerlendirin

### 2. Özelleştirme
- Renkleri değiştirin (`res/values/colors.xml`)
- Metinleri düzenleyin (`res/values/strings.xml`)
- Yeni özellikler ekleyin

### 3. Dağıtım
- APK'yı başka cihazlara yükleyin
- Google Play Store'da yayınlayın (isteğe bağlı)
- Arkadaşlarla paylaşın

## 📊 Proje İstatistikleri

| Metrik | Değer |
|--------|-------|
| Toplam Dosya | 33 |
| Kotlin Dosyaları | 7 |
| XML Dosyaları | 14 |
| Proje Boyutu | 236 KB |
| Minimum API | 24 |
| Target API | 34 |
| Bağımlılık Sayısı | 15+ |

## 🎓 Öğrenme Kaynakları

- [Android Developer Documentation](https://developer.android.com/)
- [Kotlin Official Documentation](https://kotlinlang.org/docs/)
- [Retrofit Documentation](https://square.github.io/retrofit/)
- [Google Generative AI](https://ai.google.dev/)

## 💡 İpuçları

1. **Performans:** Web scraping işlemi ilk kez biraz uzun sürebilir
2. **İnternet:** Uygulamanın çalışması için internet bağlantısı gereklidir
3. **Emülatör:** Fiziksel cihazda daha hızlı çalışır
4. **Debugging:** Android Studio'da Logcat'i açarak hataları görebilirsiniz

## 🤝 Katkı

Bu projeyi geliştirmek istiyorsanız:

1. Kodunuzu test edin
2. Yorum ekleyin
3. Değişiklikleri belgelendirin
4. Pull request gönderin

## 📞 Destek

Sorularınız veya sorunlarınız için lütfen iletişime geçin.

## 📄 Lisans

Bu proje eğitim amaçlı oluşturulmuştur.

---

## ✅ Kontrol Listesi

Başlamadan önce kontrol edin:

- [ ] Android Studio kurulu
- [ ] Java 11+ kurulu
- [ ] Android SDK 34 kurulu
- [ ] Proje Android Studio'da açıldı
- [ ] Gradle senkronizasyonu tamamlandı
- [ ] Emülatör veya cihaz hazır
- [ ] İnternet bağlantısı var

## 🎉 Hazır mısınız?

Tüm adımları tamamladıysanız, uygulamayı çalıştırmaya başlayabilirsiniz!

```
Shift + F10 (Run)
```

İyi eğlenceler! 🚀

---

**Geliştirici:** Manus AI  
**Sürüm:** 1.0  
**Güncelleme Tarihi:** Mart 2026
