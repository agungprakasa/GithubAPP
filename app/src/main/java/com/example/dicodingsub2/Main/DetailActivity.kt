package com.example.dicodingsub2.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.dicodingsub2.viewmodel.GithubViewModel
import com.example.dicodingsub2.viewmodel.GithubViewModelProviderFactory
import com.example.dicodingsub2.R
import com.example.dicodingsub2.adapter.GithubAdapter
import com.example.dicodingsub2.adapter.PagerAdapter
import com.example.dicodingsub2.db.ItemDB
import com.example.dicodingsub2.models.Item
import com.example.dicodingsub2.repository.GithubRepository
import com.google.android.material.tabs.TabLayout
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.detail_activity.*
import org.json.JSONObject

class DetailActivity : AppCompatActivity() {
    lateinit var viewModel: GithubViewModel
    lateinit var myAdapter: GithubAdapter
    private var statusFavorite: Boolean = false
    lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val githubRepository = GithubRepository(ItemDB(this))
        val viewModelProviderFactory =
            GithubViewModelProviderFactory(
                githubRepository
            )
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(GithubViewModel::class.java)

        item = intent.getParcelableExtra<Item>("EXTRA_PERSON") as Item
        Log.d("tag", "TEST ${item.login}")
        val PagerAdapter = PagerAdapter(this, supportFragmentManager)
        PagerAdapter?.login = item.login
        val viewPager: ViewPager = view_pager
        viewPager.adapter = PagerAdapter
        val tabs: TabLayout = tabs
        tabs.setupWithViewPager(viewPager)

        koneksi(item.login)

        viewModel.getItemByUser(item.login).observe(this, Observer {
            if (it != null ){
                statusFavorite = true
                item.id2 = it.id2
                setStatusFavorite(statusFavorite)
            }
        })

        fab.setOnClickListener {
            statusFavorite = !statusFavorite
            if (statusFavorite) {
                viewModel.saveUser(item)
                viewModel.getItemByUser(item.login).observe(this, Observer {
                    if (it != null ){
                        item.id2 = it.id2
                    }
                })
                Toast.makeText(this, "User saved successfully", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.deleteUser(item)
                Toast.makeText(this, "Hapus favorit successfully", Toast.LENGTH_SHORT).show()

            }
            setStatusFavorite(statusFavorite)
        }


    }


    fun koneksi(username: String) {
        val url = "https://api.github.com/users/${username}"
        val client = AsyncHttpClient()
        client.addHeader("Authorization", "token ec236f55fbafc539615de3fffeab69be55f3aaf3")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    textView2.text = responseObject.getString("login")
                    textView3.text = responseObject.getString("name")
                    textView9.text = responseObject.getString("location")
                    desc.text = responseObject.getString("company")
                    this@DetailActivity?.let {
                        Glide.with(it).load(responseObject.getString("avatar_url")).into(imageView2)
                    }
                    progressdetail.visibility = View.GONE
                } catch (e: Exception) {
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

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            fab.setImageResource(R.drawable.ic_baseline_favorite_24)

        } else {
            fab.setImageResource(R.drawable.ic_favorite)
        }
    }
}