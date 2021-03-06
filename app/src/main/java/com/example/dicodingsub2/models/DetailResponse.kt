package com.example.dicodingsub2.models

data class DetailResponse(
    val avatar_url: String? = null,
    val bio: Any? = null,
    val blog: String? = null,
    var company: String? = null,
    val created_at: String? = null,
    val email: Any?= null,
    val events_url: String?= null,
    var followers: Int?= null,
    val followers_url: String?= null,
    var following: Int?= null,
    val following_url: String?= null,
    val gists_url: String?= null,
    val gravatar_id: String?= null,
    val hireable: Any?= null,
    val html_url: String?= null,
    val id: Int?= null,
    val location: String?= null,
    var login: String?= null,
    var name: String?= null,
    val node_id: String?= null,
    val organizations_url: String?= null,
    val public_gists: Int?= null,
    val public_repos: Int?= null,
    val received_events_url: String?= null,
    val repos_url: String?= null,
    val site_admin: Boolean?= null,
    val starred_url: String?= null,
    val subscriptions_url: String?= null,
    val twitter_username: Any?= null,
    val type: String?= null,
    val updated_at: String?= null,
    val url: String?= null
)