package com.akhsan.suittest.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.akhsan.suittest.data.remote.ListUsersResponse.Data
import kotlinx.coroutines.delay

class UserPagingSource(
    private val apiService: ApiService,
    private val perPage: Int
) : PagingSource<Int, Data>() {

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val currentPage = params.key ?: 1

            val apiResponse = apiService.fetchListUsers(perPage = perPage, page = currentPage)
            delay(
                if (currentPage == 1) {
                    0
                } else {
                    500
                }
            )
            val previousPage =
                if (currentPage == 1) {
                    null
                } else {
                    currentPage - 1
                }
            val nextPage =
                if (currentPage != apiResponse.totalPages) {
                    currentPage + 1
                } else {
                    null
                }
            LoadResult.Page(
                data = apiResponse.data,
                prevKey = previousPage,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}