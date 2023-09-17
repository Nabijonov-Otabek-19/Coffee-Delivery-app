package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.nabijonov.otabek.coffeedeliveryapp.domain.repository.Repository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val direction: HomeDirection,
    private val repository: Repository
) : ViewModel(), HomeContract.ViewModel {

    override val container =
        container<HomeContract.UIState, HomeContract.SideEffect>(HomeContract.UIState.Loading)

    override fun onEventDispatcher(intent: HomeContract.Intent) {
        when (intent) {
            HomeContract.Intent.OpenDetailScreen -> {
                viewModelScope.launch {
                    direction.navigateToDetailScreen()
                }
            }

            HomeContract.Intent.OpenSearchScreen -> {
                viewModelScope.launch {
                    direction.navigateToSearchScreen()
                }
            }

            is HomeContract.Intent.LoadData -> {
                repository.loadData(intent.categoryName).onEach { result ->
                    result.onSuccess {
                        intent { reduce { HomeContract.UIState.PrepareData(it) } }
                    }
                    result.onFailure {
                        intent {
                            postSideEffect(
                                HomeContract.SideEffect.Toast(
                                    it.message ?: "Exception occured!"
                                )
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}