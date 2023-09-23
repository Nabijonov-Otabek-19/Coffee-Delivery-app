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

    override fun addToCart(coffeeData: CoffeeData) {
        dao.addToCart(coffeeData.toCartEntity())
    }

    override fun deleteFromCart(coffeeData: CoffeeData) {
        dao.deleteFromCart(coffeeData.toCartEntity())
    }

    override fun getAllCartProducts(): Flow<List<CoffeeData>> =
        dao.getAllCartProducts().map { list ->
            list.map { it.toData() }
        }

    override fun incrementProductCount(id: Int, count: Int) {
        dao.incCartProductCount(id, count)
    }

    override fun decrementProductCount(id: Int, count: Int) {
        dao.decCartProductCount(id, count)
    }

    override fun addToFav(coffeeData: CoffeeData) {
        dao.addToFav(coffeeData.toFavEntity())
    }

    override fun deleteFromFav(coffeeData: CoffeeData) {
        dao.deleteFromFav(coffeeData.toFavEntity())
    }
}