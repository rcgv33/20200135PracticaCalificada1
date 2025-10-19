package com.example.pc012025



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pc012025.presentation.navigation.AppNavGraph
import com.example.pc012025.ui.theme.AppTheme
import com.example.pc012025.viewmodel.AuthViewModel



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val auth: AuthViewModel = viewModel()
                    AppNavGraph(isLoggedIn = auth.state.value.isLoggedIn)
                }
            }
        }
    }
}
