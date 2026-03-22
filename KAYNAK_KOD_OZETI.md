# Bebek Giyim Asistanı - Kaynak Kod Özeti

Bu belge, uygulamanın tüm önemli bileşenlerinin ve işlevlerinin bir özetini içerir.

## 🏗️ Proje Mimarisi

```
┌─────────────────────────────────────────────────────────────┐
│                    MainActivity (UI Layer)                   │
│                                                               │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  ChatRecyclerView + ChatAdapter                       │   │
│  │  - Mesajları gösterir                                 │   │
│  │  - Ürün kartlarını gösterir                           │   │
│  └──────────────────────────────────────────────────────┘   │
│                                                               │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  MessageInput + SendButton                            │   │
│  │  - Kullanıcı girdisini alır                           │   │
│  │  - Mesajı gönderir                                    │   │
│  └──────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                  ChatViewModel (Logic Layer)                 │
│                                                               │
│  - Mesajları yönetir                                         │
│  - AI servisini çağırır                                      │
│  - Ürünleri yönetir                                          │
│  - Hata mesajlarını yönetir                                  │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                   Services (Data Layer)                      │
│                                                               │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  GeminiAIService                                      │   │
│  │  - Soruları analiz eder                              │   │
│  │  - Ürünleri filtreler                                │   │
│  │  - AI yanıtı oluşturur                               │   │
│  └──────────────────────────────────────────────────────┘   │
│                                                               │
│  ┌──────────────────────────────────────────────────────┐   │
│  │  WebScrapingService                                   │   │
│  │  - enucuzbebekmarket.com'dan ürün çeker             │   │
│  │  - Fiyatları parse eder                              │   │
│  │  - Ürün bilgilerini çıkarır                          │   │
│  └──────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────────┐
│                  External APIs & Data                        │
│                                                               │
│  - Google Generative AI (Gemini)                             │
│  - enucuzbebekmarket.com                                     │
└─────────────────────────────────────────────────────────────┘
```

## 📱 Kullanıcı Akışı

```
1. Uygulama Başlat
        ↓
2. MainActivity Yüklenir
        ↓
3. ChatViewModel Başlatılır
        ↓
4. Ürünler Yüklenir (WebScrapingService)
        ↓
5. Hoş Geldiniz Mesajı Gösterilir
        ↓
6. Kullanıcı Soru Yazar
        ↓
7. GeminiAIService Soruyu Analiz Eder
        ↓
8. Ürünler Filtrelenir
        ↓
9. AI Yanıtı Oluşturulur
        ↓
10. Mesaj ve Ürünler Gösterilir
```

## 🔑 Temel Bileşenler

### 1. Product Model

```kotlin
data class Product(
    val id: String,                  // Ürün kimliği
    val name: String,                // Ürün adı
    val description: String,         // Açıklama
    val originalPrice: Double,       // Orijinal fiyat
    val discountedPrice: Double,     // İndirimli fiyat
    val discount: Int,               // İndirim yüzdesi
    val imageUrl: String,            // Görsel URL
    val ageGroup: String,            // Yaş grubu
    val category: String,            // Kategori
    val color: String,               // Renk
    val material: String,            // Malzeme
    val size: String                 // Boyut
)
```

### 2. ChatMessage Model

```kotlin
data class ChatMessage(
    val id: String,                  // Mesaj kimliği
    val text: String,                // Mesaj metni
    val isUser: Boolean,             // Kullanıcı mesajı mı?
    val products: List<Product>,     // İlişkili ürünler
    val timestamp: Long              // Zaman damgası
)
```

## 🤖 AI Soru Analizi

Uygulamada kullanılan AI analiz sistemi:

### Yaş Grubu Tespiti
```
"yenidoğan" → "0-3 Ay"
"3-6" → "3-6 Ay"
"6-12" → "6-12 Ay"
"1 yaş" → "1-2 Yaş"
"2 yaş" → "2-5 Yaş"
```

### Cinsiyet Tespiti
```
"erkek" / "oğlan" → "Erkek"
"kız" → "Kız"
Diğer → "Unisex"
```

### Kategori Tespiti
```
"elbise" → "Elbise"
"tulum" → "Tulum"
"takım" → "Takım"
"çorap" → "Çorap"
"ayakkabı" → "Ayakkabı"
```

### Bütçe Çıkarma
```
Regex: "([0-9]+)\s*(tl|₺|lira)"
Örnek: "300 ₺" → 300.0
```

## 🌐 Web Scraping Mantığı

### Ürün Çekme Süreci

1. **HTTP İsteği Gönder**
   ```
   URL: https://www.enucuzbebekmarket.com
   User-Agent: Mozilla/5.0...
   ```

2. **HTML Parse Et**
   ```
   JSoup.parse(html)
   ```

3. **Ürün Kartlarını Bul**
   ```
   doc.select("a[href*='/Urun/']")
   ```

4. **Bilgileri Çıkar**
   - Ürün adı
   - Fiyatlar (orijinal ve indirimli)
   - İndirim yüzdesi
   - Yaş grubu
   - Renk
   - Malzeme
   - Boyut

5. **Product Nesnesi Oluştur**

## 💬 Sohbet Akışı

### Mesaj Gönderme

```kotlin
fun sendMessage(userMessage: String) {
    // 1. Kullanıcı mesajını ekle
    addMessage(ChatMessage(text = userMessage, isUser = true))
    
    // 2. AI yanıtı al
    val (aiResponse, products) = geminiAIService.generateProductRecommendation(
        userMessage,
        availableProducts
    )
    
    // 3. AI mesajını ekle
    addMessage(ChatMessage(
        text = aiResponse,
        isUser = false,
        products = products
    ))
}
```

### AI Yanıt Oluşturma

```kotlin
fun generateProductRecommendation(
    userQuestion: String,
    availableProducts: List<Product>
): Pair<String, List<Product>> {
    // 1. Soruyu analiz et
    val analysis = analyzeUserQuestion(userQuestion)
    
    // 2. Ürünleri filtrele
    val recommended = filterProductsByAnalysis(
        availableProducts,
        analysis
    )
    
    // 3. Yanıt oluştur
    val response = generateAIResponse(
        userQuestion,
        recommended,
        analysis
    )
    
    return Pair(response, recommended)
}
```

## 🎨 UI Bileşenleri

### ChatAdapter

```kotlin
class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    // Mesajları gösterir
    // Ürün kartlarını gösterir
    // Mesaj konumunu ayarlar (sol/sağ)
}
```

### ProductAdapter

```kotlin
class ProductAdapter(private val products: List<Product>) 
    : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    // Ürünleri yatay listede gösterir
    // Ürün bilgilerini gösterir
    // Tıklama olaylarını işler
}
```

## 🔄 Veri Akışı

```
Kullanıcı Mesajı
    ↓
ChatViewModel.sendMessage()
    ↓
GeminiAIService.generateProductRecommendation()
    ├─ analyzeUserQuestion()
    ├─ filterProductsByAnalysis()
    └─ generateAIResponse()
    ↓
ChatMessage Oluştur
    ↓
ChatAdapter Güncelle
    ↓
RecyclerView Yenile
    ↓
Mesaj ve Ürünler Göster
```

## 📊 Ürün Filtreleme Kriterleri

```kotlin
val ageMatch = analysis.ageGroup == "Tüm Yaşlar" || 
               product.ageGroup.contains(analysis.ageGroup)

val priceMatch = product.discountedPrice <= analysis.budget

val categoryMatch = analysis.category == "Giyim" ||
                   product.category.contains(analysis.category)

val genderMatch = analysis.gender == "Unisex" ||
                 product.name.contains(analysis.gender)

// Tüm kriterleri karşılayan ürünleri seç
val filtered = products.filter { 
    ageMatch && priceMatch && categoryMatch && genderMatch 
}
```

## 🔐 Güvenlik Özellikleri

1. **HTTPS Bağlantıları**
   - Tüm API çağrıları şifrelenmiş

2. **ProGuard Obfüskasyonu**
   - Kod karmaşıklaştırılmıştır
   - Ters mühendislik zorlaştırılmıştır

3. **İzin Yönetimi**
   - `INTERNET` izni gereklidir
   - `ACCESS_NETWORK_STATE` izni gereklidir

## 📦 Bağımlılıklar

| Bağımlılık | Sürüm | Amaç |
|-----------|-------|------|
| AndroidX Core | 1.12.0 | Temel Android işlevleri |
| Retrofit | 2.10.0 | HTTP istekleri |
| OkHttp | 4.11.0 | HTTP istemci |
| Gson | 2.10.1 | JSON parsing |
| Coroutines | 1.7.3 | Async işlemler |
| JSoup | 1.16.1 | HTML parsing |
| Glide | 4.16.0 | Görsel yükleme |
| Google Generative AI | 0.1.2 | Gemini API |

## 🚀 Performans İpuçları

1. **Web Scraping Optimizasyonu**
   - Timeout: 30 saniye
   - Maksimum 20 ürün çekme
   - Paralel işlem yok (sıralı)

2. **UI Optimizasyonu**
   - RecyclerView view pooling
   - Lazy loading
   - Smooth scrolling

3. **Bellek Yönetimi**
   - Coroutines ile bellek sızıntısı önleme
   - Lifecycle-aware components

## 📝 Kod Kalitesi

- **Kotlin Best Practices** uygulanmıştır
- **MVVM Mimarisi** kullanılmıştır
- **Coroutines** ile asynchronous işlemler
- **Type Safety** sağlanmıştır
- **Null Safety** kontrol edilmiştir

---

**Geliştirici:** Manus AI  
**Sürüm:** 1.0  
**Güncelleme Tarihi:** Mart 2026
