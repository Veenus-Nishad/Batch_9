package com.example.shoppingappcustomer.presentation.prodctDetails


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingappcustomer.R


@Preview(showSystemUi = true)
@Composable
fun ProductDetails() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.dress),
            modifier = Modifier
                .height(500.dp)
                .fillMaxWidth(),
            contentDescription = null
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 390.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            "One Shoulder Linen Dress", modifier = Modifier.fillMaxWidth(), style = TextStyle(
                color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.W700,
                fontFamily = FontFamily(Font(R.font.montserrat_regular))
            )
        )
        Spacer(Modifier.width(8.dp))
        Image(
            painter = painterResource(R.drawable.rating),
            contentDescription = null,
            modifier = Modifier
                .height(26.dp)
                .width(167.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Rs. 5740", modifier = Modifier.fillMaxWidth(), style = TextStyle(
                color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.W700,
                fontFamily = FontFamily(Font(R.font.montserrat_regular))
            )
        )
        Spacer(Modifier.width(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Size", style = TextStyle(
                    color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.W700,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular))
                )
            )
            Text(
                text = "See more", style = TextStyle(
                    color = Color(0xFFF68B8B), fontSize = 14.sp, fontWeight = FontWeight.W700,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular))
                )
            )
        }
        Spacer(Modifier.height(8.dp))
        Row(modifier =Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)){
                DressSizeAndQuantity("UK 8")
                DressSizeAndQuantity("UK 10")
                DressSizeAndQuantity("UK 12")}
            Spacer(Modifier.width(93.dp))
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)){
                Icon(imageVector = Icons.Outlined.Add, contentDescription = null,modifier = Modifier.size(24.dp))
                DressSizeAndQuantity("1")
                Icon(imageVector = Icons.Outlined.Remove, contentDescription = null,modifier = Modifier.size(24.dp))}

        }
    }


}