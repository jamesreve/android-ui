package com.develou.timepickers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.develou.timepickers.main.TimePickersScreen
import com.develou.timepickers.ui.theme.TimePickersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimePickersTheme {
                TimePickersScreen()
            }
        }
    }
}