package uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.entity.FavProductEntity


@Dao
interface FavDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addToFav(favProductEntity: FavProductEntity)

    @Delete
    fun deleteFromFav(favProductEntity: FavProductEntity)

    @Query("Select * from favProducts")
    fun getAllFavProducts(): Flow<List<FavProductEntity>>

    @Query("SELECT * FROM favProducts WHERE id = :id")
    fun checkFavProduct(id: Int): FavProductEntity?
}