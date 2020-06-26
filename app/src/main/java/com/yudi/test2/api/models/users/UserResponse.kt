package com.yudi.test2.api.models.users


import com.fasterxml.jackson.annotation.JsonProperty

data class UserResponse(
    @field:JsonProperty("incomplete_results")
    var incompleteResults: Boolean? = null,
    @field:JsonProperty("items")
    var items: List<UserItem>? = null,
    @field:JsonProperty("total_count")
    var totalCount: Int? = null
)