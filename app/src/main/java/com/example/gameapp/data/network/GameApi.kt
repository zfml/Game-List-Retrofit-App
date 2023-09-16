package com.example.gameapp.data.network

import com.example.gameapp.model.GameModel
import com.example.gameapp.util.Constants.GAME_END_POINT
import com.example.gameapp.util.Constants.GAME_ID_END_POINT
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApi {
    @GET(GAME_END_POINT)
     suspend fun getAllGames(): List<GameModel>

     @GET(GAME_ID_END_POINT)
     suspend fun getGameById(@Query(value = "id") id: Int): GameModel
}