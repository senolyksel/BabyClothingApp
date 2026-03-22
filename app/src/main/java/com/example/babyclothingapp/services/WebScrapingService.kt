package com.example.babyclothingapp.services

import com.example.babyclothingapp.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.concurrent.TimeUnit

class WebScrapingService {
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    suspend fun fetchProductsFromWebsite(category: String = ""): List<Product> = withContext(Dispatchers.IO) {
        try {
            val url = "https://www.enucuzbebekmarket.com"
            val request = Request.Builder()
                .url(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string() ?: return@withContext emptyList()

            val doc = Jsoup.parse(body)
            val products = mutableListOf<Product>()

            // Ürün kartlarını bul
            val productElements = doc.select("a[href*='/Urun/']")

            productElements.take(20).forEach { element ->
                try {
                    val productName = element.select("div").text()
                    if (productName.isNotEmpty()) {
                        val priceText = element.text()
                        val prices = extractPrices(priceText)

                        if (prices.isNotEmpty()) {
                            val product = Product(
                                id = element.attr("href").hashCode().toString(),
                                name = productName.take(100),
                                description = "En Ucuz Bebek Market'ten gelen kaliteli ürün",
                                originalPrice = prices.getOrNull(0) ?: 0.0,
                                discountedPrice = prices.getOrNull(1) ?: prices.getOrNull(0) ?: 0.0,
                                discount = extractDiscount(priceText),
                                imageUrl = "",
                                ageGroup = extractAgeGroup(productName),
                                category = category.ifEmpty { "Bebek Giyim" },
                                color = extractColor(productName),
                                material = extractMaterial(productName),
                                size = extractSize(productName)
                            )
                            products.add(product)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            products
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    private fun extractPrices(text: String): List<Double> {
        val prices = mutableListOf<Double>()
        val pricePattern = "([0-9]+[.,][0-9]{2})".toRegex()
        pricePattern.findAll(text).forEach { match ->
            try {
                val price = match.value.replace(",", ".").toDouble()
                prices.add(price)
            } catch (e: Exception) {
                // Fiyat parse edilemedi
            }
        }
        return prices
    }

    private fun extractDiscount(text: String): Int {
        val discountPattern = "%([0-9]+)".toRegex()
        val match = discountPattern.find(text)
        return match?.groupValues?.get(1)?.toIntOrNull() ?: 0
    }

    private fun extractAgeGroup(text: String): String {
        return when {
            text.contains("0-3 Ay", ignoreCase = true) -> "0-3 Ay"
            text.contains("3-6 Ay", ignoreCase = true) -> "3-6 Ay"
            text.contains("6-12 Ay", ignoreCase = true) -> "6-12 Ay"
            text.contains("1-2 Yaş", ignoreCase = true) -> "1-2 Yaş"
            text.contains("2-5 Yaş", ignoreCase = true) -> "2-5 Yaş"
            text.contains("6-18 Ay", ignoreCase = true) -> "6-18 Ay"
            else -> "Tüm Yaşlar"
        }
    }

    private fun extractColor(text: String): String {
        val colors = listOf("Kırmızı", "Mavi", "Yeşil", "Sarı", "Pembe", "Siyah", "Beyaz", "Gri", "Turuncu", "Mor", "Füme", "Bej", "Pudra")
        return colors.firstOrNull { text.contains(it, ignoreCase = true) } ?: "Renkli"
    }

    private fun extractMaterial(text: String): String {
        val materials = listOf("Müslin", "Pamuk", "Polyester", "Organik", "Elastan", "Akrilik", "Yün")
        return materials.firstOrNull { text.contains(it, ignoreCase = true) } ?: "Kumaş"
    }

    private fun extractSize(text: String): String {
        return when {
            text.contains("XS", ignoreCase = true) -> "XS"
            text.contains("S", ignoreCase = true) -> "S"
            text.contains("M", ignoreCase = true) -> "M"
            text.contains("L", ignoreCase = true) -> "L"
            text.contains("XL", ignoreCase = true) -> "XL"
            else -> "Standart"
        }
    }
}
