package uz.nabijonov.otabek.coffeedeliveryapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.Repository
import uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.RepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindRepository(impl: RepositoryImpl): Repository
}