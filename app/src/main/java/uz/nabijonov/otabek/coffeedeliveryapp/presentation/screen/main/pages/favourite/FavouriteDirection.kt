package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.favourite

import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData
import uz.nabijonov.otabek.coffeedeliveryapp.navigation.AppNavigator
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.detail.DetailScreen
import javax.inject.Inject


class FavouriteDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : FavouriteContract.Direction {

    override suspend fun navigateToDetailScreen(coffeeData: CoffeeData) {
        appNavigator.navigateTo(DetailScreen(coffeeData))
    }
}