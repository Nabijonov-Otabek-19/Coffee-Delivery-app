package uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.entity.CartProductEntity
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.entity.FavProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addToCart(cartProductEntity: CartProductEntity)

    @Delete
    fun deleteFromCart(cartProductEntity: CartProductEntity)

    @Query("Select * from cartProducts")
    fun getAllCartProducts(): Flow<List<CartProductEntity>>

    @Query("Update cartProducts set count = :count WHERE id = :id")
    fun incCartProductCount(id: Int, count: Int)

    @Query("Update cartProducts set count = :count WHERE id = :id")
    fun decCartProductCount(id: Int, count: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addToFav(favProductEntity: FavProductEntity)

    @Delete
    fun deleteFromFav(favProductEntity: FavProductEntity)

    @Query("Select * from favProducts")
    fun getAllFavProducts(): Flow<List<FavProductEntity>>
}