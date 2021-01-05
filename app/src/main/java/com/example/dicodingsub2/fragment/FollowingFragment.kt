package com.example.dicodingsub2.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingsub2.R
import com.example.dicodingsub2.adapter.FollowerAdapter
import com.example.dicodingsub2.adapter.FollowingAdapter
import com.example.dicodingsub2.models.FollowerItem
import com.example.dicodingsub2.models.followingItem
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.fragment_follower.*
import kotlinx.android.synthetic.main.fragment_following.*
import org.json.JSONArray


class FollowingFragment : Fragment() {
    companion object {
        private const val ARG_USERNAME = "username"
        @JvmStatic
        fun getInstance(index: String) =
            FollowingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USERNAME, index)
                }
            }
    }

    lateinit var myAdapter: FollowingAdapter
    private val mData = ArrayList<followingItem>()
    var TAG = "FollowingFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        koneksi()

    }

    fun koneksi() {
        val username = arguments?.getString(FollowingFragment.ARG_USERNAME)
        Log.d(TAG," TEST USERNAME $username")
        val url = "https://api.github.com/users/${username}/following"
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token ec236f55fbafc539615de3fffeab69be55f3aaf3")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                val result = String(responseBody)
                Log.d(TAG, result)
                val jsonArray = JSONArray(result)
                mData.clear()
                try {
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val fItem = followingItem(jsonObject.getString("avatar_url"),
                            0,
                            jsonObject.getString("login"),
                            jsonObject.getString("url"))
                        mData.add(fItem)
                    }
                    myAdapter.setData(mData)
                    progress.visibility == View.GONE
                }
                catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure", error?.message.toString())
            }

        })

    }

    private fun setupRecyclerView(){
        myAdapter = FollowingAdapter()
        followingRv.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}