package com.metehanbolat.homeworkproject.models.productmodel

data class Bilgiler(
    val brief: String,
    val campaign: Campaign,
    val campaignDescription: String,
    val campaignTitle: String,
    val categories: List<Category>,
    val description: String,
    val image: Boolean,
    val images: List<Image>,
    val likes: Likes,
    val price: String,
    val productId: String,
    val productName: String,
    val saleInformation: SaleInformation
)