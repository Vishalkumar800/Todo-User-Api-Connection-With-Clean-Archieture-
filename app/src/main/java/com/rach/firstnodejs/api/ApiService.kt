package com.rach.firstnodejs.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("/create")
    suspend fun createUser(@Body user: User):Response<User>

    @GET("/read")
    suspend fun getAllUser() :Response<List<User>>

}