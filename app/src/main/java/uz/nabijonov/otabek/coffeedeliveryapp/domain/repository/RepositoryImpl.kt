package uz.nabijonov.otabek.coffeedeliveryapp.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    override fun loadData(): Flow<Result<List<CoffeeData>>> = flow {

        emit(Result.success(listOf()))
    }
}