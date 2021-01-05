package com.example.dicodingsub2.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.dicodingsub2.R
import com.example.dicodingsub2.adapter.FollowerAdapter
import com.example.dicodingsub2.adapter.GithubAdapter
import com.example.dicodingsub2.models.FollowerItem
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.detail_activity.*
import kotlinx.android.synthetic.main.fragment_follower.*
import kotlinx.android.synthetic.main.fragment_following.*
import kotlinx.android.synthetic.main.recyclerview_follower.*
import kotlinx.android.synthetic.main.search_fragment.*
import org.json.JSONArray
import org.json.JSONObject


class FollowerFragment : Fragment(R.layout.fragment_follower) {
    lateinit var myAdapter: FollowerAdapter
    var TAG = "FollowerFragment"
    private val mData = ArrayList<FollowerItem>()


    companion object {
        private const val ARG_USERNAME = "username"
        @JvmStatic
        fun newInstance(index: String) =
            FollowerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USERNAME, index)
                }
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_follower, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        koneksi()

    }

    fun koneksi() {
        val username = arguments?.getString(ARG_USERNAME)
        Log.d(TAG," TEST USERNAME $username")
        val url = "https://api.github.com/users/${username}/followers"
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
                        val fItem = FollowerItem(jsonObject.getString("avatar_url"),
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
        myAdapter = FollowerAdapter()
        followerRv.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}