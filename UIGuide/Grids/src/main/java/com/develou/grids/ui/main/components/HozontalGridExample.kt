package com.develou.grids.ui.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.develou.grids.ui.main.GridItemUiModel
import com.develou.grids.ui.main.base.GridItem

@Composable
fun HorizontalGridExample(
    items: List<GridItemUiModel>,
    modifier: Modifier = Modifier,
    contentPadding: Float = 0f,
    verticalSpacing: Float = 0f,
    horizontalSpacing: Float = 0f,
    cellSize: Float = 0f,
    cellType: CellType = CellType.Fixed,
    onGridItemClick: (GridItemUiModel) -> Unit = {}
) {
    LazyHorizontalGrid(
        modifier = modifier.height(412.dp),
        rows = cellsDefinitionFor(cellSize, cellType),
        contentPadding = PaddingValues(all = contentPadding.dp),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing.dp),
        horizontalArrangement = Arrangement.spacedBy(horizontalSpacing.dp),
        content = {
            items(items = items, key = { it.id }) {
                GridItem(
                    modifier = Modifier
                        .animateItem()
                        .width(GridItemUiModel.MIN_SIZE.dp),
                    item = it,
                    onClick = { onGridItemClick(it) }
                )
            }
        }
    )
}

@Preview
@Composable
private fun HorizontalGridExamplePreview() {
    HorizontalGridExample(items = List(20) {
        GridItemUiModel(it.toString(), "Item ${it + 1}")
    })
}
