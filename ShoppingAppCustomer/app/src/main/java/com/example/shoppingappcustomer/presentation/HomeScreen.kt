package com.example.shoppingappcustomer.presentation

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.shoppingappcustomer.R
import com.example.shoppingappcustomer.presentation.components.CategoryEachRow

@Composable
fun HomeScreenUi(
    viewModel: ViewModel = hiltViewModel(),
    navController: NavController
) {
    val homeScreenState = viewModel.homeScreenState.collectAsStateWithLifecycle()
    val localContext = LocalContext.current

    var searchTextValue by remember {
        mutableStateOf("")
    }

    val listOfProduct = if (searchTextValue.isNotEmpty()) {
        homeScreenState.value.products.filter {
            it!!.name.contains(searchTextValue, ignoreCase = true)
        }
    } else {
        homeScreenState.value.products
    }

    when{
        homeScreenState.value.isLoading->{
            CircularProgressIndicator()
        }
        homeScreenState.value.categories.isNotEmpty()->{
            LazyRow {
                items(homeScreenState.value.categories,key={it!!.categoryId}) {
CategoryEachRow(it!!)
                }
            }
            Toast.makeText(localContext,homeScreenState.value.categories.toString(), Toast.LENGTH_SHORT).show()
        }
        homeScreenState.value.error.isNotEmpty()->{
            Toast.makeText(localContext,homeScreenState.value.error, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllCategory()
    }



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) { val product = homeScreenState.value.products
        Icon(painter = painterResource(R.drawable.frock), contentDescription = null,modifier = Modifier.size(60.dp).clickable(onClick = {}))

    }

}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 23.dp, end = 23.dp, top = 73.dp)
    ) {
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
        Row(modifier = Modifier.fillMaxWidth().padding(top=19.dp),
            horizontalArrangement = SpaceBetween,
        ) {
            Text("Categories")
            Text("See more")
        }
        Row(modifier = Modifier.padding(top=19.dp)){


            }
        }
    }
