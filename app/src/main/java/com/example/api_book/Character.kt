package com.example.api_book

data class Character(
    val name: String,
    val gender: String,
    val culture: String,
    val born: String,
    val died: String,
    val titles: MutableList<String>,
    val aliases: MutableList<String>,
    val father: String,
    val mother: String
)