package uz.nabijonov.otabek.coffeedeliveryapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.ButtonBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.CategoryBackground


@Composable
fun CartBottomComponent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 16.dp)
    ) {

        OutlinedButton(
            onClick = { },
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
@Preview
fun CartBottomPreview() {
    CartBottomComponent()
}