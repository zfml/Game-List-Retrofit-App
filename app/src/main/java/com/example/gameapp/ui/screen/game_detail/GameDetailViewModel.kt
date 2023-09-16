package com.example.gameapp.ui.screen.game_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameapp.model.GameModel
import com.example.gameapp.repository.GameRepository
import com.example.gameapp.ui.screen.gameList.GameUiState
import com.example.gameapp.util.Constants.GAME_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val repository: GameRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val gameId: Int = checkNotNull(savedStateHandle[GAME_ID])

    var gameUiState by mutableStateOf(GameDetailUiState())
    private set

    init {
        viewModelScope.launch {
            gameUiState = repository.getGameById(gameId).toGameDetialUiState()
        }
    }

}



data class GameDetailUiState(
    val gameDetail: GameDetail = GameDetail()
)

data class GameDetail(
    val id: Int = 0,
    val title: String = "",
    val thumbnail: String = "",
    val short_description: String = ""
)

fun GameModel.toGameDetail(): GameDetail = GameDetail(
    id = id,
    title =  title,
    thumbnail = thumbnail,
    short_description = short_description
)

fun GameModel.toGameDetialUiState(): GameDetailUiState = GameDetailUiState(
    gameDetail = GameDetail(
        id = id,
        title = title,
        thumbnail = thumbnail,
        short_description = short_description
    )
)