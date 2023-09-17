package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.search

import uz.nabijonov.otabek.coffeedeliveryapp.navigation.AppNavigator
import javax.inject.Inject

class SearchDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : SearchContract.Direction {

    override suspend fun back() {
        appNavigator.back()
    }
}