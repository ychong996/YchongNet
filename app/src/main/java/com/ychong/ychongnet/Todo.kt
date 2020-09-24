package com.ychong.ychongnet

data class Todo(
    val `data`: List<Data>,
    val status: Int
)

data class Data(
    val image: String,
    val title: String,
    val url: String
)