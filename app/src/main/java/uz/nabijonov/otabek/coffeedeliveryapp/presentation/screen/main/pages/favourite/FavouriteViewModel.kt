package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.LocalRepository
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val direction: FavouriteDirection,
    private val localRepository: LocalRepository
) : ViewModel(), FavouriteContract.ViewModel {

    override val container =
        container<FavouriteContract.UIState, FavouriteContract.SideEffect>(FavouriteContract.UIState.Loading)

    override fun onEventDispatcher(intent: FavouriteContract.Intent) {
        when (intent) {
            FavouriteContract.Intent.LoadData -> {
                // load data from local
            }

            is FavouriteContract.Intent.Delete -> {
                // delete from favourite local db
            }

            is FavouriteContract.Intent.OpenDetailScreen -> {
                viewModelScope.launch {
                    direction.navigateToDetailScreen(intent.coffeeData)
                }
            }
        }
    }
}