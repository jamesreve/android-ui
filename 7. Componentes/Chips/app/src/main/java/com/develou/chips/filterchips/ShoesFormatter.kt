package com.develou.chips.filterchips

import com.develou.chips.filterchips.data.Shoe

fun List<Shoe>.asUi(): String {
    return if (isEmpty()) {
        "|Sin resultados|"
    } else {
        map(Shoe::name).joinToString(prefix = "|", postfix = "|")
    }
}