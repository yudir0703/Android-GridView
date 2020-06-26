package com.yudi.test2.api.models.category

import com.fasterxml.jackson.annotation.JsonProperty

data class CategoryResponse(
    @field:JsonProperty("status")
    var status: Int? = null,
    @field:JsonProperty("message")
    var message: String? = null,
    @field:JsonProperty("data")
    var data: List<CategoryItem>? = null
)