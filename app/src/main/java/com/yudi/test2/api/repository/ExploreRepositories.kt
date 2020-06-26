package com.yudi.test2.api.repository

import com.yudi.test2.api.base.BaseRepository
import com.yudi.test2.api.interfaces.ApiCallBack
import com.yudi.test2.api.models.category.CategoryResponse
import com.yudi.test2.api.models.explore.ExploreResponse
import com.yudi.test2.api.models.users.UserResponse
import com.yudi.test2.service.api.APIInterface
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Yudi Rahmat
 */

class ExploreRepositories(service: APIInterface, compositeDisposable: CompositeDisposable) : BaseRepository(service, compositeDisposable) {

    fun loadData(search: String, callback: ApiCallBack<ExploreResponse>) {
        fetchData(service.loadData(search), callback)
    }

    fun loadUser(search: String, page: Int, callback: ApiCallBack<UserResponse>) {
        fetchData(service.loadUsers(search, page), callback)
    }

    fun loadCategory(url: String, callback: ApiCallBack<CategoryResponse>) {
        fetchData(service.loadCategory(url), callback)
    }

}

