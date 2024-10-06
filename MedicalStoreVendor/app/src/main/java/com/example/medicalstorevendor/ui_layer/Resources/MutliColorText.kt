package com.example.medicalstorevendor.ui_layer.Resources

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun MultiColorText(firstString: String,secondString: String,modifier: Modifier = Modifier){
   val annotatedString = buildAnnotatedString {
       withStyle(style = SpanStyle(color = Color.Black)) {
           append(firstString)
       }
       withStyle(style = SpanStyle(color = Color.Blue)) {
           append(secondString)

       }
   }
    Text(text = annotatedString,modifier=modifier)
}