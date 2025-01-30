package com.rach.firstnodejs.models

sealed class HomeUiEvent {

    data class EnterUserNameChanged(val value:String): HomeUiEvent()
    data class EnterRoleChanged(val value: String): HomeUiEvent()
    data class EnterEmailChanged(val value: String) : HomeUiEvent()

}