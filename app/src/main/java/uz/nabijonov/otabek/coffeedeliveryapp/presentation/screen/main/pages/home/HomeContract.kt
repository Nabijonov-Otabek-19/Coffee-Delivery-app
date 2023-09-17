package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.home

import org.orbitmvi.orbit.ContainerHost
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData

interface HomeContract {
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
        object OpenDetailScreen : Intent
        object OpenSearchScreen : Intent
        data class LoadData(val categoryName: String) : Intent
    }

    interface Direction {
        suspend fun navigateToDetailScreen()
        suspend fun navigateToSearchScreen()
    }
}