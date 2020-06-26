package com.yudi.test2.app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yudi.test2.api.interfaces.ApiCallBack
import com.yudi.test2.api.models.category.CategoryItem
import com.yudi.test2.api.models.category.CategoryResponse
import com.yudi.test2.api.models.explore.DataExplore
import com.yudi.test2.api.models.explore.ExploreResponse
import com.yudi.test2.api.models.users.UserItem
import com.yudi.test2.api.models.users.UserResponse
import com.yudi.test2.api.repository.ExploreRepositories
import com.yudi.test2.app.base.BaseViewModel

/**
 * @author Yudi Rahmat
 */

class ExploreViewModel(
    private val exploreRepositories: ExploreRepositories) : BaseViewModel<ExploreRepositories>(exploreRepositories) {

    private var exploredata: MutableLiveData<List<DataExplore?>?> = MutableLiveData()
    private val sampleData1Live: LiveData<List<DataExplore?>?> = exploredata

    private var userdata: MutableLiveData<List<UserItem?>?> = MutableLiveData()
    private var userDataLive: LiveData<List<UserItem?>?> = userdata

    private var categoryData: MutableLiveData<List<CategoryItem?>?> = MutableLiveData()
    private var categoryDataLive: LiveData<List<CategoryItem?>?> = categoryData
    
    fun loadData(search: String) {
        exploreRepositories.loadData(search,
            object : ApiCallBack<ExploreResponse> {
                override fun onError(error: Throwable) {
                    processError(error)
                }

                override fun onSucess(response: ExploreResponse) {
                    exploredata.value = response.data
                }
            })
    }

    fun loadUsers(search: String, page: Int) {
        exploreRepositories.loadUser(search, page,
            object : ApiCallBack<UserResponse> {
                override fun onError(error: Throwable) {
                    processError(error)
                }

                override fun onSucess(response: UserResponse) {
                    userdata.value = response.items
                }
            })
    }

    fun loadCategory(url: String) {
        exploreRepositories.loadCategory(url,
            object : ApiCallBack<CategoryResponse> {
                override fun onError(error: Throwable) {
                    processError(error)
                }

                override fun onSucess(response: CategoryResponse) {
                    categoryData.value = response.data
                }
            })
    }

    fun getExploreList(): LiveData<List<DataExplore?>?> {
        return sampleData1Live
    }

    fun getUsers(): LiveData<List<UserItem?>?> {
        return userDataLive
    }

    fun getCategory(): LiveData<List<CategoryItem?>?> {
        return categoryDataLive
    }
}