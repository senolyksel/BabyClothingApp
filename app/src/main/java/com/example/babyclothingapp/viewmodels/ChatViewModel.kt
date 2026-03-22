package com.example.babyclothingapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.babyclothingapp.models.Product
import com.example.babyclothingapp.services.GeminiAIService
import com.example.babyclothingapp.services.WebScrapingService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ChatMessage(
    val id: String = System.currentTimeMillis().toString(),
    val text: String,
    val isUser: Boolean,
    val products: List<Product> = emptyList(),
    val timestamp: Long = System.currentTimeMillis()
)

class ChatViewModel : ViewModel() {
    private val webScrapingService = WebScrapingService()
    private val geminiAIService = GeminiAIService()
    
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()
    
    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    init {
        // Başlangıç mesajı
        addMessage(ChatMessage(
            text = "Merhaba! 👋 Ben Bebek Giyim Asistanınız. Bebeğiniz için ne tür ürün arıyorsunuz? Yaşını, cinsiyetini ve bütçenizi söylerseniz size en uygun ürünleri bulabilirim.",
            isUser = false
        ))
        
        // Ürünleri yükle
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val loadedProducts = webScrapingService.fetchProductsFromWebsite()
                _products.value = loadedProducts
            } catch (e: Exception) {
                _errorMessage.value = "Ürünler yüklenirken hata oluştu: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun sendMessage(userMessage: String) {
        if (userMessage.trim().isEmpty()) return
        
        // Kullanıcı mesajını ekle
        addMessage(ChatMessage(
            text = userMessage,
            isUser = true
        ))
        
        // AI yanıtını al
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val (aiResponse, recommendedProducts) = geminiAIService.generateProductRecommendation(
                    userMessage,
                    _products.value
                )
                
                addMessage(ChatMessage(
                    text = aiResponse,
                    isUser = false,
                    products = recommendedProducts
                ))
            } catch (e: Exception) {
                addMessage(ChatMessage(
                    text = "Üzgünüm, bir hata oluştu: ${e.message}",
                    isUser = false
                ))
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun addMessage(message: ChatMessage) {
        val currentMessages = _messages.value.toMutableList()
        currentMessages.add(message)
        _messages.value = currentMessages
    }

    fun clearMessages() {
        _messages.value = emptyList()
        init()
    }
}
