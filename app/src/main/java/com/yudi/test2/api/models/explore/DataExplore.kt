package com.yudi.test2.api.models.explore


import com.fasterxml.jackson.annotation.JsonProperty

data class DataExplore(
    @field:JsonProperty("description")
    var description: String? = null,
    @field:JsonProperty("file_type")
    var fileType: String? = null,
    @field:JsonProperty("file_url")
    var fileUrl: String? = null,
    @field:JsonProperty("image_list")
    var imageList: List<String>? = null
)