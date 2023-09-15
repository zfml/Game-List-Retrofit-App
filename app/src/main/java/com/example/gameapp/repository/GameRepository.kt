package com.example.gameapp.repository

import com.example.gameapp.data.network.GameApi
import com.example.gameapp.model.GameModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val gameApi: GameApi
) {
     suspend fun getAllGames(): List<GameModel> {
        return withContext(Dispatchers.IO) {
            gameApi.getAllGames()
        }
     }




}