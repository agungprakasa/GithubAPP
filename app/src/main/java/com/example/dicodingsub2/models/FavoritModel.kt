package com.example.dicodingsub2.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "tb_favorite")
data class FavoritModel(
    @PrimaryKey
    @ColumnInfo(name = "username")
    @NotNull
    var username: String = "",

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,

    @ColumnInfo(name = "type")
    var type: String = "",

    @ColumnInfo(name = "image")
    var image: String = ""
)
