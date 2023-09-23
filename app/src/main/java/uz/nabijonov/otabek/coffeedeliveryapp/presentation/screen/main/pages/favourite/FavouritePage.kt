package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.favourite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.nabijonov.otabek.coffeedeliveryapp.R
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.home.HomeContract
import uz.nabijonov.otabek.coffeedeliveryapp.ui.component.EmptyCartComponent
import uz.nabijonov.otabek.coffeedeliveryapp.ui.component.CoffeeItemComponent
import uz.nabijonov.otabek.coffeedeliveryapp.ui.component.FavItemComponent
import uz.nabijonov.otabek.coffeedeliveryapp.ui.component.LoadingComponent
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.Background
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.CoffeeDeliveryAppTheme
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.customFontFamily
import uz.nabijonov.otabek.coffeedeliveryapp.utils.logger
import uz.nabijonov.otabek.coffeedeliveryapp.utils.toast

object FavouritePage : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.favourite_tab)
            val icon = rememberVectorPainter(Icons.Default.Favorite)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val viewModel: FavouriteContract.ViewModel = getViewModel<FavouriteViewModel>()
        val uiState = viewModel.collectAsState()
        val context = LocalContext.current

        CoffeeDeliveryAppTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold(topBar = { AppBarFavourite() }) {
                    FavouritePageComponent(
                        modifier = Modifier.padding(it),
                        uiState = uiState,
                        onEventDispatcher = viewModel::onEventDispatcher
                    )
                }
            }
        }

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is FavouriteContract.SideEffect.Toast -> {
                    logger("FavPageScreen Error = " + sideEffect.message)
                    toast(context, sideEffect.message)
                }
            }
        }
    }
}

@Composable
private fun AppBarFavourite() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Background)
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Favourite",
            fontSize = 20.sp,
            fontFamily = customFontFamily,
            color = Color.White
        )
    }
}

@Composable
private fun FavouritePageComponent(
    modifier: Modifier = Modifier,
    uiState: State<FavouriteContract.UIState>,
    onEventDispatcher: (FavouriteContract.Intent) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Background)
    ) {
        when (uiState.value) {
            FavouriteContract.UIState.Loading -> {
                LoadingComponent()
                onEventDispatcher(FavouriteContract.Intent.LoadData)
            }

            is FavouriteContract.UIState.PrepareData -> {
                val data = (uiState.value as FavouriteContract.UIState.PrepareData).coffeeData

                if (data.isEmpty()) {
                    EmptyCartComponent()
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.Center,
                        contentPadding = PaddingValues(4.dp),
                    ) {
                        items(data.size) { index ->
                            FavItemComponent(
                                imgUrl = data[index].imgUrl,
                                title = data[index].title
                            )
                        }
                    }
                }
            }
        }
    }
}