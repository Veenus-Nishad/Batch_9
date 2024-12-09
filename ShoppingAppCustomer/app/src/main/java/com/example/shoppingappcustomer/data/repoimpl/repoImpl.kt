package com.example.shoppingappcustomer.data.repoimpl

import com.example.shoppingappcustomer.common.CATEGORY
import com.example.shoppingappcustomer.common.PRODUCT
import com.example.shoppingappcustomer.common.ResultState
import com.example.shoppingappcustomer.common.USERS
import com.example.shoppingappcustomer.data.di.DataModel_ProvideFirebaseStorageFactory
import com.example.shoppingappcustomer.domain.models.Category
import com.example.shoppingappcustomer.domain.models.ProductModel
import com.example.shoppingappcustomer.domain.models.UserData
import com.example.shoppingappcustomer.domain.repo.repo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.firestore.toObjects
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class repoImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val firebaseStorage: FirebaseStorage
) : repo {
    override fun registerUserWithEmailAndPassword(userData: UserData): Flow<ResultState<String>> =
        callbackFlow {
            trySend(ResultState.Loading)
            firebaseAuth.createUserWithEmailAndPassword(userData.email, userData.password)
                .addOnSuccessListener {
                    userData.uuid = it.user?.uid.toString()
                    firebaseFirestore.collection(USERS).document(it.user?.uid.toString())
                        .set(userData).addOnSuccessListener {
                            trySend(ResultState.Success("User Registered Successfully"))
                        }.addOnFailureListener {
                            trySend(ResultState.Error(it.message.toString()))
                        }

                }
                .addOnFailureListener {
                    trySend(ResultState.Error(it.message.toString()))
                }
            awaitClose {
                close()
            }
        }

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

    override fun getCategoryInLimit(): Flow<ResultState<List<Category>>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseFirestore.collection(CATEGORY).limit(5).get()
            .addOnSuccessListener {
                val categoryData = it.documents.mapNotNull {
                    it.toObject(Category::class.java)
                }
                trySend(ResultState.Success(categoryData))
            }
            .addOnFailureListener {
                trySend(ResultState.Error(it.toString()))
            }
        awaitClose {
            close()
        }
    }

    override fun getAllProducts(): Flow<ResultState<List<ProductModel>>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseFirestore.collection(PRODUCT).get()
            .addOnSuccessListener {
                val productData = it.documents.mapNotNull {
                    it.toObject(ProductModel::class.java)
                }
                trySend(ResultState.Success(productData))
            }
            .addOnFailureListener {
                trySend(ResultState.Error(it.toString()))
            }
        awaitClose {
            close()
        }
    }

    override fun getProductsInLimit(): Flow<ResultState<List<ProductModel>>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseFirestore.collection(PRODUCT).limit(5).get()
            .addOnSuccessListener {
                val productData = it.documents.mapNotNull {
                    it.toObject(ProductModel::class.java)
                }
                trySend(ResultState.Success(productData))
            }
            .addOnFailureListener {
                trySend(ResultState.Error(it.toString()))
            }
        awaitClose {
            close()
        }
    }

}