package com.example.consumeapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "items"
)

@Parcelize
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id2: Int? = null,
    @ColumnInfo(name = "avatar_url")
    val avatar_url: String,
    @ColumnInfo(name = "html_url")
    val html_url: String,
    @ColumnInfo(name = "login")
    val login: String,
    @ColumnInfo(name = "url")
    val url: String

): Parcelable