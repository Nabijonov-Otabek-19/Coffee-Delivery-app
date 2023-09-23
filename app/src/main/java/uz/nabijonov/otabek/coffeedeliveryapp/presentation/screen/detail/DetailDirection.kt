package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.detail

import uz.nabijonov.otabek.coffeedeliveryapp.navigation.AppNavigator
import javax.inject.Inject

class DetailDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : DetailContract.Direction {

    override suspend fun back() {
        appNavigator.back()
    }
}