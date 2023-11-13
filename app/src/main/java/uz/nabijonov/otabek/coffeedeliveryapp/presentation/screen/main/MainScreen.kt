package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import uz.nabijonov.otabek.coffeedeliveryapp.navigation.AppScreen
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.favourite.FavouritePage
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.home.HomePage
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.cart.CartPage
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.Background
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.BackgroundDark
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.ButtonBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.CoffeeDeliveryAppTheme
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.UnSelectedButton

class MainScreen : AppScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        CoffeeDeliveryAppTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                TabNavigator(tab = HomePage) {
                    Scaffold(
                        content = {
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .padding(it)
                            ) {
                                CurrentTab()
                            }
                        },
                        bottomBar = {
                            MyNavigationBar()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun MyNavigationBar() = NavigationBar(
    modifier = Modifier.height(60.dp),
    containerColor = BackgroundDark
) {
    TabNavigationItem(tab = HomePage)
    TabNavigationItem(tab = CartPage)
    TabNavigationItem(tab = FavouritePage)
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = ButtonBackground,
            unselectedIconColor = UnSelectedButton,
            indicatorColor = Background
        ),
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}