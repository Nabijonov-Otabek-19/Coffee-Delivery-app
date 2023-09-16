package uz.nabijonov.otabek.coffeedeliveryapp.navigation

import kotlinx.coroutines.flow.Flow

interface NavigationHandler {
    val navigationBuffer: Flow<NavigationArg>
}