package com.example.dicodingsub2.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingsub2.Main.DetailActivity
import com.example.dicodingsub2.viewmodel.GithubViewModel
import com.example.dicodingsub2.Main.MainActivity
import com.example.dicodingsub2.R
import com.example.dicodingsub2.adapter.GithubAdapter
import com.example.dicodingsub2.util.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import com.example.dicodingsub2.util.Resource
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment: Fragment(R.layout.search_fragment){
    lateinit var viewModel: GithubViewModel
    lateinit var githubAdapter: GithubAdapter
    val TAG = "SearchFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        githubAdapter.setOnClickListener {
            val moveWithObjectIntent = Intent(context, DetailActivity::class.java)
            moveWithObjectIntent.putExtra("EXTRA_PERSON", it)
            startActivity(moveWithObjectIntent)
        }


        var job: Job? = null
        etSearch.addTextChangedListener{editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let{
                    if (editable.toString().isNotEmpty()){
                        viewModel.searchUsers(editable.toString())
                    }
                }
            }
        }

        viewModel.searchUsers.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.success -> {
                    hideProgressBar()
                    response.data?.let{ githubResponse ->
                        githubAdapter.differ.submitList(githubResponse.items)
                    }
                }
                is Resource.error ->{
                    hideProgressBar()
                    response.message?.let {
                            message ->
                        Log.e(TAG,"An error occured: $message")
                    }
                }
                is Resource.loading ->{
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        githubAdapter = GithubAdapter()
        rvSearchNews.apply {
            adapter = githubAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}