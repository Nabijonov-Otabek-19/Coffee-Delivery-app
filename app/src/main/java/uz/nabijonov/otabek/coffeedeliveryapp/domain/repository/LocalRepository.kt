package uz.nabijonov.otabek.coffeedeliveryapp.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData

interface LocalRepository {
    fun add(coffeeData: CoffeeData)
    fun delete(coffeeData: CoffeeData)
    fun getAllProducts(): Flow<List<CoffeeData>>
    fun incrementProductCount(id: Int, count: Int)
    fun decrementProductCount(id: Int, count: Int)
}