package com.example.dicodingsub2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dicodingsub2.models.FavoritModel

@Database(
    entities = [FavoritModel::class],
    version = 1,
    exportSchema = true
)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoritDao(): FavoritDao
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var favoriteDatabase: FavoriteDatabase? = null

        fun getDatabase(context: Context): FavoriteDatabase {
            val tempInstance = favoriteDatabase
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    FavoriteDatabase::class.java,
                    "fav_database"
                ).build()
                favoriteDatabase = instance
                return instance
            }
        }
    }
}