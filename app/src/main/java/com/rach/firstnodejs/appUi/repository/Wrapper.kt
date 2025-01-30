package com.rach.firstnodejs.appUi.repository

sealed class ResponseUi <T>(
    val data: T? = null,
    val message:String?= null
){

    class Success<T>(data: T) : ResponseUi<T>(data)
    class Loading<T>: ResponseUi<T>()
    class Error<T>(message: String , data: T? =null) :ResponseUi<T>(data,message)

}