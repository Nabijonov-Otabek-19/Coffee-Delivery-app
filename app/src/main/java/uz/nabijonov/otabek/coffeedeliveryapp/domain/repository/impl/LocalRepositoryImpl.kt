package uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.dao.ProductDao
import uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dao: ProductDao
) : LocalRepository {

    override fun add(coffeeData: CoffeeData) {
        dao.add(coffeeData.toEntity())
    }

    override fun delete(coffeeData: CoffeeData) {
        dao.delete(coffeeData.toEntity())
    }

    override fun getAllProducts(): Flow<List<CoffeeData>> =
        dao.retrieveAllProducts().map { list ->
            list.map { it.toData() }
        }

    override fun incrementProductCount(id: Int, count: Int) {
        dao.incProductCount(id, count)
    }

    override fun decrementProductCount(id: Int, count: Int) {
        dao.decProductCount(id, count)
    }
}