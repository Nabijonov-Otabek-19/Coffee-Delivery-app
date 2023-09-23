package uz.nabijonov.otabek.coffeedeliveryapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.dao.CartDao
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.dao.FavDao
import uz.nabijonov.otabek.coffeedeliveryapp.data.source.local.database.AppDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @[Provides Singleton]
    fun provideDB(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "coffees"
    ).allowMainThreadQueries().build()

    @[Provides Singleton]
    fun provideCartProductDao(db: AppDatabase): CartDao = db.getCartProductDao()

    @[Provides Singleton]
    fun provideFavProductDao(db: AppDatabase): FavDao = db.getFavProductDao()
}