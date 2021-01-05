package com.example.dicodingsub2.api


import com.example.dicodingsub2.models.GithubResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubAPI {
    @GET("search/users")
    suspend fun searchForNews(
        @Query("q")
        searchQuery:String
    ): Response<GithubResponse>

}