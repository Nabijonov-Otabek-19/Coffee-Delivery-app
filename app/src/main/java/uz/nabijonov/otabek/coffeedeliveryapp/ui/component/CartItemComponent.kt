package uz.nabijonov.otabek.coffeedeliveryapp.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import uz.nabijonov.otabek.coffeedeliveryapp.R
import uz.nabijonov.otabek.coffeedeliveryapp.data.common.CoffeeData
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartItemComponent(
    modifier: Modifier = Modifier, item: CoffeeData,
    onItemClick: (CoffeeData) -> Unit,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    onDeleteClick: () -> Unit
) {

    Card(
        modifier = modifier.padding(
            horizontal = 12.dp,
            vertical = 12.dp
        ),
        colors = CardDefaults.cardColors(containerColor = ItemBackground),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { onItemClick.invoke(item) }
    ) {
        Row(
            modifier = Modifier
                .background(color = ItemBackground)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(12.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imgUrl)
                    .crossfade(true)
                    .build(),
                placeholder = rememberAsyncImagePainter(model = R.drawable.ic_placeholder),
                error = painterResource(id = R.drawable.ic_coffee_cup),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .width(0.dp)
                    .weight(1f, true)
            ) {
                Text(
                    text = item.title,
                    fontFamily = customFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Text(
                    text = "${item.price}$",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier,
                    color = Color.White
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.End
            ) {

                IconButton(onClick = {
                    onDeleteClick.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = DeleteButtonBack,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .background(
                            color = ItemAddBackground,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .height(30.dp)
                ) {
                    OutlinedIconButton(
                        onClick = { onMinusClick.invoke() },
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(
                            0.dp,
                            color = ButtonBackground
                        ),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = ButtonBackground
                        ),
                        modifier = Modifier.size(30.dp),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_remove),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = BackgroundDark)
                        )
                    }

                    Text(text = item.count.toString(), fontSize = 16.sp, color = Color.White)

                    OutlinedIconButton(
                        onClick = { onPlusClick.invoke() },
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(
                            0.dp,
                            color = ButtonBackground
                        ),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = ButtonBackground
                        ),
                        modifier = Modifier.size(30.dp),
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
}