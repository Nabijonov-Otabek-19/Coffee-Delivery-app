package uz.nabijonov.otabek.coffeedeliveryapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.detail.DetailContract
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.detail.DetailDirection
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.cart.CartContract
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.cart.CartDirection
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.favourite.FavouriteContract
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.favourite.FavouriteDirection
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.home.HomeContract
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.home.HomeDirection
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @[Binds Singleton]
    fun bindHomeScreenDirection(impl: HomeDirection): HomeContract.Direction

    @[Binds Singleton]
    fun bindCartScreenDirection(impl: CartDirection): CartContract.Direction

    @[Binds Singleton]
    fun bindDetailScreenDirection(impl: DetailDirection): DetailContract.Direction

    @[Binds Singleton]
    fun bindFavouriteScreenDirection(impl: FavouriteDirection): FavouriteContract.Direction
}