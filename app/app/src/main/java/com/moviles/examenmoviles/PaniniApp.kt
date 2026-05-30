package com.moviles.examenmoviles

import androidx.compose.runtime.Composable
import com.moviles.examenmoviles.navigation.AppNavHost
import com.moviles.examenmoviles.ui.theme.PaniniTheme

@Composable
fun PaniniApp() {
    PaniniTheme {
        AppNavHost()
    }
}