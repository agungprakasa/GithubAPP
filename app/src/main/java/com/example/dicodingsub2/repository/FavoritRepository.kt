package com.example.dicodingsub2.repository

import android.content.Context
import android.os.AsyncTask
import com.example.dicodingsub2.db.ItemDB
import com.example.dicodingsub2.db.ItemDao
import com.example.dicodingsub2.models.Item
import java.util.concurrent.ExecutionException

//
//class FavoritRepository (private val context: Context) {
//        private lateinit var itemDao: ItemDao
//
//
//        private fun getDb(): ItemDB {
//        return ItemDB.invoke(context)
//    }
//
//    fun favorite(status: Int, item: Item?): Int {
//        return try {
//            if (item != null) {
//                itemDao = getDb().getItemDao()
//                val backgroundThread = BackgroundThread(status, itemDao)
//                backgroundThread.execute(item)
//                backgroundThread.get()
//            } else {
//                0
//            }
//        } catch (e: ExecutionException) {
//            e.printStackTrace()
//            -1
//        } catch (e: InterruptedException) {
//            e.printStackTrace()
//            -1
//        }
//    }
//
//    private class BackgroundThread(
//        private val status: Int,
//        private val itemDao: ItemDao
//    ) : AsyncTask<Item, Void, Int>() {
//
//        override fun doInBackground(vararg params: Item): Int {
//            return when (status) {
//                INSERT -> {
//                    itemDao.upsert()
//                }
//                CHECKFAVORITE -> {
//                    itemDao.getItemByUsername(params[0].username)
//                }
//                DELETE -> {
//                    itemDao.deleteItem(params[0].username)
//                }
//                else -> 0
//            }
//        }
//    }
//
//    fun getFavorite(status: Int, item: Item?): List<Item>? {
//        try {
//            itemDao = getDb().getItemDao()
//            val threadListKeranjang =
//                ThreadListFavorite(status, itemDao)
//            threadListKeranjang.execute(item)
//            return threadListKeranjang.get()
//        } catch (e: ExecutionException) {
//            e.printStackTrace()
//        } catch (e: InterruptedException) {
//            e.printStackTrace()
//        }
//        return null
//    }
//
//    private class ThreadListFavorite internal constructor(
//        private val status: Int,
//        private val itemDao: ItemDao
//    ) :
//        AsyncTask<Item?, Void?, List<Item>>() {
//        override fun doInBackground(vararg params: Item?): List<Item>? {
//            return when(status){
//                GETALL -> {
//                    itemDao.getItemByUsername()
//                }
//            }
//        }
//    }
//
//}