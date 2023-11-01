package com.develou.chips.filterchips.data

object MemoryShoes {
    val sizes = setOf("US 6", "US 6.5", "US 7")

    private val shoes = listOf(
        Shoe(name = "Nizza Platform", availableSizes = setOf("US 6", "US 6.5")),
        Shoe(name = "Racer TR23", availableSizes = setOf("US 6.5")),
        Shoe(name = "Duramo SL Running", availableSizes = setOf("US 6")),
        Shoe(name = "Samba OG", availableSizes = setOf("US 7")),
        Shoe(name = "Daily 3.0", availableSizes = setOf("US 6", "US 6.5", "US 7"))
    )

    fun findBy(selectedSizes: Set<String>): List<Shoe> {
        if (selectedSizes.isEmpty())
            return shoes

        return shoes.filter { shoe ->
            selectedSizes.all { selectedSize ->
                selectedSize in shoe.availableSizes
            }
        }
    }
}