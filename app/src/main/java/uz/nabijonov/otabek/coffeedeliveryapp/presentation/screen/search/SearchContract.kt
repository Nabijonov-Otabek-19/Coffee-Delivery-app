package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.search

import org.orbitmvi.orbit.ContainerHost
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData

interface SearchContract {
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
        data class LoadData(val title: String) : Intent
        object Back : Intent
    }

    interface Direction {
        suspend fun back()
    }
}