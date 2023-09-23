package uz.nabijonov.otabek.coffeedeliveryapp.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData

interface LocalRepository {
    fun addToCart(coffeeData: CoffeeData)
    fun deleteFromCart(coffeeData: CoffeeData)
    fun getAllCartProducts(): Flow<List<CoffeeData>>
    fun incrementProductCount(id: Int, count: Int)
    fun decrementProductCount(id: Int, count: Int)

    fun addToFav(coffeeData: CoffeeData)
    fun deleteFromFav(coffeeData: CoffeeData)
    fun getAllFavProducts(): Flow<List<CoffeeData>>
    fun checkFavProduct(id: Int): Boolean
}