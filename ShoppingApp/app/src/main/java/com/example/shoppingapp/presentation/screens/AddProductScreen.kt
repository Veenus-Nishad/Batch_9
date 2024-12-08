package com.example.shoppingapp.presentation.screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.shoppingapp.domain.models.ProductModels

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    viewModel: myViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.getCategory()
    }

    val addProductState = viewModel.addProducts.collectAsState()
    val getCategoryState = viewModel.getCategory.collectAsState()
    val productImage = viewModel.uploadProductImage.collectAsState()


    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var category by remember { mutableStateOf<Array<String>?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageUrl by remember { mutableStateOf("") }
    var availableUnits by remember { mutableStateOf(0) }
    var expanded by remember { mutableStateOf("") }
    var createdBy by remember { mutableStateOf("") }
    var finalPrice by remember { mutableStateOf("") }

    var isExposedDropDownExpanded by remember {
        mutableStateOf(false)
    }
    val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }

    //getting image through scope storage
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                viewModel.uploadProductImage(uri)
                imageUri = uri
            }
        }
    // image jaha pe store hai storage mein  usko kehte hai uri

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (imageUri != null) {
            AsyncImage(
                model = imageUri,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            launcher.launch(
                                PickVisualMediaRequest(
                                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { }
                    )
                    Text(text = "Add Image")
                    Text(text = "Add Products")
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text(text = "Name") })
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = price,
                            onValueChange = { price = it },
                            label = { Text(text = "Price") })
                        OutlinedTextField(
                            value = finalPrice,
                            onValueChange = { finalPrice = it },
                            label = { Text(text = "Final Price") })
                    }
                    ExposedDropdownMenuBox(
                        expanded = isExposedDropDownExpanded,
                        onExpandedChange = { isExposedDropDownExpanded != isExposedDropDownExpanded }
                    ) {
                        OutlinedTextField(
                            value = selectedText,
                            onValueChange = { selectedText = it },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExposedDropDownExpanded) },
                            modifier = Modifier.menuAnchor()
                        )
                        ExposedDropdownMenu(
                            expanded = isExposedDropDownExpanded,
                            onDismissRequest = { isExposedDropDownExpanded = false }
                        ) {
                            category!!.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        selectedText = item
                                        isExposedDropDownExpanded = false
                                    })
                            }
                        }
                    }

                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text(text = "Description") }
                    )
                    OutlinedTextField(
                        value = availableUnits.toString(),
                        onValueChange = { availableUnits = it.toInt() },
                        label = { Text(text = "Available Units") }
                    )
                    OutlinedTextField(
                        value = createdBy,
                        onValueChange = { createdBy = it },
                        label = { Text(text = "Created By") }
                    )
                    Button(
                        onClick = {
                            val data = ProductModels(
                                name = name,
                                price = price,
                                finalprice = finalPrice,
                                availableUnits = availableUnits,
                                createdBy = createdBy,
                                description = description,
                                category = category.toString(),
                                image=imageUrl
                            )
                            viewModel.addProduct(
                                data
                            )
                        },modifier=Modifier.fillMaxWidth()

                    ){
                        Text("Add Product")
                    }

                }
            }

        }
    }
}
