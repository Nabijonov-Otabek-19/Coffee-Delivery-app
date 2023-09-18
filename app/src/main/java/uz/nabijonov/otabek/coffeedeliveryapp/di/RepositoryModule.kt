package uz.nabijonov.otabek.coffeedeliveryapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.LocalRepository
import uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.ServerRepository
import uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.impl.LocalRepositoryImpl
import uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.impl.ServerRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindServerRepository(impl: ServerRepositoryImpl): ServerRepository

    @[Binds Singleton]
    fun bindLocalRepository(impl: LocalRepositoryImpl): LocalRepository
}