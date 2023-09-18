package uz.nabijonov.otabek.coffeedeliveryapp.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData

interface ServerRepository {

    fun loadData(categoryName: String): Flow<Result<List<CoffeeData>>>
}