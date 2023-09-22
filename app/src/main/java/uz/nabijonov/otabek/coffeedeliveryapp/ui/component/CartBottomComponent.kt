package uz.nabijonov.otabek.coffeedeliveryapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.Background
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.ButtonBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.CategoryBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.customFontFamily


@Composable
fun CartBottomComponent(onPayClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Background)
            .padding(horizontal = 16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .padding(horizontal = 16.dp)
                .background(color = CategoryBackground, shape = CutCornerShape(10.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = "Apply Coupon here",
                fontFamily = customFontFamily,
                fontSize = 18.sp,
                color = ButtonBackground
            )

            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = ButtonBackground
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Divider(thickness = 1.dp, color = DividerDefaults.color)

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Delivery Charges",
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = customFontFamily
            )

            Text(
                text = "49$",
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = customFontFamily
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Taxes",
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = customFontFamily
            )

            Text(
                text = "64.87$",
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = customFontFamily
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Divider(thickness = 1.dp, color = DividerDefaults.color)

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Grand Total",
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = customFontFamily
            )

            Text(
                text = "1009.87$",
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = customFontFamily
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = {
                onPayClick.invoke()
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonBackground
            )
        ) {
            Text(
                text = "Pay Now",
                fontSize = 22.sp,
                color = CategoryBackground
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CartBottomPreview() {
    CartBottomComponent {

    }
}