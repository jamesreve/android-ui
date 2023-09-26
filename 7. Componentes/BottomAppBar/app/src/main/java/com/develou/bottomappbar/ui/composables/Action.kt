package com.develou.bottomappbar.ui.composables

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Representa el estado de las acciones en la Bottom App Bar
 */
class Action private constructor(
    val icon: Any,
    val description: String,
    val action: () -> Unit
) {
    companion object {
        fun vectorAction(
            imageVector: ImageVector,
            description: String,
            action: () -> Unit
        ): Action {
            return Action(imageVector, description, action)
        }

        fun painterAction(
            painter: Painter,
            description: String,
            action: () -> Unit
        ): Action {
            return Action(painter, description, action)
        }
    }
}
