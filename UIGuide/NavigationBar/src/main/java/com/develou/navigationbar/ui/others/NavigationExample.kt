package com.develou.navigationbar.ui.others

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Explore

@Serializable
object Notifications

@Serializable
object Profile

/**
 * Representa un destino de ejemplo. Normalmente creas un archivo Kotlin
 * por cada uno de tus pantallas.
 */
@Composable
fun OtherDestination(route: String = "Default") {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(route, style = MaterialTheme.typography.displaySmall)
    }
}

private val SampleText = LoremIpsum(500).values.joinToString("")

fun NavGraphBuilder.homeScreen(scrollState: ScrollState) {
    composable<Home> {
        Box(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            Text(text = SampleText)
        }
    }
}

fun NavGraphBuilder.exploreScreen() {
    composable<Explore> {
        OtherDestination("Explorar")
    }
}

fun NavGraphBuilder.notificationsScreen() {
    composable<Notifications> {
        OtherDestination("Avisos")
    }
}

fun NavGraphBuilder.profileScreen() {
    composable<Profile> {
        OtherDestination("Perfil")
    }
}