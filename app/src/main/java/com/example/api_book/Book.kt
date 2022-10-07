package com.example.api_book

data class Book (
    val name: String,
    val isbn: String,
    val authors: MutableList<String>,
    val numberOfPages: Int,
    val country: String,
    val released: String
)