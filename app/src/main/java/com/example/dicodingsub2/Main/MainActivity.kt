package com.example.dicodingsub2.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dicodingsub2.viewmodel.GithubViewModel
import com.example.dicodingsub2.viewmodel.GithubViewModelProviderFactory
import com.example.dicodingsub2.R
import com.example.dicodingsub2.db.ItemDB
import com.example.dicodingsub2.repository.GithubRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: GithubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val githubRepository = GithubRepository(ItemDB(this))
        val viewModelProviderFactory =
            GithubViewModelProviderFactory(
                githubRepository
            )
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(GithubViewModel::class.java)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

    }
}