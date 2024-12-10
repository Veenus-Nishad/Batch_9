package com.example.shoppingappcustomer.presentation.prodctDetails

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingappcustomer.R
import com.example.shoppingappcustomer.ui.theme.PinkSoft


@Composable
fun DressSizeAndQuantity(text:String) {
    Box(modifier = Modifier.border(width = 1.dp, color = PinkSoft).size(35.dp), contentAlignment = Alignment.Center){
        Text(text, style = TextStyle(
            color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.W700,
            fontFamily = FontFamily(Font(R.font.montserrat_regular))
        )
        )
    }
}
