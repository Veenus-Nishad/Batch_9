package com.example.shoppingappcustomer.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppingappcustomer.R
import com.example.shoppingappcustomer.domain.models.Category

@Composable
fun CategoryEachRow(
    category: Category,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Card(
            modifier = Modifier.size(75.dp),
            shape = CircleShape,
            border = BorderStroke(1.dp, Color(0xFF8C8585)),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            AsyncImage(
                model = category.imageUri,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.frock),
                error = painterResource(R.drawable.frock)
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = category.name,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Start,
                color = Color.Black,
            )
        )
    }
}