package com.example.practice.SwipableItem

import androidx.compose.animation.Animatable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun SwipeAbleItemWithActions(
    isRevealed:Boolean,
    actions:@Composable RowScope.()->Unit,
    modifier: Modifier=Modifier,
    onExpanded:()->Unit={},
    onCollapsed:()->Unit={},
    content : @Composable ()->Unit
){
    var lowerLayerWidth by remember { // Width of hidden behind the upper layer
        mutableFloatStateOf(0f)
    }

    val offset = remember { // to remember how far did we offset or drag the upper layer
        androidx.compose.animation.core.Animatable(initialValue = 0f)
    }

    val scope = rememberCoroutineScope()
}