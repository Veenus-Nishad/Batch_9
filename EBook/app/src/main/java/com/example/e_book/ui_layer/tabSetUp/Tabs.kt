package com.example.e_book.ui_layer.tabSetUp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.util.fastForEachIndexed


@Composable
fun Tabs(pagerState: MutableState<Int>) {
    val  list = listOf(
        "Category" to Icons.Default.Category,
        "All Books" to Icons.Default.Book
    )
    TabRow(selectedTabIndex = pagerState.value) {
       list.fastForEachIndexed{i,pair->
           Tab(
               selected = pagerState.value==i,
               onClick = {
                   pagerState.value=i
               },
               icon={
                   Icon(imageVector = pair.second, contentDescription = null)
               }, text = {
                   Text(text = pair.first)
               }
           )

       }
    }
}