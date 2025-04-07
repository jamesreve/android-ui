package com.develou.grids.ui.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.develou.grids.ui.main.GridItemUiModel
import com.develou.grids.ui.main.base.GridItem

@Composable
fun VerticalStaggeredGridExample(
    items: List<GridItemUiModel>,
    modifier: Modifier = Modifier,
    contentPadding: Float = 0f,
    verticalSpacing: Float = 0f,
    horizontalSpacing: Float = 0f,
    cellSize: Float = 0f,
    cellType: CellType = CellType.Fixed,
    onGridItemClick: (GridItemUiModel) -> Unit = {}
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = staggeredCellsDefinitionFor(cellSize, cellType),
        contentPadding = PaddingValues(all = contentPadding.dp),
        verticalItemSpacing = verticalSpacing.dp,
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing.dp)
    ) {
        items(items = items, key = { it.id }) {
            GridItem(
                modifier = Modifier
                    .animateItem()
                    .height(it.sizeForStaggered.dp),
                item = it,
                onClick = { onGridItemClick(it) }
            )
        }
    }
}

fun staggeredCellsDefinitionFor(cellSize: Float, cellType: CellType): StaggeredGridCells {
    return when (cellType) {
        CellType.Fixed -> return StaggeredGridCells.Fixed(count = cellSize.toInt())
        CellType.Adaptive -> StaggeredGridCells.Adaptive(minSize = cellSize.dp)
        CellType.FixedSize -> StaggeredGridCells.FixedSize(size = cellSize.dp)
    }
}

@Preview
@Composable
private fun Preview() {
    VerticalStaggeredGridExample(
        List(20) {
            GridItemUiModel(it.toString(), "Item ${it + 1}")
        },
    )
}