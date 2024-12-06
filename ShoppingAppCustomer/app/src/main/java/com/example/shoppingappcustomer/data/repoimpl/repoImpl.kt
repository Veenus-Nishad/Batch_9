package com.example.shoppingappcustomer.data.repoimpl

import com.example.shoppingappcustomer.common.CATEGORY
import com.example.shoppingappcustomer.common.ResultState
import com.example.shoppingappcustomer.domain.models.Category
import com.example.shoppingappcustomer.domain.repo.repo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class repoImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : repo {
    override fun getAllCategory(): Flow<ResultState<List<Category>>> = callbackFlow {
        trySend(ResultState.Loading)
        // .limit can be used to set the limit of get
        firebaseFirestore.collection(CATEGORY).get()
            .addOnSuccessListener {
//category collection ke andar jitna bhi documents hai unko lane ke liye
                val categoryData = it.documents.mapNotNull {
                    it.toObject(Category::class.java)
                }

                //humne admin ki taraf se puri category class bheji hai db ko toh hum bhi puri class le rahe hai
                //instead of class se ek ek karke uthana
                trySend(ResultState.Success(categoryData))
            }
            .addOnFailureListener {
                trySend(ResultState.Error(it.toString()))
            }
        awaitClose {
            close()
        }
    }
}