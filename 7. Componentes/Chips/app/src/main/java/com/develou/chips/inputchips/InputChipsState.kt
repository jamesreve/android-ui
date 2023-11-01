package com.develou.chips.inputchips

data class InputChipsState(
    val input: String,
    val confirmedInputs: Map<String, ConfirmedInput>,
    val onInputChange: (String) -> Unit,
    val onConfirmInput: () -> Unit,
    val onBackspaceClick: () -> Unit
) {
    val noInput get() =  input.isBlank()

    val chipList get() = confirmedInputs.values

    companion object {
        const val InputNone = ""
        val Default = InputChipsState(InputNone, emptyMap(), {}, {}) {}
    }
}

data class ConfirmedInput(
    val label: String,
    val isSelected: Boolean,
    val onClick: () -> Unit,
    val onRemove: () -> Unit
)