package com.develou.grids

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.develou.grids.ui.main.GridsScreen
import com.develou.grids.ui.theme.GridsTheme

class GridsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GridsTheme {
                GridsScreen()
            }
        }
    }
}