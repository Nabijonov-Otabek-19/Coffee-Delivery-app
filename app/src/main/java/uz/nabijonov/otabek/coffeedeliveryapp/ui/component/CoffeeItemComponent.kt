package uz.nabijonov.otabek.coffeedeliveryapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uz.nabijonov.otabek.coffeedeliveryapp.R
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.BackgroundDark
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.ButtonBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeItemComponent(
    item: CoffeeData,
    onItemClick: (CoffeeData) -> Unit,
    onAddClick: () -> Unit
) {
    Card(
        modifier = Modifier,
        colors = CardDefaults.cardColors(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { onItemClick.invoke(item) }
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(70.dp)
                    .clip(RoundedCornerShape(12.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imgUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_coffee_cup),
                error = painterResource(id = R.drawable.ic_coffee_cup),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )

            Text(
                text = item.title,
                fontFamily = FontFamily.Cursive,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.White
            )

            Row {
                Text(
                    text = "${item.price}$",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.White
                )

                IconButton(
                    onClick = { onAddClick.invoke() },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = ButtonBackground
                    ),
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = BackgroundDark
                    )
                }
            }
        }
    }
}