package uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.dao.CartDao
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.dao.FavDao
import uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val cartDao: CartDao,
    private val favDao: FavDao
) : LocalRepository {

    override fun addToCart(coffeeData: CoffeeData) {
        cartDao.addToCart(coffeeData.toCartEntity())
    }

    override fun deleteFromCart(coffeeData: CoffeeData) {
        cartDao.deleteFromCart(coffeeData.toCartEntity())
    }

    override fun getAllCartProducts(): Flow<List<CoffeeData>> =
        cartDao.getAllCartProducts().map { list ->
            list.map { it.toData() }
        }

    override fun incrementProductCount(id: Int, count: Int) {
        cartDao.incCartProductCount(id, count)
    }

    override fun decrementProductCount(id: Int, count: Int) {
        cartDao.decCartProductCount(id, count)
    }

    override fun addToFav(coffeeData: CoffeeData) {
        favDao.addToFav(coffeeData.toFavEntity())
    }

    override fun deleteFromFav(coffeeData: CoffeeData) {
        favDao.deleteFromFav(coffeeData.toFavEntity())
    }

    override fun getAllFavProducts(): Flow<List<CoffeeData>> =
        favDao.getAllFavProducts().map { list ->
            list.map { it.toData() }
        }

    override fun checkFavProduct(title: String): Boolean {
        val data = favDao.checkFavProduct(title)
        return data != null
    }
}