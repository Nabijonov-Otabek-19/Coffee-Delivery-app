package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.nabijonov.otabek.coffeedeliveryapp.R
import uz.nabijonov.otabek.coffeedeliveryapp.ui.component.CoffeeItemComponent
import uz.nabijonov.otabek.coffeedeliveryapp.ui.component.LoadingComponent
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.Background
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.BackgroundDark
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.ButtonBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.CoffeeDeliveryAppTheme
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.UnSelectedButton
import uz.nabijonov.otabek.coffeedeliveryapp.utils.logger
import uz.nabijonov.otabek.coffeedeliveryapp.utils.toast

object HomePage : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.home_tab)
            val icon = rememberVectorPainter(Icons.Default.Home)

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

        val viewModel: HomeContract.ViewModel = getViewModel<HomeViewModel>()
        val uiState = viewModel.collectAsState()

        val context = LocalContext.current

        CoffeeDeliveryAppTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold(topBar = {
                    TopBarHome(onEventDispatcher = viewModel::onEventDispatcher)
                }) {
                    HomePageComponent(
                        modifier = Modifier.padding(it),
                        uiState = uiState,
                        onEventDispatcher = viewModel::onEventDispatcher
                    )
                }
            }
        }

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is HomeContract.SideEffect.Toast -> {
                    logger("HomePageScreen Error = " + sideEffect.message)
                    toast(context, sideEffect.message)
                }
            }
        }
    }
}

@Composable
fun TopBarHome(onEventDispatcher: (HomeContract.Intent) -> Unit) {
    Column(
        modifier = Modifier
            .background(color = Background)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Coffee\nLand",
                fontSize = 24.sp,
                fontFamily = FontFamily.Cursive,
                color = ButtonBackground
            )

            Image(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(8.dp)
                .background(color = BackgroundDark, shape = RoundedCornerShape(8.dp))
                .padding(start = 16.dp)
                .clickable {
                    onEventDispatcher(HomeContract.Intent.OpenSearchScreen)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = UnSelectedButton,
                modifier = Modifier
                    .size(28.dp)
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = "Browse your favourite coffee..",
                fontFamily = FontFamily.Cursive,
                fontSize = 18.sp,
                color = UnSelectedButton
            )
        }
    }
}

@Composable
fun HomePageComponent(
    modifier: Modifier = Modifier,
    uiState: State<HomeContract.UIState>,
    onEventDispatcher: (HomeContract.Intent) -> Unit,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp)
            .background(color = Background)
    ) {

        when (uiState.value) {
            HomeContract.UIState.Loading -> {
                LoadingComponent()
                onEventDispatcher(HomeContract.Intent.LoadData("Cappuccino"))
            }

            is HomeContract.UIState.PrepareData -> {
                val data = (uiState.value as HomeContract.UIState.PrepareData).coffeeData

                logger("Coffee List = ${data.size}")
                logger("Coffee Data = ${data[0].title}")

                if (data.isEmpty()) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(100.dp),
                        painter = painterResource(id = R.drawable.ic_coffee_cup),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = Color.White)
                    )
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.Center,
                        contentPadding = PaddingValues(12.dp)
                    ) {
                        items(data.size) { index ->
                            logger("Index = $index")
                            CoffeeItemComponent(
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
                                item = data[index],
                                onItemClick = {
                                    onEventDispatcher(HomeContract.Intent.OpenDetailScreen)
                                },
                                onAddClick = {
                                    // add to room DB
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}