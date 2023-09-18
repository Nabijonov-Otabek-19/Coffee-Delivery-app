package uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(productEntity: ProductEntity)

    @Delete
    fun delete(productEntity: ProductEntity)

    @Query("Select * from products")
    fun retrieveAllProducts(): Flow<List<ProductEntity>>

    @Query("Update products set count = :count WHERE id = :id")
    fun incProductCount(id: Int, count: Int)

    @Query("Update products set count = :count WHERE id = :id")
    fun decProductCount(id: Int, count: Int)
}