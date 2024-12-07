package com.example.shoppingapp.data.repoimpl

import android.net.Uri
import com.example.shoppingapp.common.CATEGORY
import com.example.shoppingapp.common.PRODUCT
import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.ProductModels
import com.example.shoppingapp.domain.models.category
import com.example.shoppingapp.domain.repo.repo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class repoImpl @Inject constructor(
    private val firebaseFireStore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage
) : repo {
    //function to add collection( the hierarchy used in this ) in firebaseFireStore
    override suspend fun addCategory(category: category): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        //populating the collection named CATEGORY
        firebaseFireStore.collection(CATEGORY).add(category).addOnSuccessListener {
            trySend(ResultState.Success("Category Added Successfully"))
        }.addOnFailureListener {
            trySend(ResultState.Error(it.toString()))
        }
        awaitClose {
            //to clean up the callback or resource when the flow is cancelled or completed
            close()
        }
    }

    // flow ke bahar jab data bhejte hai tab callbackFLow use karte hai
    override suspend fun getCategories(): Flow<ResultState<List<category>>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseFireStore.collection(CATEGORY).get()
            .addOnSuccessListener { querySnapshot ->
                val categories = querySnapshot.documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject(category::class.java)
                }
                trySend(ResultState.Success(categories))
            }
            .addOnFailureListener { exception ->
                trySend(ResultState.Error(exception.toString()))
            }
        awaitClose {
            close()
        }
    }

    override suspend fun addProduct(productsModels: ProductModels): Flow<ResultState<String>> =
        callbackFlow {
            trySend(ResultState.Loading)
            firebaseFireStore.collection(PRODUCT).add(productsModels).addOnSuccessListener {
                trySend(ResultState.Success("Products Added Successfully"))

            }.addOnFailureListener { exception ->
                trySend(ResultState.Error(exception.localizedMessage!!))
            }
            awaitClose {
                close()
            }

        }

    override suspend fun addImage(image: Uri): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseStorage.reference.child("PRODUCT/${System.currentTimeMillis()}")
            .putFile(image)
            .addOnSuccessListener { // for file upload the image file is successfully stored in Firebase Storage.
                it.storage.downloadUrl.addOnSuccessListener { // for retrival of the image This listener doesn't "download" or "display"
                    // the image itself. It simply ensures that the download URL is successfully retrieved and then can be used
                    // i.e is jo bhi url server mein hai uska url dega
                    trySend(ResultState.Success(it.toString()))
                }.addOnFailureListener {
                    trySend(ResultState.Error(it.toString()))
                }
            }.addOnFailureListener {
                trySend(ResultState.Error(it.toString()))
            }
        awaitClose {
            close()
        }
    }


}