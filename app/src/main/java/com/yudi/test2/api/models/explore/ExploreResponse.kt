package com.yudi.test2.api.models.explore


import com.fasterxml.jackson.annotation.JsonProperty

data class ExploreResponse(
    @field:JsonProperty("data")
    var data: List<DataExplore?>? = null,
    @field:JsonProperty("message")
    var message: String? = null,
    @field:JsonProperty("status")
    var status: Int? = null
)