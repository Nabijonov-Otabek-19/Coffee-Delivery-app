package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.Repository
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository,
    private val direction: SearchDirection
) : SearchContract.ViewModel, ViewModel() {

    override val container =
        container<SearchContract.UIState, SearchContract.SideEffect>(SearchContract.UIState.Init)

    override fun onEventDispatcher(intent: SearchContract.Intent) {
        when (intent) {
            is SearchContract.Intent.LoadData -> {
                // load data
            }

            SearchContract.Intent.Back -> {
                viewModelScope.launch {

                }
            }
        }
    }
}