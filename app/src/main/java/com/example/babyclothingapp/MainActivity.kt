package com.example.babyclothingapp

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.babyclothingapp.adapters.ChatAdapter
import com.example.babyclothingapp.viewmodels.ChatViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var chatViewModel: ChatViewModel
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageInput: EditText
    private lateinit var sendButton: ImageButton
    private lateinit var loadingIndicator: ProgressBar
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var emptyStateText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ViewModel'i başlat
        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        // UI bileşenlerini bağla
        initializeViews()

        // Adapter'ı oluştur
        chatAdapter = ChatAdapter()
        chatRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity).apply {
                stackFromEnd = true
            }
            adapter = chatAdapter
        }

        // Mesajları gözlemle
        lifecycleScope.launch {
            chatViewModel.messages.collect { messages ->
                chatAdapter.submitList(messages)
                if (messages.isNotEmpty()) {
                    chatRecyclerView.scrollToPosition(messages.size - 1)
                }
                updateEmptyState(messages.isEmpty())
            }
        }

        // Yükleme durumunu gözlemle
        lifecycleScope.launch {
            chatViewModel.isLoading.collect { isLoading ->
                loadingIndicator.visibility = if (isLoading) android.view.View.VISIBLE else android.view.View.GONE
            }
        }

        // Hata mesajlarını gözlemle
        lifecycleScope.launch {
            chatViewModel.errorMessage.collect { error ->
                if (error.isNotEmpty()) {
                    showErrorMessage(error)
                }
            }
        }

        // Gönder butonuna tıklama
        sendButton.setOnClickListener {
            val message = messageInput.text.toString()
            if (message.isNotEmpty()) {
                chatViewModel.sendMessage(message)
                messageInput.text.clear()
            }
        }

        // Enter tuşu ile gönder
        messageInput.setOnKeyListener { _, keyCode, event ->
            if (keyCode == android.view.KeyEvent.KEYCODE_ENTER && 
                event.action == android.view.KeyEvent.ACTION_DOWN) {
                sendButton.performClick()
                return@setOnKeyListener true
            }
            false
        }
    }

    private fun initializeViews() {
        chatRecyclerView = findViewById(R.id.chatRecyclerView)
        messageInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)
        loadingIndicator = findViewById(R.id.loadingIndicator)
        emptyStateText = findViewById(R.id.emptyStateText)
    }

    private fun updateEmptyState(isEmpty: Boolean) {
        emptyStateText.visibility = if (isEmpty) android.view.View.VISIBLE else android.view.View.GONE
    }

    private fun showErrorMessage(error: String) {
        android.widget.Toast.makeText(this, error, android.widget.Toast.LENGTH_SHORT).show()
    }
}
