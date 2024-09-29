package com.example.camerax

import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun CameraScreen(modifier: Modifier = Modifier) {
    // Camera open hone ke baad jo ui dikhega
    val context = LocalContext.current

    val lensFacing = CameraSelector.LENS_FACING_BACK // konsa lens use krna hai

    //maintain Camera Lifecycle
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build() // camera se aari hai
    val previewView = remember { // apne app mein jab camera Khule natively uske liye
        PreviewView(context)
    }
    val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build() //imp hai
    val imageCapture = remember {  // jab photo ko capture karoge uska object hai
        ImageCapture.Builder().build()
    }
    LaunchedEffect(key1 = lensFacing) { // ye compose jab render hoga toh sabse
        // pehle iske andar ka run hoga
        val cameraProvider = context.getCameraProvider()

        // capture karne ke liye responsible ho
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageCapture)

        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

    //preparing a ui sort of thing
    Box(modifier = Modifier.fillMaxSize().padding(bottom = 60.dp), contentAlignment = Alignment.BottomCenter) {
        //Handle UI elements here
        AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize())
        Button(onClick = { captureImage(imageCapture,context) }) {
            Text(text = "Capture")
        }
    }

}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine {
    val listenableFuture = ProcessCameraProvider.getInstance(this).also { cameraProvider ->
        cameraProvider.addListener({
            it.resume(cameraProvider.get())
        }, ContextCompat.getMainExecutor(this))
    }
}

private fun captureImage(imageCapture: ImageCapture, context: Context) {
    val name = "CameraX ${System.currentTimeMillis()}.jpg" // image name generator

    val contentValue = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name) // name
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg") //file type
        put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image") // Location
    }
    val outPutOption = ImageCapture.OutputFileOptions.Builder(
        context.contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValue
    ).build()

    imageCapture.takePicture(
        outPutOption,
        ContextCompat.getMainExecutor(context),
        object:ImageCapture.OnImageSavedCallback{
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
              Toast.makeText(context,"Image Saved",Toast.LENGTH_SHORT).show()
            }

            override fun onError(exception: ImageCaptureException) {
                Toast.makeText(context,"Image Not Saved",Toast.LENGTH_SHORT).show()
            }
        }


    )
}
