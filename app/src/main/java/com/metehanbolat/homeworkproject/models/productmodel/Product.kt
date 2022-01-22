package com.metehanbolat.homeworkproject.models.productmodel

data class Product(
    val bilgiler: List<Bilgiler>,
    val durum: Boolean,
    val mesaj: String,
    val total: String
)