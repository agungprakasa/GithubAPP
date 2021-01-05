package com.example.dicodingsub2.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingsub2.Main.DetailActivity
import com.example.dicodingsub2.viewmodel.GithubViewModel
import com.example.dicodingsub2.Main.MainActivity
import com.example.dicodingsub2.R
import com.example.dicodingsub2.adapter.GithubAdapter
import com.example.dicodingsub2.util.Constants
import com.example.dicodingsub2.util.Constants.Companion.CONTENT_URI
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favorit.*


class FavoritFragment : Fragment(R.layout.fragment_favorit) {


    lateinit var viewModel: GithubViewModel
    lateinit var myAdapter: GithubAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

       myAdapter.setOnClickListener {
            val moveWithObjectIntent = Intent(context, DetailActivity::class.java)
            moveWithObjectIntent.putExtra("EXTRA_PERSON", it)
            startActivity(moveWithObjectIntent)
        }


//        val uriWithId = Uri.parse(CONTENT_URI.toString())
//        val cursor = context?.contentResolver?.query(uriWithId, null, null, null, null)
//
//        if (cursor != null) {
//          cursor.moveToNext()
//            Log.d(""," TEST ${cursor.count}")
//        }



        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val positon = viewHolder.adapterPosition
                val user = myAdapter.differ.currentList[positon]
                viewModel.deleteUser(user)
                Snackbar.make(view,"Successfully deleted user", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){
                        viewModel.saveUser(user)
                    }
                    show()
                }
            }

        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvFavorit)
        }

        viewModel.getSavedUser().observe(viewLifecycleOwner, Observer {
                users -> myAdapter.differ.submitList(users)
        })
    }

    private fun setupRecyclerView(){
        myAdapter = GithubAdapter()
        rvFavorit.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}