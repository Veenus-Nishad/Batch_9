package com.example.contactsappwithroomdatabaseversion1.notes

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun Launch(){
GlobalScope.launch(Dispatchers.IO){
}
}
/*
 launch has a dispatcher parameter which can have 4 specialized threads
    1. Dispatchers.IO -> for input output read fetch task
    1. Dispatchers.Main -> ui related / for tasks to be done on Main thread also default values
    1. Dispatchers.Unconfined -> for task whose category is not known
    1. Dispatchers.Default -> for Mathematical or cpu oriented task
*/

/* TYPES OF SCOPE :
1.GlobalScope -> is one of the worst scope which keeps the Coruotine Scope active till the app closes
    i.e it doesnt release the memory captured or alloted or allocated resource till the app is closed
2.lifecycleScope -> works in parallel to activity life cycle. i.e Oncreate scope start and Onpause resource release
3.viewModelScope -> sirf viewModel ke liye exist karta hai*/