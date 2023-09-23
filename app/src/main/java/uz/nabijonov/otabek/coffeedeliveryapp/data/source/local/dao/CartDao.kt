package uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.entity.CartProductEntity

@Dao
interface CartDao {

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
}