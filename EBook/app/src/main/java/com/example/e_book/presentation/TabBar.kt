package com.example.e_book.presentation

import android.media.Image
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabBar(navController: NavController){
    val tabs = listOf(
        tabItem("Category", Icons.Default.Category),
        tabItem("Books", Icons.Default.Book),
    )

    val pageState=rememberPagerState(pageCount={tabs.size})

    val coroutineScope=rememberCoroutineScope()

    Column(modifier= Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = pageState.currentPage,
            modifier=Modifier.fillMaxWidth()) {
            tabs.forEachIndexed{index,tabItem->
                Tab(
                    selected = pageState.currentPage==index,
                    modifier=Modifier.fillMaxWidth(),
                    onClick={
                        coroutineScope.launch {
                            pageState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = tabItem.name)
                    },
                    icon = {
                        Icon(imageVector = tabItem.icon, contentDescription = null)
                    }
                    )
            }
        }

        HorizontalPager(state=pageState) {
            when(it){
                0 -> BookCategory(navController = navController)
                1 -> AllBooksScreen(navController = navController)
            }
        }

    }
}

data class tabItem(
    val name:String,
    val icon: ImageVector
)