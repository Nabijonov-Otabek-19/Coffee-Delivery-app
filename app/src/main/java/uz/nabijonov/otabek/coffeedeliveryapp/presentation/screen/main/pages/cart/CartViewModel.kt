package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.LocalRepository
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val direction: CartDirection
) : ViewModel(), CartContract.ViewModel {

    override val container =
        container<CartContract.UIState, CartContract.SideEffect>(CartContract.UIState.Loading)

    override fun onEventDispatcher(intent: CartContract.Intent) {
        when (intent) {
            CartContract.Intent.LoadData -> {
                localRepository.getAllCartProducts().onEach {
                    var total = 0
                    it.forEach { data ->
                        total += (data.price * data.count)
                    }
                    intent { reduce { CartContract.UIState.PrepareData(it, total) } }
                }.launchIn(viewModelScope)
            }

            is CartContract.Intent.IncCount -> {
                localRepository.incrementProductCount(intent.id, intent.count)
            }

            is CartContract.Intent.DecCount -> {
                localRepository.decrementProductCount(intent.id, intent.count)
            }

            is CartContract.Intent.Delete -> {
                localRepository.deleteFromCart(intent.coffeeData)
            }

            CartContract.Intent.OpenPayScreen -> {
                viewModelScope.launch {
                    direction.navigateToPayScreen()
                }
            }
        }
    }

}