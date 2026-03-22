package com.example.babyclothingapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.babyclothingapp.R
import com.example.babyclothingapp.viewmodels.ChatMessage

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    private val messages = mutableListOf<ChatMessage>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_message, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount() = messages.size

    fun submitList(newMessages: List<ChatMessage>) {
        messages.clear()
        messages.addAll(newMessages)
        notifyDataSetChanged()
    }

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageText: TextView = itemView.findViewById(R.id.messageText)
        private val messageContainer: LinearLayout = itemView.findViewById(R.id.messageContainer)
        private val productsRecyclerView: RecyclerView = itemView.findViewById(R.id.productsRecyclerView)
        private val productsContainer: LinearLayout = itemView.findViewById(R.id.productsContainer)

        fun bind(message: ChatMessage) {
            messageText.text = message.text
            
            // Mesaj konumunu ayarla
            val params = messageContainer.layoutParams as RecyclerView.LayoutParams
            if (message.isUser) {
                messageContainer.setBackgroundResource(R.drawable.bg_user_message)
                messageText.setTextColor(android.graphics.Color.WHITE)
            } else {
                messageContainer.setBackgroundResource(R.drawable.bg_ai_message)
                messageText.setTextColor(android.graphics.Color.BLACK)
            }

            // Ürünleri göster
            if (message.products.isNotEmpty()) {
                productsContainer.visibility = View.VISIBLE
                val productAdapter = ProductAdapter(message.products)
                productsRecyclerView.apply {
                    layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = productAdapter
                }
            } else {
                productsContainer.visibility = View.GONE
            }
        }
    }
}
