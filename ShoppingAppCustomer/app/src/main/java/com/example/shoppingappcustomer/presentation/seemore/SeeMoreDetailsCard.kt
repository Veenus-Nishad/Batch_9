package com.example.shoppingappcustomer.presentation.seemore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shoppingappcustomer.domain.models.ProductModel


@Composable
fun SeeMoreDetailsCard(
    modifier: Modifier, product: ProductModel,
) {
    Row(modifier = modifier) {
        AsyncImage(
            model=product.image,
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
        )
        Column(modifier = Modifier.padding(start = 18.dp)) {
            Row {
                Text(product.name, modifier = Modifier.width(83.dp))
                Text(product.finalprice, modifier = Modifier.padding(start = 13.dp))
            }
            Text("GF1025", modifier = Modifier.padding(top = 8.dp))
            Text("Size: S", modifier = Modifier.padding(top = 8.dp))
            Text("Color: Blue", modifier = Modifier.padding(top = 8.dp))

        }
    }
}
