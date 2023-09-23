package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.cart

import org.orbitmvi.orbit.ContainerHost
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData

interface CartContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object Loading : UIState
        data class PrepareData(val coffeeData: List<CoffeeData>, val total: Int) : UIState
    }

    sealed interface SideEffect {
        data class Toast(val message: String) : SideEffect
    }

    sealed interface Intent {
        object LoadData : Intent
        data class IncCount(val id: Int, val count: Int) : Intent
        data class DecCount(val id: Int, val count: Int) : Intent
        data class Delete(val coffeeData: CoffeeData) : Intent
        object OpenPayScreen : Intent
    }

    interface Direction {
        suspend fun navigateToPayScreen()
    }
}