package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.home

import org.orbitmvi.orbit.ContainerHost
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData

interface HomeContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object Init : UIState
        object Loading : UIState
        data class PrepareData(val coffeeData: List<CoffeeData>) : UIState
    }

    sealed interface SideEffect {
        data class Toast(val message: String) : SideEffect
    }

    sealed interface Intent {
        data class OpenDetailScreen(val coffeeData: CoffeeData) : Intent
        object OpenSearchScreen : Intent
        object SetLoading : Intent
        data class LoadData(val categoryName: String) : Intent
        data class AddToDB(val coffeeData: CoffeeData) : Intent
    }

    interface Direction {
        suspend fun navigateToDetailScreen(coffeeData: CoffeeData)
        suspend fun navigateToSearchScreen()
    }
}