package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.favourite

import org.orbitmvi.orbit.ContainerHost
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData

interface FavouriteContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object Loading : UIState
        data class PrepareData(val coffeeData: List<CoffeeData>) : UIState
    }

    sealed interface SideEffect {
        data class Toast(val message: String) : SideEffect
    }

    sealed interface Intent {
        object LoadData : Intent
        data class OpenDetailScreen(val coffeeData: CoffeeData): Intent
        data class Delete(val coffeeData: CoffeeData) : Intent
    }

    interface Direction {
        suspend fun navigateToDetailScreen(coffeeData: CoffeeData)
    }
}