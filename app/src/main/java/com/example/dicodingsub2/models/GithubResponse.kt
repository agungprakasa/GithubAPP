package com.example.dicodingsub2.models

data class GithubResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)