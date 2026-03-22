package com.example.babyclothingapp.services

import com.example.babyclothingapp.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeminiAIService {
    
    /**
     * Müşteri sorusuna göre ürün önerileri yapan AI yanıtı oluştur
     * Not: Gerçek uygulamada Google Generative AI SDK kullanılır
     * Burada demo amaçlı basit bir sistem kullanıyoruz
     */
    suspend fun generateProductRecommendation(
        userQuestion: String,
        availableProducts: List<Product>
    ): Pair<String, List<Product>> = withContext(Dispatchers.Default) {
        try {
            // Soru analizi
            val analysisResult = analyzeUserQuestion(userQuestion)
            
            // Ürünleri filtrele
            val recommendedProducts = filterProductsByAnalysis(availableProducts, analysisResult)
            
            // AI yanıtı oluştur
            val aiResponse = generateAIResponse(userQuestion, recommendedProducts, analysisResult)
            
            Pair(aiResponse, recommendedProducts.take(5))
        } catch (e: Exception) {
            Pair("Üzgünüm, şu anda öneriler sunamamaktayım. Lütfen daha sonra tekrar deneyin.", emptyList())
        }
    }

    private fun analyzeUserQuestion(question: String): QuestionAnalysis {
        val lowerQuestion = question.lowercase()
        
        val ageGroup = when {
            lowerQuestion.contains("yenidoğan") || lowerQuestion.contains("0-3") -> "0-3 Ay"
            lowerQuestion.contains("3-6") -> "3-6 Ay"
            lowerQuestion.contains("6-12") -> "6-12 Ay"
            lowerQuestion.contains("1 yaş") || lowerQuestion.contains("1-2") -> "1-2 Yaş"
            lowerQuestion.contains("2 yaş") || lowerQuestion.contains("2-5") -> "2-5 Yaş"
            else -> "Tüm Yaşlar"
        }
        
        val gender = when {
            lowerQuestion.contains("erkek") || lowerQuestion.contains("oğlan") -> "Erkek"
            lowerQuestion.contains("kız") || lowerQuestion.contains("kız bebek") -> "Kız"
            else -> "Unisex"
        }
        
        val category = when {
            lowerQuestion.contains("elbise") -> "Elbise"
            lowerQuestion.contains("tulum") -> "Tulum"
            lowerQuestion.contains("takım") -> "Takım"
            lowerQuestion.contains("çorap") -> "Çorap"
            lowerQuestion.contains("ayakkabı") -> "Ayakkabı"
            else -> "Giyim"
        }
        
        val budget = extractBudget(question)
        val season = extractSeason(question)
        
        return QuestionAnalysis(
            ageGroup = ageGroup,
            gender = gender,
            category = category,
            budget = budget,
            season = season,
            keywords = question.split(" ").filter { it.length > 3 }
        )
    }

    private fun extractBudget(question: String): Double {
        val budgetPattern = "([0-9]+)\\s*(tl|₺|lira)".toRegex(RegexOption.IGNORE_CASE)
        val match = budgetPattern.find(question)
        return match?.groupValues?.get(1)?.toDoubleOrNull() ?: 1000.0
    }

    private fun extractSeason(question: String): String {
        return when {
            question.lowercase().contains("yazlık") || question.lowercase().contains("yaz") -> "Yazlık"
            question.lowercase().contains("kışlık") || question.lowercase().contains("kış") -> "Kışlık"
            else -> "Tüm Sezonlar"
        }
    }

    private fun filterProductsByAnalysis(
        products: List<Product>,
        analysis: QuestionAnalysis
    ): List<Product> {
        return products.filter { product ->
            // Yaş grubu eşleşmesi
            val ageMatch = analysis.ageGroup == "Tüm Yaşlar" || 
                          product.ageGroup.contains(analysis.ageGroup) ||
                          product.name.contains(analysis.ageGroup, ignoreCase = true)
            
            // Fiyat eşleşmesi
            val priceMatch = product.discountedPrice <= analysis.budget
            
            // Kategori eşleşmesi
            val categoryMatch = analysis.category == "Giyim" ||
                               product.category.contains(analysis.category, ignoreCase = true) ||
                               product.name.contains(analysis.category, ignoreCase = true)
            
            // Cinsiyet eşleşmesi
            val genderMatch = analysis.gender == "Unisex" ||
                             product.name.contains(analysis.gender, ignoreCase = true) ||
                             product.name.contains("Bebek", ignoreCase = true)
            
            ageMatch && priceMatch && categoryMatch && genderMatch
        }.sortedBy { it.discountedPrice }
    }

    private fun generateAIResponse(
        question: String,
        recommendedProducts: List<Product>,
        analysis: QuestionAnalysis
    ): String {
        val response = StringBuilder()
        
        response.append("Merhaba! 👋\n\n")
        response.append("Sorunuza göre size en uygun ürünleri buldum:\n\n")
        
        if (recommendedProducts.isEmpty()) {
            response.append("Üzgünüm, ${analysis.ageGroup} yaş grubunda ${analysis.budget.toInt()} ₺ bütçeye uygun ürün bulamadım. ")
            response.append("Lütfen bütçenizi artırmayı veya farklı bir kategori seçmeyi deneyin.")
        } else {
            response.append("✨ Önerilen Ürünler:\n\n")
            
            recommendedProducts.take(3).forEachIndexed { index, product ->
                response.append("${index + 1}. ${product.name}\n")
                response.append("   💰 Fiyat: ${product.discountedPrice} ₺")
                if (product.discount > 0) {
                    response.append(" (%${product.discount} indirim)")
                }
                response.append("\n")
                response.append("   📝 ${product.description}\n")
                response.append("   👶 Yaş Grubu: ${product.ageGroup}\n")
                response.append("   🎨 Renk: ${product.color}\n")
                response.append("   🧵 Malzeme: ${product.material}\n\n")
            }
            
            response.append("Bu ürünleri sepete ekleyerek satın alabilirsiniz. ")
            response.append("Başka sorularınız varsa lütfen sorun!")
        }
        
        return response.toString()
    }

    data class QuestionAnalysis(
        val ageGroup: String,
        val gender: String,
        val category: String,
        val budget: Double,
        val season: String,
        val keywords: List<String>
    )
}
