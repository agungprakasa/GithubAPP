package com.example.dicodingsub2.models

import java.io.Serializable

data class FollowerItem(
    var avatar_url: String,
    var id: Int,
    var login: String,
    var url: String
): Serializable