package com.example.gameapp.ui.screen.gameList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gameapp.R
import com.example.gameapp.model.GameModel
import com.example.gameapp.ui.screen.components.GameItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    viewModel: GameListViewModel = hiltViewModel(),
    navigateToDetailGameScreen:(Int) -> Unit
) {
    val gameUiState = viewModel.uiState.collectAsStateWithLifecycle()
    
    Scaffold(
        topBar = {
           TopAppBar(
               title = {
                   Text(text = "Games")
               }
           )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            when(gameUiState.value) {
                 GameUiState.Error -> ErrorScreen(onRetryClicked = viewModel::getAllGames)
                 GameUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
                is GameUiState.Success ->
                    GameListScreen(
                        gameList = (gameUiState.value as GameUiState.Success).list,
                        navigateToDetailGameScreen = navigateToDetailGameScreen
                    )
            }


        }

    }


}

@Composable
fun GameListScreen(
    gameList: List<GameModel>,
    navigateToDetailGameScreen: (Int) -> Unit
) {
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn() {
            items(gameList, key = {item: GameModel -> item.id  }) { gameModel ->
                GameItem(
                    game = gameModel,
                    modifier = Modifier.padding(8.dp),
                    navigateToDetailScreen = {id -> navigateToDetailGameScreen(id) }
                )
            }
        }
    }
}
@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        CircularProgressIndicator()
    }

}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onRetryClicked: () -> Unit
) {
   Column(
       modifier = modifier
           .fillMaxSize()
       ,
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center
   ) {
       Image(
           painter = painterResource(id = R.drawable.ic_connection_error),
           contentDescription = "Connection Error"
       )
       Text(
           text = "Loading Fail"
       )

       Button(onClick = onRetryClicked) {
           Text(
               text = "Retry"
           )
       }

   }
}
@Composable
@Preview
fun LoadingScreenPreview() {
    LoadingScreen(
        modifier = Modifier.fillMaxSize()
    )
}