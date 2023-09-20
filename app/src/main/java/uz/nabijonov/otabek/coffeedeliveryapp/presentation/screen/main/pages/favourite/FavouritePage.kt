package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.pages.favourite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.nabijonov.otabek.coffeedeliveryapp.R
import uz.nabijonov.otabek.coffeedeliveryapp.ui.component.EmptyCartComponent
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.Background
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.CoffeeDeliveryAppTheme
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.customFontFamily

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
        CoffeeDeliveryAppTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold(topBar = { AppBarFavourite() }) {
                    FavouritePageComponent(Modifier.padding(it))
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
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = Background)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmptyCartComponent()
    }
}