package uz.nabijonov.otabek.coffeedeliveryapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    /*@[Binds Singleton]
    fun bindHomeScreenDirection(impl: HomeDirection): HomeViewContract.Direction*/
}