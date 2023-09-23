package uz.nabijonov.otabek.coffeedeliveryapp.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import uz.nabijonov.otabek.coffeedeliveryapp.R
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.BackgroundDark
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.ButtonBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.ItemAddBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.ItemBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.customFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeItemComponent(
    modifier: Modifier = Modifier,
    item: CoffeeData,
    onItemClick: (CoffeeData) -> Unit,
    onAddClick: () -> Unit
) {

    Card(
        modifier = modifier.padding(horizontal = 12.dp, vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = ItemBackground),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { onItemClick.invoke(item) }
    ) {
        Column(
            modifier = Modifier
                .background(color = ItemBackground)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .size(125.dp)
                    .clip(RoundedCornerShape(15.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imgUrl)
                    .crossfade(true)
                    .build(),
                placeholder = rememberAsyncImagePainter(model = R.drawable.ic_placeholder),
                error = painterResource(id = R.drawable.ic_coffee_cup),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Text(
                text = item.title,
                fontFamily = customFontFamily,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 16.dp),
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .background(
                        color = ItemAddBackground,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth()
                    .height(40.dp)
            ) {

                Box(
                    modifier = Modifier
                        .width(0.dp)
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${item.price}$",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier,
                        color = Color.White
                    )
                }

                OutlinedIconButton(
                    onClick = { onAddClick.invoke() },
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(0.dp, color = ButtonBackground),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = ButtonBackground
                    ),
                    modifier = Modifier.size(40.dp),
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

/*
@Composable
@Preview
fun CoffeeItemPreview() {
    CoffeeItemComponent(item = CoffeeData(0, "Cinnamon & Cocoa", "", "", 0), onItemClick = {}) {

    }
}*/