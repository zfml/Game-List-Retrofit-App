package com.example.gameapp.ui.screen.gameList

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.gameapp.model.GameModel
import com.example.gameapp.repository.GameRepository
import com.example.gameapp.util.Constants.TIME_OUT_MILLIS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.Objects
import javax.inject.Inject

sealed interface GameUiState {
    object Loading : GameUiState
    data class Success(val list: List<GameModel>) : GameUiState
    object Error: GameUiState
}
@HiltViewModel
class GameListViewModel @Inject constructor(
    private val gameRepository: GameRepository
): ViewModel(){

    private val _uiState: MutableStateFlow<GameUiState>  = MutableStateFlow(GameUiState.Success(emptyList()))
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()


    init {
        getAllGames()
    }
    fun getAllGames() {
        viewModelScope.launch{


            _uiState.value = GameUiState.Loading

            try {

                gameRepository.latestAllGames
                    .collect{ allGames ->
                        _uiState.value = GameUiState.Success(allGames)
                    }



//                gameUiState = GameUiState.Success(gameRepository.getAllGames())


            } catch (e: IOException) {
                _uiState.value = GameUiState.Error

            } catch (e: HttpException) {
                _uiState.value = GameUiState.Error

            }



        }
    }








}