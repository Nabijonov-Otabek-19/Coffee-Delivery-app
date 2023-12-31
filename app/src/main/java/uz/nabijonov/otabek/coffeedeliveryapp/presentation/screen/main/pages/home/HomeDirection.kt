package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.home

import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData
import uz.nabijonov.otabek.coffeedeliveryapp.navigation.AppNavigator
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.detail.DetailScreen
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.search.SearchScreen
import javax.inject.Inject

class HomeDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : HomeContract.Direction {
    override suspend fun navigateToDetailScreen(coffeeData: CoffeeData) {
        appNavigator.navigateTo(DetailScreen(coffeeData))
    }

    override suspend fun navigateToSearchScreen() {
        appNavigator.navigateTo(SearchScreen())
    }
}