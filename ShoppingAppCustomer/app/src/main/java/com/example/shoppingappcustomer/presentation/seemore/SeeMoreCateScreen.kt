package com.example.shoppingappcustomer.presentation.seemore

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shoppingappcustomer.R
import com.example.shoppingappcustomer.presentation.ViewModel


@Composable
fun SeeMoreCateScreen(
    viewModel: ViewModel= hiltViewModel(),modifier: Modifier=Modifier
) {

    val productDetails= viewModel.homeScreenState.collectAsStateWithLifecycle()

    val localContext= LocalContext.current

    when{
        productDetails.value.isLoading->{
            CircularProgressIndicator()
            Log.d("TAG", "IsLoading: ${productDetails.value.isLoading}")
        }
        productDetails.value.products.isNotEmpty()->{
            Log.d("TAG", "SeeMoreCateScreen: ${productDetails.value.products.toString()}")
            Toast.makeText(localContext,productDetails.value.products.toString(), Toast.LENGTH_SHORT).show()
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(R.drawable.ellipse1),
                    contentDescription = null,
                    modifier = Modifier
                        .align(
                            Alignment.TopEnd
                        )
                        .size(185.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 63.dp, start = 18.dp,end=29.dp)
                ) {
                    Text(
                        "See More",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 27.dp)
                    )
                    Row(modifier = Modifier.padding(top=18.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                        Text("See Favorites", modifier = Modifier.padding(8.dp))
                    }
                    Row(modifier = Modifier.padding(top=18.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("Items")
                        Spacer(Modifier.width(144.dp))
                        Text("Price")
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(height = 40.dp, width = 305.dp)
                            .border(width = 2.dp, color = Color(0xFFF68B8B))
                    ) {
                        Row(
                            verticalAlignment = CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                            Text("Search")
                        }
                    }
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.Black,
                        modifier = Modifier.padding(top=36.dp)
                    )
                    LazyColumn {
                        items(
                           productDetails.value.products,
                            key = {it!!.productId}
                        ){
                            SeeMoreDetailsCard(modifier = Modifier.padding(top=18.dp).fillMaxSize(), it!!)
                        }
                    }


                }
            }

        }
        productDetails.value.error.isNotEmpty()->{
            Toast.makeText(localContext,productDetails.value.error, Toast.LENGTH_SHORT).show()
        }
        else->{
            Text("No Data Found")

        }

    }



}