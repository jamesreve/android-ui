package com.develou.grids.ui.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.develou.grids.ui.main.GridItemUiModel
import com.develou.grids.ui.main.base.GridItem

@Composable
fun VerticalGridExample(
    items: List<GridItemUiModel>,
    modifier: Modifier = Modifier,
    contentPadding: Float = 0f,
    verticalSpacing: Float = 0f,
    horizontalSpacing: Float = 0f,
    cellSize: Float = 0f,
    cellType: CellType,
    onGridItemClick: (GridItemUiModel) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = cellsDefinitionFor(cellSize, cellType),
        contentPadding = PaddingValues(all = contentPadding.dp),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing.dp),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing.dp)
    ) {
        items(items = items, key = { it.id }) {
            GridItem(
                modifier = Modifier
                    .animateItem()
                    .height(GridItemUiModel.MIN_SIZE.dp),
                item = it,
                onClick = { onGridItemClick(it) }
            )
        }
    }
}

fun cellsDefinitionFor(cellSize: Float, cellType: CellType): GridCells {
    return when (cellType) {
        CellType.Fixed -> return GridCells.Fixed(count = cellSize.toInt())
        CellType.Adaptive -> GridCells.Adaptive(minSize = cellSize.dp)
        CellType.FixedSize -> GridCells.FixedSize(size = cellSize.dp)
    }
}