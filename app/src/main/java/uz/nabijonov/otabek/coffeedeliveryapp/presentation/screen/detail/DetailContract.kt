package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.detail

import org.orbitmvi.orbit.ContainerHost
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData

interface DetailContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object Init : UIState
    }

    sealed interface SideEffect {
        data class Toast(val message: String) : SideEffect
    }

    sealed interface Intent {
        data class AddFav(val coffeeData: CoffeeData) : Intent
        data class RemoveFav(val coffeeData: CoffeeData) : Intent
        object Back : Intent
    }

    interface Direction {
        suspend fun back()
    }
}