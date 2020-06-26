package com.yudi.test2.api.models.category


import com.fasterxml.jackson.annotation.JsonProperty

data class CategoryItem(
    @field:JsonProperty("id")
    var id: String? = null,
    @field:JsonProperty("name")
    var name: String? = null
)