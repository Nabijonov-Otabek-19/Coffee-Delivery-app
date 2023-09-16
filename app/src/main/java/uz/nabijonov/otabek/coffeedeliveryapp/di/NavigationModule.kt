package uz.nabijonov.otabek.coffeedeliveryapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.nabijonov.otabek.coffeedeliveryapp.navigation.AppNavigator
import uz.nabijonov.otabek.coffeedeliveryapp.navigation.NavigationDispatcher
import uz.nabijonov.otabek.coffeedeliveryapp.navigation.NavigationHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindAppNavigator(impl: NavigationDispatcher): AppNavigator

    @Binds
    fun bindNavigationHandler(impl: NavigationDispatcher): NavigationHandler
}