package com.example.pc012025.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.pc012025.presentation.auth.LoginScreen
import com.example.pc012025.presentation.conversion.ConversionScreen
import com.example.pc012025.presentation.history.HistoryScreen


object Routes { const val LOGIN="login"; const val CONVERT="convert"; const val HISTORY="history" }

@Composable
fun AppNavGraph(isLoggedIn:Boolean) {
    val nav = rememberNavController()
    NavHost(nav, startDestination = if (isLoggedIn) Routes.CONVERT else Routes.LOGIN) {
        composable(Routes.LOGIN) { LoginScreen(onLogged = {
            nav.navigate(Routes.CONVERT) { popUpTo(Routes.LOGIN) { inclusive = true } }
        }) }
        composable(Routes.CONVERT) { ConversionScreen(onHistory = { nav.navigate(Routes.HISTORY) }) }
        composable(Routes.HISTORY) { HistoryScreen(onBack = { nav.popBackStack() }) }
    }
}
