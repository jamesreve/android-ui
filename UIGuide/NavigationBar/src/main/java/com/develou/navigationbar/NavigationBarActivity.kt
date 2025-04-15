package com.develou.navigationbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.develou.navigationbar.ui.main.NavigationBarScreen
import com.develou.navigationbar.ui.theme.NavigationBarTheme

class NavigationBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationBarTheme {
                NavigationBarScreen()
            }
        }
    }
}