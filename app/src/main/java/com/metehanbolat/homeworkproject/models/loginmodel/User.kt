package com.metehanbolat.homeworkproject.models.loginmodel

data class User(
    val bilgiler: Bilgiler,
    val durum: Boolean,
    val mesaj: String
)