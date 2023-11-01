package com.develou.chips.inputchips

import androidx.lifecycle.ViewModel
import com.develou.chips.inputchips.InputChipsState.Companion.InputNone
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InputChipsViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        InputChipsState(
            input = InputNone,
            confirmedInputs = emptyMap(),
            onInputChange = ::onInputChange,
            onConfirmInput = ::onConfirmInput,
            onBackspaceClick = ::onBackspaceClick
        )
    )

    val state = _state.asStateFlow()

    private fun onInputChange(input: String) {
        _state.update { currentState ->
            currentState.copy(input = input.trim())
        }
    }

    private fun onConfirmInput() {
        if (_state.value.input.isBlank()) return

        _state.update { currentState ->
            val newChips = buildChip(
                chips = currentState.confirmedInputs,
                label = currentState.input,
                isSelected = false
            )
            currentState.copy(
                confirmedInputs = newChips,
                input = InputNone
            )
        }
    }

    private fun onBackspaceClick() {
        val chips = state.value.confirmedInputs.values
        if (chips.isEmpty() || state.value.input.isNotBlank()) return

        val lastChip = chips.last()

        if (lastChip.isSelected) {
            removeChip(lastChip.label)
        } else {
            selectChip(lastChip.label)
        }
    }

    private fun selectChip(label: String) {
        _state.update { currentState ->
            currentState.copy(
                confirmedInputs = buildChip(
                    chips = currentState.confirmedInputs,
                    label = label,
                    isSelected = true
                )
            )
        }
    }

    private fun buildChip(
        chips: Map<String, ConfirmedInput>,
        label: String,
        isSelected: Boolean
    ): Map<String, ConfirmedInput> {
        val newPickedExtra = ConfirmedInput(
            label = label,
            isSelected = isSelected,
            onClick = {},
            onRemove = { removeChip(label) }
        )

        return chips + (label to newPickedExtra)
    }

    private fun removeChip(chipToRemoveKey: String) {
        _state.update { currentState ->
            val newChips = currentState.confirmedInputs - chipToRemoveKey
            currentState.copy(confirmedInputs = newChips)
        }
    }
}