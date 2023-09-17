package com.example.gameapp.ui.screen.game_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: GameDetailViewModel = hiltViewModel(),
    navigateToGamesList: () -> Unit
) {
    val gameDetailUiState = viewModel.gameUiState
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = gameDetailUiState.gameDetail.title)
                },
                navigationIcon = {
                    IconButton(onClick = { navigateToGamesList()}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to GamesListScreen"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column (
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current).data(gameDetailUiState.gameDetail.thumbnail)
                        .build()
                    ,
                    contentDescription = "Game Image",

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(194.dp)
                )
                Text(
                    text = gameDetailUiState.gameDetail.title,
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = gameDetailUiState.gameDetail.short_description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    )

}