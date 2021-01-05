package com.example.dicodingsub2.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dicodingsub2.models.FavoritModel

@Dao
interface FavoritDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(favorit: FavoritModel):Long
    @Query("SELECT  * FROM tb_favorite")
    fun getFavorit(): LiveData<List<FavoritModel>>
    @Delete
    suspend fun deleteIFavorit(favorit: FavoritModel)
}