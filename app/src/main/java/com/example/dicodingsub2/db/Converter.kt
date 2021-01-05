package com.example.dicodingsub2.db

import androidx.room.TypeConverter
import com.example.dicodingsub2.models.Source

class Converter {
    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}