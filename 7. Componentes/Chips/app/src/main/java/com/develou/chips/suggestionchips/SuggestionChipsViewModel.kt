package com.develou.chips.suggestionchips

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf

class SuggestionChipsViewModel : ViewModel() {

    private val isGreetingPanelVisible = MutableStateFlow(true)
    private val sentGreeting = MutableStateFlow("")
    private val greetingSuggestions = flowOf(listOf("Hola", "Buenos días", "¿Qué tal?"))

    val state = combine(
        isGreetingPanelVisible,
        sentGreeting,
        greetingSuggestions
    ) { isVisible, greeting, suggestions ->
        SuggestionChipsState(
            buildSuggestionStates(suggestions),
            greeting,
            isVisible
        )
    }

    private fun buildSuggestionStates(
        suggestions: List<String>
    ): List<SuggestionState> {
        return suggestions.map { suggestion ->
            SuggestionState(
                onClick = {
                    isGreetingPanelVisible.value = false
                    sentGreeting.value = suggestion
                },
                label = suggestion
            )
        }
    }
}