package uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.dao.CartDao
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.dao.FavDao
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.entity.CartProductEntity
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.entity.FavProductEntity

@Database(
    entities = [CartProductEntity::class, FavProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCartProductDao(): CartDao
    abstract fun getFavProductDao(): FavDao
}