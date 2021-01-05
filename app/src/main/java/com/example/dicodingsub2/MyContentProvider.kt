package com.example.dicodingsub2

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.dicodingsub2.db.ItemDB
import com.example.dicodingsub2.db.ItemDao
import com.example.dicodingsub2.repository.GithubRepository
import com.example.dicodingsub2.util.Constants.Companion.AUTHORITY
import com.example.dicodingsub2.util.Constants.Companion.TABLE_NAME

class MyContentProvider : ContentProvider() {

    lateinit var githubRepository: GithubRepository
    lateinit var itemDao: ItemDao


    companion object {
        private const val NOTE = 1
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, NOTE)
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
    }

    override fun onCreate(): Boolean {
        itemDao = ItemDB.invoke(context as Context).getItemDao()
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return when (sUriMatcher.match(uri)) {
            NOTE -> {
                val cursor = itemDao.getItemCursor()
                cursor.setNotificationUri(context?.contentResolver, uri)
                return cursor
            }
            else -> null
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }

}
