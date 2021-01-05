package com.example.dicodingsub2.repository

import com.example.dicodingsub2.api.RetrofitInstance
import com.example.dicodingsub2.db.ItemDB
import com.example.dicodingsub2.models.Item

class GithubRepository(
    val db: ItemDB
){
    suspend fun searchUsers(searchQuery: String) =
        RetrofitInstance.api.searchForNews(searchQuery)

    suspend fun upsert(item: Item) = db.getItemDao().upsert(item)

    fun getSavedUser() = db.getItemDao().getItemFavorit()

    suspend fun deleteUser(item: Item) = db.getItemDao().deleteItem(item)

    fun getItemByUsername(username: String) = db.getItemDao().getItemByUsername(username)

    fun getItemCursor(username: String) = db.getItemDao().getItemCursor()

}