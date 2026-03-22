package com.example.babyclothingapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.babyclothingapp.R
import com.example.babyclothingapp.models.Product

class ProductAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_card, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImage: ImageView = itemView.findViewById(R.id.productImage)
        private val productName: TextView = itemView.findViewById(R.id.productName)
        private val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        private val productDiscount: TextView = itemView.findViewById(R.id.productDiscount)
        private val productDescription: TextView = itemView.findViewById(R.id.productDescription)
        private val productAgeGroup: TextView = itemView.findViewById(R.id.productAgeGroup)

        fun bind(product: Product) {
            productName.text = product.name
            productPrice.text = "₺${product.discountedPrice}"
            productDescription.text = product.description
            productAgeGroup.text = product.ageGroup

            // İndirim göster
            if (product.discount > 0) {
                productDiscount.visibility = View.VISIBLE
                productDiscount.text = "%${product.discount}"
            } else {
                productDiscount.visibility = View.GONE
            }

            // Placeholder görsel (gerçek uygulamada Glide kullanılır)
            productImage.setBackgroundColor(android.graphics.Color.LTGRAY)
            productImage.setImageResource(R.drawable.ic_placeholder)

            itemView.setOnClickListener {
                // Ürün detayları göster
            }
        }
    }
}
