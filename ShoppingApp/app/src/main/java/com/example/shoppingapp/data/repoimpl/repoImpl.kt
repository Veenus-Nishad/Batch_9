package com.example.shoppingapp.data.repoimpl

import com.example.shoppingapp.common.CATEGORY
import com.example.shoppingapp.common.ResultState
import com.example.shoppingapp.domain.models.category
import com.example.shoppingapp.domain.repo.repo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class repoImpl @Inject constructor(
    private val firebaseFireStore:FirebaseFirestore
) : repo{
    //function to add collection( the hierarchy used in this ) in firebaseFireStore
    override fun addCategory(category: category): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        //populating the collection named CATEGORY
        firebaseFireStore.collection(CATEGORY).add(category).addOnSuccessListener {
            trySend(ResultState.Success("Category Added Successfully"))
        }.addOnFailureListener {
            trySend(ResultState.Error(it.toString()))
        }
        awaitClose{
            //to clean up the callback or resource when the flow is cancelled or completed
            close()
        }
    }

}