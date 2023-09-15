package com.example.gameapp.model

import kotlinx.serialization.Serializable

@Serializable
data class GameModel(
    val id: Int ,
    val title: String,
    val thumbnail: String,
    val short_description: String
)