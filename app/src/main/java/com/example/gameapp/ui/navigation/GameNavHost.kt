package com.example.gameapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gameapp.ui.screen.gameList.GameScreen
import com.example.gameapp.ui.screen.game_detail.GameDetailScreen
import com.example.gameapp.util.Constants.GAMES_SCREEN
import com.example.gameapp.util.Constants.GAME_DETAIL_SCREEN
import com.example.gameapp.util.Constants.GAME_DETAIL_SCREEN_ARG
import com.example.gameapp.util.Constants.GAME_ID

sealed class Screens(val route: String) {
    object GamesScreen: Screens(route = GAMES_SCREEN)
    object GameDetailScreen: Screens(route = GAME_DETAIL_SCREEN_ARG)
}


@Composable
fun GameNavHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController ,
        startDestination = Screens.GamesScreen.route
    ) {
        composable(
            route = Screens.GamesScreen.route
        ) {
            GameScreen(
                navigateToDetailGameScreen = {
                    navController.navigate("${GAME_DETAIL_SCREEN}/${it}")
                }
            )
        }

        composable(
            route = Screens.GameDetailScreen.route,
            arguments = listOf(
                navArgument(name = GAME_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            GameDetailScreen()
        }
    }
}