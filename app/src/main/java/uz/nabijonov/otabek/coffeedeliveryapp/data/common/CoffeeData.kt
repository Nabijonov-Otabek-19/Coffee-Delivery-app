package uz.nabijonov.otabek.coffeedeliveryapp.data.common

import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.entity.ProductEntity

data class CoffeeData(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val imgUrl : String = "",
    val price : Int = 0,
    val count : Int = 1
){
    fun toEntity() = ProductEntity(
        id, title, description, imgUrl, price, count
    )
}
