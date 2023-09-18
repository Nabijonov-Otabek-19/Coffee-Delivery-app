package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.cart

import uz.nabijonov.otabek.coffeedeliveryapp.navigation.AppNavigator
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.pay.PayScreen
import javax.inject.Inject

class CartDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : CartContract.Direction {

    override suspend fun navigateToPayScreen() {
        appNavigator.navigateTo(PayScreen())
    }
}