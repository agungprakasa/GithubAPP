package com.example.dicodingsub2.db

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dicodingsub2.models.Item

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: Item):Long
    @Query("SELECT  * FROM items")
    fun getItem(): LiveData<List<Item>>
    @Query("SELECT id2,avatar_url,login,html_url,url FROM items ORDER BY login ASC")
    fun getItemFavorit(): LiveData<List<Item>>
    @Query("SELECT * FROM items where login = :username ")
    fun getItemByUsername(username: String): LiveData<Item>
    @Delete
    suspend fun deleteItem(item: Item)
    @Query("SELECT  * FROM items")
    fun getItemCursor(): Cursor
}