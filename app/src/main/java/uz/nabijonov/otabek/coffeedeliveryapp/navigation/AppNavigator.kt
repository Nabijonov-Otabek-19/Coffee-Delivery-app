package uz.nabijonov.otabek.coffeedeliveryapp.navigation


interface AppNavigator {
    suspend fun navigateTo(screen: AppScreen)
    suspend fun back()
}