package uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.dao.ProductDao
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.entity.CartProductEntity

@Database(entities = [CartProductEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
}