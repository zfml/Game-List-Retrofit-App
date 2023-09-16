package com.example.gameapp.ui.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gameapp.model.GameModel

@Composable
fun GameItem(
    modifier: Modifier = Modifier,
    game: GameModel,
    navigateToDetailScreen: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .clickable {
                navigateToDetailScreen(game.id)
            }
        ,
        shape = RectangleShape
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = game.title,
                style = MaterialTheme.typography.headlineMedium
            )

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current).data(game.thumbnail)
                    .crossfade(true).build()
                ,
                contentDescription = "Game photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp)
            )

            Text(
                text = game.short_description,
                style = MaterialTheme.typography.bodyMedium
            )


        }
    }

}