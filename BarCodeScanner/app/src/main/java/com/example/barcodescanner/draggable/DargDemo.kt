package com.example.barcodescanner.draggable

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned

//modifier.onGloballyPositioned {} where the view exists currently in the window
/*LocalDraggableItemInfo.current to get access to an instance of DraggableItemInfo,
 which keeps track of whether the view is currently being dragged and its position during the drag.*/
internal val LocalDraggableItemInfo = compositionLocalOf { DraggableItemInfo() }

@Composable
fun DraggableView(
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit)
) {
    var currentPosition by remember {
        mutableStateOf(Offset.Zero)
    }

    val currentState = LocalDraggableItemInfo.current

    Box(modifier = modifier
        .onGloballyPositioned { layoutCoordinates ->
            currentPosition = layoutCoordinates.localToWindow(Offset.Zero)
        }
        .pointerInput(Unit) {
            detectDragGestures(
                onDragStart = { startOffset ->
                    currentState.draggableComposable = content
                    currentState.isDragging = true
                    currentState.dragStartOffset = startOffset + currentPosition
                },
                onDrag = { change, dragAmount ->
                    change.consume()
                    currentState.dragCurrentOffset += Offset(dragAmount.x, dragAmount.y)
                },
                onDragEnd = {
                    currentState.isDragging = false
                    currentState.dragCurrentOffset = Offset.Zero
                },
                onDragCancel = {
                    currentState.isDragging = false
                    currentState.dragCurrentOffset = Offset.Zero
                }
            )
        }) {
        content()
    }

}

internal class DraggableItemInfo {
    var isDragging by mutableStateOf(false)
    var dragStartOffset by mutableStateOf(Offset.Zero)
    var dragCurrentOffset by mutableStateOf(Offset.Zero) // curr position
    var draggableComposable by mutableStateOf<(@Composable () -> Unit)?>(null) // composable being dragged

}

