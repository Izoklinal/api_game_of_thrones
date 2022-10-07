package com.example.api_book

data class House (
    val name: String,
    val region: String,
    val coatOfArms: String,
    val words: String,
    val titles: MutableList<String>,
    val seats: MutableList<String>
)