package uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData

@Entity(tableName = "cartProducts")
data class CartProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val imgUrl: String,
    val price: Int,
    val count : Int
) {
    fun toData() = CoffeeData(id, title, description, imgUrl, price, count)
}