package com.example.gameapp.data.network

import com.example.gameapp.model.GameModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface GameApi {
    @GET("games")
     suspend fun getAllGames(): List<GameModel>
}