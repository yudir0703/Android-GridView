package com.yudi.test2.api.models.users


import com.fasterxml.jackson.annotation.JsonProperty

data class UserItem(
    @field:JsonProperty("avatar_url")
    var avatarUrl: String? = null,
    @field:JsonProperty("events_url")
    var eventsUrl: String? = null,
    @field:JsonProperty("followers_url")
    var followersUrl: String? = null,
    @field:JsonProperty("following_url")
    var followingUrl: String? = null,
    @field:JsonProperty("gists_url")
    var gistsUrl: String? = null,
    @field:JsonProperty("gravatar_id")
    var gravatarId: String? = null,
    @field:JsonProperty("html_url")
    var htmlUrl: String? = null,
    @field:JsonProperty("id")
    var id: Int? = null,
    @field:JsonProperty("login")
    var login: String? = null,
    @field:JsonProperty("node_id")
    var nodeId: String? = null,
    @field:JsonProperty("organizations_url")
    var organizationsUrl: String? = null,
    @field:JsonProperty("received_events_url")
    var receivedEventsUrl: String? = null,
    @field:JsonProperty("repos_url")
    var reposUrl: String? = null,
    @field:JsonProperty("score")
    var score: Double? = null,
    @field:JsonProperty("site_admin")
    var siteAdmin: Boolean? = null,
    @field:JsonProperty("starred_url")
    var starredUrl: String? = null,
    @field:JsonProperty("subscriptions_url")
    var subscriptionsUrl: String? = null,
    @field:JsonProperty("type")
    var type: String? = null,
    @field:JsonProperty("url")
    var url: String? = null
)