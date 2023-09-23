package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.LocalRepository
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val direction: DetailDirection,
    private val localRepository: LocalRepository
) : ViewModel(), DetailContract.ViewModel {

    override val container =
        container<DetailContract.UIState, DetailContract.SideEffect>(DetailContract.UIState.Init)

    override fun onEventDispatcher(intent: DetailContract.Intent) {
        when (intent) {
            is DetailContract.Intent.AddFav -> {
                localRepository.addToFav(intent.coffeeData)
            }

            is DetailContract.Intent.RemoveFav -> {
                localRepository.deleteFromFav(intent.coffeeData)
            }

            DetailContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }
        }
    }
}