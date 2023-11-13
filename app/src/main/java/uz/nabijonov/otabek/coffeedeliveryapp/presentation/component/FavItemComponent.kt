package uz.nabijonov.otabek.coffeedeliveryapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import uz.nabijonov.otabek.coffeedeliveryapp.R
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.ItemBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.customFontFamily

@Composable
fun FavItemComponent(
    imgUrl: String,
    title: String,
    onLongDeleteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .pointerInput(key1 = Unit) {
                detectTapGestures(onLongPress = { onLongDeleteClick.invoke() })
            }
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = ItemBackground),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .size(125.dp)
                    .clip(RoundedCornerShape(15.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imgUrl)
                    .crossfade(true)
                    .build(),
                placeholder = rememberAsyncImagePainter(model = R.drawable.ic_placeholder),
                error = painterResource(id = R.drawable.ic_coffee_cup),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Text(
                text = title,
                fontFamily = customFontFamily,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 16.dp),
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}