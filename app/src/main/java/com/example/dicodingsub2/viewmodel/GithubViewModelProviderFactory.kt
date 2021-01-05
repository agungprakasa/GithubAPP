package com.example.dicodingsub2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingsub2.repository.GithubRepository

class GithubViewModelProviderFactory(
    val githubRepository: GithubRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GithubViewModel(
            githubRepository
        ) as T
    }
}