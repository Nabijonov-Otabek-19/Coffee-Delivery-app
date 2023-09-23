package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.nabijonov.otabek.coffeedeliveryapp.navigation.AppScreen
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.Background
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.BackgroundDark
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.CoffeeDeliveryAppTheme
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.UnSelectedButton

class SearchScreen : AppScreen() {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val viewModel: SearchContract.ViewModel = getViewModel<SearchViewModel>()
        val uiState = viewModel.collectAsState()

        CoffeeDeliveryAppTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold(topBar = { TopBarSearch() }) {
                    SearchScreenComponent(
                        modifier = Modifier.padding(it),
                        uiState = uiState,
                        onEventDispatcher = viewModel::onEventDispatcher
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBarSearch() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreenComponent(
    modifier: Modifier = Modifier,
    uiState: State<SearchContract.UIState>,
    onEventDispatcher: (SearchContract.Intent) -> Unit
) {
    Column(
        modifier = modifier
            .background(color = Background)
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = { newString ->
                // send request and get response
            },
            modifier = Modifier
                .background(
                    color = BackgroundDark,
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
                .height(80.dp)
                .padding(8.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = UnSelectedButton,
                    modifier = Modifier
                        .size(28.dp)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = BackgroundDark
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "GridView", fontSize = 30.sp, color = Color.White)
    }
}