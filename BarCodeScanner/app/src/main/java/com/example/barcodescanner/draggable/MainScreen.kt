package com.example.barcodescanner.draggable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

@Composable
fun MainScreen(
    modifier: Modifier=Modifier,
    content:@Composable BoxScope.()->Unit
){
    val state = remember {
        DraggableItemInfo()
    }

    CompositionLocalProvider(LocalDraggableItemInfo provides state){
        Box(modifier = Modifier.fillMaxSize()){
            content()

            if(state.isDragging){
                var targetSize by remember {
                    mutableStateOf(IntSize.Zero)
                }

                Box(modifier = Modifier.graphicsLayer{
                    val offset=(state.dragStartOffset+state.dragCurrentOffset)
                    scaleX=1.3f
                    scaleY=1.3f
                    translationX=offset.x.minus(targetSize.width/2)
                    translationY=offset.y.minus(targetSize.width)
                }.onGloballyPositioned {
                    targetSize=it.size
                }){
                    state.draggableComposable?.invoke()
                }
            }


        }
    }
}