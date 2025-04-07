package com.develou.grids.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.develou.grids.R
import com.develou.grids.ui.main.components.CellType
import com.develou.grids.ui.main.components.ConfigPanel
import com.develou.grids.ui.main.components.GridOrientation
import com.develou.grids.ui.main.components.HorizontalGridExample
import com.develou.grids.ui.main.components.HorizontalStaggeredGridExample
import com.develou.grids.ui.main.components.VerticalGridExample
import com.develou.grids.ui.main.components.VerticalStaggeredGridExample

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridsScreen(viewModel: GridsViewModel = viewModel()) {
    val gridItems by viewModel.items.collectAsStateWithLifecycle()

    var isConfigPanelOpen by rememberSaveable { mutableStateOf(false) }
    var selectedType by rememberSaveable { mutableStateOf(GridType.Basic) }
    var selectedOrientation by rememberSaveable { mutableStateOf(GridOrientation.Vertical) }
    var contentPadding by rememberSaveable { mutableFloatStateOf(0f) }
    var verticalSpacing by rememberSaveable { mutableFloatStateOf(0f) }
    var horizontalSpacing by rememberSaveable { mutableFloatStateOf(0f) }
    var cellType = rememberTextFieldState(CellType.Fixed.name)
    var cellSizeOrNumber by rememberSaveable { mutableFloatStateOf(initialCellSizeFor(CellType.Fixed)) }
    val verticalScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            TopBar(
                onConfigClick = { isConfigPanelOpen = true },
                verticalScrollBehavior = verticalScrollBehavior
            )
        },
        floatingActionButton = { Fab(viewModel::addItem) }
    ) {
        GridsContent(
            gridType = selectedType,
            gridOrientation = selectedOrientation,
            modifier = Modifier
                .padding(it)
                .nestedScroll(verticalScrollBehavior.nestedScrollConnection),
            items = gridItems,
            contentPadding = contentPadding,
            verticalSpacing = verticalSpacing,
            horizontalSpacing = horizontalSpacing,
            size = cellSizeOrNumber,
            cellType = cellType,
            removeItem = viewModel::removeItem
        )
    }

    if (isConfigPanelOpen) {
        ConfigPanel(
            selectedType = selectedType,
            selectedOrientation = selectedOrientation,
            onOrientationSelected = { selectedOrientation = it },
            onTypeSelected = { selectedType = it },
            contentPadding = contentPadding,
            onContentPaddingChange = { contentPadding = it },
            verticalSpacing = verticalSpacing,
            onVerticalSpacingChange = { verticalSpacing = it },
            horizontalSpacing = horizontalSpacing,
            onHorizontalSpacingChange = { horizontalSpacing = it },
            cellSize = cellSizeOrNumber,
            onCellSizeChange = { cellSizeOrNumber = it },
            cellType = cellType,
            onCellTypeChange = {
                cellType.setTextAndPlaceCursorAtEnd(it)
                cellSizeOrNumber = initialCellSizeFor(CellType.valueOf(it))
            },
            onDismiss = { isConfigPanelOpen = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    onConfigClick: () -> Unit,
    verticalScrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        actions = {
            IconButton(onClick = onConfigClick) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
            }
        },
        scrollBehavior = verticalScrollBehavior
    )
}

@Composable
private fun GridsContent(
    gridType: GridType,
    gridOrientation: GridOrientation,
    modifier: Modifier = Modifier,
    items: List<GridItemUiModel>,
    contentPadding: Float,
    verticalSpacing: Float,
    horizontalSpacing: Float,
    size: Float,
    cellType: TextFieldState,
    removeItem: (GridItemUiModel) -> Unit,
) {
    when (gridType to gridOrientation) {
        GridType.Basic to GridOrientation.Vertical -> VerticalGridExample(
            modifier = modifier,
            items = items,
            contentPadding = contentPadding,
            verticalSpacing = verticalSpacing,
            horizontalSpacing = horizontalSpacing,
            cellSize = size,
            cellType = cellType.mapToCellType(),
            onGridItemClick = removeItem
        )

        GridType.Basic to GridOrientation.Horizontal -> HorizontalGridExample(
            modifier = modifier,
            items = items,
            contentPadding = contentPadding,
            verticalSpacing = verticalSpacing,
            horizontalSpacing = horizontalSpacing,
            cellSize = size,
            cellType = cellType.mapToCellType(),
            onGridItemClick = removeItem
        )

        GridType.Staggered to GridOrientation.Vertical -> VerticalStaggeredGridExample(
            modifier = modifier,
            items = items,
            contentPadding = contentPadding,
            verticalSpacing = verticalSpacing,
            horizontalSpacing = horizontalSpacing,
            cellSize = size,
            cellType = cellType.mapToCellType(),
            onGridItemClick = removeItem
        )

        GridType.Staggered to GridOrientation.Horizontal -> HorizontalStaggeredGridExample(
            modifier = modifier,
            items = items,
            contentPadding = contentPadding,
            verticalSpacing = verticalSpacing,
            horizontalSpacing = horizontalSpacing,
            cellSize = size,
            cellType = cellType.mapToCellType(),
            onGridItemClick = removeItem
        )
    }
}

@Composable
private fun Fab(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}

fun initialCellSizeFor(cellType: CellType) = when (cellType) {
    CellType.Fixed -> 2f
    CellType.Adaptive -> 100f
    CellType.FixedSize -> 100f
}

enum class GridType {
    Basic,
    Staggered
}

fun TextFieldState.mapToCellType() = CellType.valueOf(this.text.toString())

@Preview()
@Composable
private fun GridsScreenPreview() {
    GridsScreen()
}