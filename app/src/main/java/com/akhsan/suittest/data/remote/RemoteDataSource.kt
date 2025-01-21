package com.akhsan.suittest.data.remote

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun fetchListUsers() = UserPagingSource(
        apiService = apiService,
        perPage = 10
    )
}