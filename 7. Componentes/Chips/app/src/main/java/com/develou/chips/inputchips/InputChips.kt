package com.develou.chips.inputchips

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.ParagraphIntrinsics
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.develou.chips.ui.components.SmallSpace
import kotlinx.coroutines.launch

@Composable
fun InputChips(
    modifier: Modifier = Modifier,
    viewModel: InputChipsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    InputChips(modifier = modifier, state = state)
}

@Composable
private fun InputChips(
    modifier: Modifier,
    state: InputChipsState
) {
    Column(modifier = modifier) {
        SmallSpace()

        Text("Input Chip", style = MaterialTheme.typography.titleMedium)

        SmallSpace()

        InputChipTextField(state)
    }
}

@Composable
private fun InputChipTextField(state: InputChipsState) {
    val keyboard = LocalSoftwareKeyboardController.current

    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }

    Row(
        Modifier
            .border(2.dp, Color.Gray, RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .height(56.dp)
            .horizontalScroll(scrollState)
            .onGloballyPositioned {
                scope.launch { scrollState.scrollTo(it.size.width) }
            }
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    focusRequester.requestFocus()
                    keyboard?.show()
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        InputChipsGroup(state.chipList)
        GeneralTextField(
            state = state,
            focusRequester = focusRequester,
            keyboard = keyboard
        )
    }
}

@Composable
private fun GeneralTextField(
    state: InputChipsState,
    focusRequester: FocusRequester,
    keyboard: SoftwareKeyboardController?
) {
    Box {
        Placeholder(state)
        SpecificTextField(
            state,
            focusRequester,
            keyboard
        )
    }
}

@Composable
private fun BoxScope.Placeholder(state: InputChipsState) {
    if (state.noInput && state.confirmedInputs.isEmpty()) {
        Text(
            text = "Ingredientes",
            modifier = Modifier.Companion
                .align(Alignment.CenterStart)
                .padding(start = 16.dp),
            color = Color.Black.copy(alpha = 0.66f)
        )
    }
}

@Composable
private fun SpecificTextField(
    state: InputChipsState,
    focusRequester: FocusRequester,
    keyboard: SoftwareKeyboardController?
) {
    OutlinedTextField(
        modifier = Modifier
            .requiredWidth(inputWidth(state.input))
            .onKeyEvent {
                if (it.key == Key.Backspace) {
                    state.onBackspaceClick()
                }
                false
            }
            .focusRequester(focusRequester),
        value = state.input,
        onValueChange = state.onInputChange,
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = {
                state.onConfirmInput()
                if (state.noInput) keyboard?.hide()
            }
        ),
        keyboardOptions= KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent
        )
    )
}

@Composable
private fun inputWidth(state: String): Dp {
    val inputOrOneCharacter = state.ifEmpty { " " }

    return with(LocalDensity.current) {
        val inputWidth = ParagraphIntrinsics(
            text = inputOrOneCharacter,
            style = MaterialTheme.typography.bodyLarge,
            density = this,
            fontFamilyResolver = LocalFontFamilyResolver.current
        ).maxIntrinsicWidth

        inputWidth.toDp() + 32.dp
    }
}

@Composable
private fun InputChipsGroup(
    chips: Collection<ConfirmedInput>
) {
    if (chips.isEmpty()) return

    Row(
        modifier = Modifier.padding(start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        chips.forEach { pickedExtra ->
            InputChip(state = pickedExtra)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputChip(state: ConfirmedInput) {
    InputChip(
        selected = state.isSelected,
        onClick = state.onClick,
        label = { Text(state.label) },
        trailingIcon = {
            Icon(
                Icons.Default.Close,
                contentDescription = "Remover",
                Modifier
                    .clickable(onClick = state.onRemove)
                    .size(InputChipDefaults.IconSize),
            )
        }
    )
}

@Preview
@Composable
private fun Preview() {
    InputChips(
        modifier = Modifier,
        state = InputChipsState.Default
    )
}