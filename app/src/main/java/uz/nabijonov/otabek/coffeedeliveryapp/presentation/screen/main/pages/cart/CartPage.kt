package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.nabijonov.otabek.coffeedeliveryapp.R
import uz.nabijonov.otabek.coffeedeliveryapp.ui.component.CartBottomComponent
import uz.nabijonov.otabek.coffeedeliveryapp.ui.component.CartItemComponent
import uz.nabijonov.otabek.coffeedeliveryapp.ui.component.EmptyCartComponent
import uz.nabijonov.otabek.coffeedeliveryapp.ui.component.LoadingComponent
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.Background
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.BackgroundDark
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.ButtonBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.CoffeeDeliveryAppTheme
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.ItemAddBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.ItemBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.customFontFamily
import uz.nabijonov.otabek.coffeedeliveryapp.utils.logger
import uz.nabijonov.otabek.coffeedeliveryapp.utils.toast

object CartPage : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.cart_tab)
            val icon = rememberVectorPainter(Icons.Default.ShoppingCart)

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

        val viewModel: CartContract.ViewModel = getViewModel<CartViewModel>()
        val uiState = viewModel.collectAsState()

        val context = LocalContext.current

        CoffeeDeliveryAppTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold(topBar = { AppBarCart() }) {
                    OrderPageComponent(
                        modifier = Modifier.padding(it),
                        uiState = uiState,
                        onEventDispatcher = viewModel::onEventDispatcher
                    )
                }
            }
        }

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is CartContract.SideEffect.Toast -> {
                    logger("HomePageScreen Error = " + sideEffect.message)
                    toast(context, sideEffect.message)
                }
            }
        }
    }
}

@Composable
private fun AppBarCart() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Background)
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Cart",
            fontSize = 20.sp,
            fontFamily = customFontFamily,
            color = Color.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OrderPageComponent(
    modifier: Modifier = Modifier,
    uiState: State<CartContract.UIState>,
    onEventDispatcher: (CartContract.Intent) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Background)
    ) {
        when (uiState.value) {
            CartContract.UIState.Loading -> {
                LoadingComponent()
                onEventDispatcher(CartContract.Intent.LoadData)
            }

            is CartContract.UIState.PrepareData -> {
                val data = (uiState.value as CartContract.UIState.PrepareData).coffeeData

                if (data.isEmpty()) {
                    EmptyCartComponent()
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.dp)
                                .weight(1f, true)
                        ) {
                            items(data.size) { index ->
                                CartItemComponent(
                                    modifier = Modifier,
                                    item = data[index],
                                    onItemClick = {},
                                    onAddClick = {}
                                )
                            }
                        }

                        CartBottomComponent()
                    }
                }
            }
        }
    }
}
