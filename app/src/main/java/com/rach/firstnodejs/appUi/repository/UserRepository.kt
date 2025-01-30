package com.rach.firstnodejs.appUi.repository

import com.rach.firstnodejs.api.User
import com.rach.firstnodejs.api.UserDataSource
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDataSource: UserDataSource
) {

    suspend fun getAllUsers(): ResponseUi<List<User>>{
        return handleResponse {
            userDataSource.getAllUser()
        }
    }

    suspend fun createUser(user: User): ResponseUi<User>{
        return handleResponse {
            userDataSource.createUser(user)
        }
    }


    private inline fun <T> handleResponse(call: () -> Response<T>): ResponseUi<T>{
        return try {

            val response = call()

            if (response.isSuccessful && response.body() != null){
                ResponseUi.Success(response.body()!!)
            }else{
                ResponseUi.Error("Error: ${response.message()}")
            }

        }catch (e:Exception){
            ResponseUi.Error("Exception ${e.localizedMessage}")
        }
    }

}