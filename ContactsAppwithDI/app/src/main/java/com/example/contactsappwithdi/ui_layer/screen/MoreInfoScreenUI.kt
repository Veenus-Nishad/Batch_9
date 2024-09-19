package com.example.contactsappwithdi.ui_layer.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contactsappwithdi.R

@Preview(showSystemUi = true)
@Composable
fun MoreInfoScreenUI() {

    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(90.dp))
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .fillMaxWidth()
                .background(Color.White)
                .height(185.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Name")
            Text(text = "Phone +91 99988882")

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.name_icon),
                    contentDescription = null, contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .aspectRatio(1f)
                )
                Image(
                    painter = painterResource(id = R.drawable.name_icon),
                    contentDescription = null, contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .aspectRatio(1f)
                )
                Image(
                    painter = painterResource(id = R.drawable.name_icon),
                    contentDescription = null, contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .aspectRatio(1f)
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))

        Column(
            modifier = Modifier.clip(RoundedCornerShape(14.dp))
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth().height(35.dp).padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Text(text = "WhatsApp")
                Image(
                    painter = painterResource(id = R.drawable.name_icon),
                    contentDescription = null, contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .aspectRatio(1f)
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 8.dp),
                thickness = 2.dp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth().height(35.dp).padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Text(text = "Email")
                Image(
                    painter = painterResource(id = R.drawable.name_icon),
                    contentDescription = null, contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .aspectRatio(1f)
                )
            }
        }
    }
}
