package com.akhsan.suittest.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.akhsan.suittest.data.local.LocalDataSource
import com.akhsan.suittest.data.remote.ListUsersResponse.Data
import com.akhsan.suittest.data.remote.RemoteDataSource
import com.akhsan.suittest.domain.UseCase
import com.akhsan.suittest.domain.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : UseCase {

    override fun getName(): Flow<String?> = localDataSource.getName()

    override suspend fun saveName(name: String) {
        localDataSource.saveName(name)
    }

    override fun getListUsers(): Flow<PagingData<UserModel>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            remoteDataSource.fetchListUsers()
        }
    ).flow.map { pagingData ->
        pagingData.map(Data::asUserModel)
    }

}