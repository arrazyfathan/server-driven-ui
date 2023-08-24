package com.arrazyfathan.serverdrivenui.presenstation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.arrazyfathan.serverdrivenui.R
import com.arrazyfathan.serverdrivenui.presenstation.home.HomeScreen
import com.arrazyfathan.serverdrivenui.presenstation.home.HomeViewModel
import com.arrazyfathan.serverdrivenui.presenstation.ui.theme.ServerDrivenUITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = ContextCompat.getColor(this, R.color.status_bar_color)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.status_bar_color)
        setContent {
            ServerDrivenUITheme {
                val viewModel = hiltViewModel<HomeViewModel>()
                HomeScreen(viewModel)
            }
        }
    }
}
