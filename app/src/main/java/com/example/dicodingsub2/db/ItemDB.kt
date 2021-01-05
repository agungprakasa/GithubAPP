package com.example.dicodingsub2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dicodingsub2.models.Item

@Database(
    entities = [Item::class],
    version = 1
)
abstract class ItemDB:RoomDatabase() {
    abstract fun getItemDao(): ItemDao
    companion object {
        @Volatile
        private var instance: ItemDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance= it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ItemDB::class.java,
                "item_db.db"
            ).build()
    }
}