package com.example.gameapp.screen.gameList

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gameapp.model.GameModel
import com.example.gameapp.screen.components.GameItem


@Composable
fun GameListScreen(
    modifier: Modifier = Modifier,
    viewModel: GameListViewModel = hiltViewModel(),
) {
    val gameListUiState = viewModel.gameListUiState


    Column (
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn() {
            items(gameListUiState.gameList, key = {item: GameModel -> item.id  }) { gameModel ->
                GameItem(game = gameModel)
            }
        }
    }

}