package com.yudi.test2.service.api

import android.content.Context
import com.yudi.test2.api.models.category.CategoryResponse
import com.yudi.test2.api.models.explore.ExploreResponse
import com.yudi.test2.api.models.users.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * @author Yudi Rahmat
 */

interface APIInterface {

    companion object {
        operator fun invoke(baseUrl: String, mContext: Context): APIInterface {
            val baseService =
                APIService(
                    baseUrl,
                    mContext
                )

            return baseService.retrofit.create(APIInterface::class.java)
        }
    }

    @GET("explore")
    fun loadData(@Query("search") search: String): Observable<ExploreResponse>

    @GET("search/users")
    fun loadUsers(@Query("q") search: String, @Query("page") page: Int): Observable<UserResponse>

    @GET
    fun loadCategory(@Url url: String): Observable<CategoryResponse>
}

