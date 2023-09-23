package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.nabijonov.otabek.coffeedeliveryapp.R
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData
import uz.nabijonov.otabek.coffeedeliveryapp.navigation.AppScreen
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.search.SearchContract
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.search.SearchViewModel
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.Background
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.ButtonBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.CategoryBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.CoffeeDeliveryAppTheme
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.DeleteButtonBack
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.customFontFamily
import uz.nabijonov.otabek.coffeedeliveryapp.utils.toast

class DetailScreen(val coffeeData: CoffeeData) : AppScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val viewModel: DetailContract.ViewModel = getViewModel<DetailViewModel>()
        val uiState = viewModel.collectAsState()

        val context = LocalContext.current

        CoffeeDeliveryAppTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold(bottomBar = {
                    DetailBottomComponent(
                        coffeeData = coffeeData,
                        onEventDispatcher = viewModel::onEventDispatcher
                    )
                }) {
                    DetailScreenComponent(
                        modifier = Modifier.padding(it),
                        coffeeData = coffeeData,
                        uiState = uiState,
                        onEventDispatcher = viewModel::onEventDispatcher
                    )
                }
            }
        }

        LifecycleEffect(
            onStarted = {
                viewModel::onEventDispatcher.invoke(DetailContract.Intent.CheckFavProduct(coffeeData.id))
            }
        )

        viewModel.collectSideEffect { sideEffect ->
            when (sideEffect) {
                is DetailContract.SideEffect.Toast -> {
                    toast(context, sideEffect.message)
                }
            }
        }
    }
}

@Composable
private fun DetailBottomComponent(
    coffeeData: CoffeeData,
    onEventDispatcher: (DetailContract.Intent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${coffeeData.price}$",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .width(0.dp)
                .weight(1f, true)
        )

        OutlinedButton(
            onClick = {
                // Pay screen
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .width(0.dp)
                .weight(3f, true)
                .padding(horizontal = 8.dp, vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonBackground
            )
        ) {
            Text(
                text = "BUY NOW",
                fontFamily = customFontFamily,
                color = CategoryBackground,
                fontSize = 22.sp
            )
        }
    }
}

@Composable
private fun DetailScreenComponent(
    modifier: Modifier = Modifier,
    coffeeData: CoffeeData,
    uiState: State<DetailContract.UIState>,
    onEventDispatcher: (DetailContract.Intent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState(), enabled = true)
            .background(color = Background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(300.dp)
                .clip(RoundedCornerShape(12.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(coffeeData.imgUrl)
                .crossfade(true)
                .build(),
            placeholder = rememberAsyncImagePainter(model = R.drawable.ic_placeholder),
            error = painterResource(id = R.drawable.ic_coffee_cup),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = coffeeData.title,
                fontSize = 22.sp,
                fontFamily = customFontFamily,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            when (uiState.value) {
                is DetailContract.UIState.IsFavProduct -> {
                    val isSaved = (uiState.value as DetailContract.UIState.IsFavProduct).isFav

                    IconButton(onClick = {
                        if (isSaved) {
                            onEventDispatcher(DetailContract.Intent.RemoveFav(coffeeData))
                        } else {
                            onEventDispatcher(DetailContract.Intent.AddFav(coffeeData))
                        }
                        onEventDispatcher(DetailContract.Intent.CheckFavProduct(coffeeData.id))
                    }) {
                        Icon(
                            imageVector = if (isSaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            tint = DeleteButtonBack,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }

                else -> {}
            }
        }

        Text(
            text = coffeeData.description,
            fontSize = 18.sp,
            fontFamily = customFontFamily,
            color = Color.White,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}