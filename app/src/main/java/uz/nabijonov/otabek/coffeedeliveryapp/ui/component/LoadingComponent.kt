package uz.nabijonov.otabek.coffeedeliveryapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.*
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import uz.nabijonov.otabek.coffeedeliveryapp.R

@Composable
fun LoadingComponent() {

    val rawComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_loading1))
    val progress by animateLottieCompositionAsState(
        rawComposition,
        iterations = LottieConstants.IterateForever,
        restartOnPlay = false,
        isPlaying = true,
        speed = 1f
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LottieAnimation(
            contentScale = ContentScale.Fit,
            composition = rawComposition,
            progress = progress,
            modifier = Modifier.size(400.dp)
        )
    }
}