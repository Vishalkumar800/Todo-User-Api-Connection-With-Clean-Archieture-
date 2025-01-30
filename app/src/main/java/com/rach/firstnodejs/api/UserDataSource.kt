package com.rach.firstnodejs.api

import retrofit2.Response

interface UserDataSource {

    suspend fun createUser(user: User): Response<User>
    suspend fun getAllUser(): Response<List<User>>

}