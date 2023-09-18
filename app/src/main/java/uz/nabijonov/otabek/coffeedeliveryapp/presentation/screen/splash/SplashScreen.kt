package uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.delay
import uz.nabijonov.otabek.coffeedeliveryapp.R
import uz.nabijonov.otabek.coffeedeliveryapp.navigation.AppScreen
import uz.nabijonov.otabek.coffeedeliveryapp.presentation.screen.main.MainScreen
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.Background
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.CoffeeDeliveryAppTheme
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.customFontFamily

class SplashScreen : AppScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        CoffeeDeliveryAppTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold {
                    SplashComponent(modifier = Modifier.padding(it))
                }
            }
        }

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = true) {
            delay(2000L)
            navigator.replace(MainScreen())
        }
    }
}

@Composable
fun SplashComponent(modifier: Modifier = Modifier) {
    val rawComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_cup3))
    val progress by animateLottieCompositionAsState(
        rawComposition,
        iterations = LottieConstants.IterateForever,
        restartOnPlay = false,
        isPlaying = true,
        speed = 1f
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = Background)
    ) {
        LottieAnimation(
            contentScale = ContentScale.Fit,
            composition = rawComposition,
            progress = progress,
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Coffee Land",
            color = Color.White,
            fontSize = 40.sp,
            fontFamily = customFontFamily
        )
    }
}