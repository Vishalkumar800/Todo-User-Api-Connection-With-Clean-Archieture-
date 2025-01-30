package com.rach.firstnodejs.api

import retrofit2.Response
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : UserDataSource {
    override suspend fun createUser(user: User): Response<User> {
        return try {
            apiService.createUser(user)
        } catch (e: Exception) {
            throw Exception("Error in creating User ${e.localizedMessage}")
        }
    }

    override suspend fun getAllUser(): Response<List<User>> {
        return try {
            apiService.getAllUser()
        } catch (e: Exception) {
            throw Exception("Error in Getting User ${e.localizedMessage}")
        }
    }
}