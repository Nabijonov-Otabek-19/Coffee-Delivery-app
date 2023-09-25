package uz.nabijonov.otabek.coffeedeliveryapp.data.common

import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.entity.FavProductEntity
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.entity.CartProductEntity

data class CoffeeData(
    val id: Int = -1,
    val title: String = "",
    val description: String = "",
    val imgUrl: String = "",
    val price: Int = 0,
    val count: Int = 1
) {
    fun toCartEntity() = CartProductEntity(id, title, description, imgUrl, price, count)
    fun toFavEntity() = FavProductEntity(id, title, description, imgUrl, price, count)
}