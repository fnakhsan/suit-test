package com.akhsan.suittest.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getName(): Flow<String?>

    suspend fun saveName(name: String)

    fun getListUsers(): Flow<PagingData<UserModel>>
}