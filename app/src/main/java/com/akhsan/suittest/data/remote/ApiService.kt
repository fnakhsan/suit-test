package com.akhsan.suittest.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun fetchListUsers(
        @Query("page") page: Int? = null,
        @Query("per_page") perPage: Int? = 10,
    ): ListUsersResponse
}