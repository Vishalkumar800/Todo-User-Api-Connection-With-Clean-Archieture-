package com.rach.firstnodejs.models

import com.rach.firstnodejs.api.User

data class HomeUiState(
    val users:List<User> = emptyList(),
    val isLoading:Boolean = false,
    val enteredName:String = "",
    val enteredEmail:String = "",
    val enterRole:String = "",
    var errorMessage:String? = null
)
