package com.example.babyclothingapp.models

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val originalPrice: Double,
    val discountedPrice: Double,
    val discount: Int,
    val imageUrl: String,
    val ageGroup: String,
    val category: String,
    val color: String,
    val material: String,
    val size: String
)

data class ProductResponse(
    val products: List<Product>,
    val totalCount: Int
)
