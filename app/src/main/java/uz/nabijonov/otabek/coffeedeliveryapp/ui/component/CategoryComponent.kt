package uz.nabijonov.otabek.coffeedeliveryapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.ButtonBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.CategoryBackground
import uz.nabijonov.otabek.coffeedeliveryapp.ui.theme.customFontFamily
import uz.nabijonov.otabek.coffeedeliveryapp.utils.categoryList


@Composable
fun CategoryComponent(onItemClick: (String) -> Unit) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp)
            .height(45.dp)
            .background(
                color = CategoryBackground,
                shape = CutCornerShape(50.dp)
            )
            .padding(start = 24.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start)
    ) {
        items(categoryList.size) { index ->
            TextButton(onClick = { onItemClick(categoryList[index]) }) {
                Text(
                    text = categoryList[index],
                    modifier = Modifier,
                    fontFamily = customFontFamily,
                    fontSize = 16.sp,
                    color = ButtonBackground
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CategoryPreview() {
    CategoryComponent(onItemClick = {})
}
