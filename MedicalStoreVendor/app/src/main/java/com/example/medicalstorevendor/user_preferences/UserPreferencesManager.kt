package com.example.medicalstorevendor.user_preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_preferences")
class UserPreferencesManager(private val context:Context) {
    companion object{   // like static object in JAVA allows you to use the values inside this class without making objects
        private val USER_ID_KEY= stringPreferencesKey("user_id")
                //This defines a key (USER_ID_KEY) used to store and retrieve the user_id in DataStore.
    }


    val getUserId: Flow<String?> = context.dataStore.data.map{
        it[USER_ID_KEY] // for reading the user id and retrieving
    }

    suspend fun saveUserId(userId:String){ // first this will run to store
        context.dataStore.edit{
            it[USER_ID_KEY]=userId
        }
    }

    suspend fun clearUserId(){ // call on logout button
        context.dataStore.edit{
            it.remove(USER_ID_KEY)
        }

    }


}