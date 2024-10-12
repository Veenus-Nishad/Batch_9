package com.example.medicalstoreadmin.ui_layer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalstoreadmin.State
import com.example.medicalstoreadmin.data_layer.response.GetAllUserResponse
import com.example.medicalstoreadmin.data_layer.state.GetAllUsersState
import com.example.medicalstoreadmin.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _getAllUsersState= MutableStateFlow(GetAllUsersState())
    val getAllUsersState=_getAllUsersState.asStateFlow()

    init {
        getAllUser()
    }
    fun getAllUser(){
        viewModelScope.launch(Dispatchers.IO) {
        repository.getAllUsers().collect{ state->
            when(state){
                is State.Loading->{
                    _getAllUsersState.value= GetAllUsersState(Loading = true)
                }
                is State.Success->{
                    _getAllUsersState.value= GetAllUsersState(Data = state.data, Loading = false)
                }
                is State.Error->{
                    _getAllUsersState.value= GetAllUsersState(Error = state.message, Loading = false)
                }
            }

        }
        }
    }
}