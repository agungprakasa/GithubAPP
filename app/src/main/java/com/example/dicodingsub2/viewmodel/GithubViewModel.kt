package com.example.dicodingsub2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dicodingsub2.models.GithubResponse
import com.example.dicodingsub2.models.Item
import com.example.dicodingsub2.repository.GithubRepository
import com.example.dicodingsub2.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class GithubViewModel(
    val githubRepository: GithubRepository
): ViewModel() {
    val searchUsers: MutableLiveData<Resource<GithubResponse>> = MutableLiveData()

    fun searchUsers(searchQuery: String) = viewModelScope.launch {
        searchUsers.postValue(Resource.loading())
        val response= githubRepository.searchUsers(searchQuery)
        searchUsers.postValue(handleSearchNewsResponse(response))
    }

    private fun handleSearchNewsResponse(response: Response<GithubResponse>): Resource<GithubResponse>{
        if (response.isSuccessful){
            response.body()?.let{resultResponse ->
                return Resource.success(resultResponse)
            }
        }
        return Resource.error(response.message())
    }

    fun saveUser(item: Item) = viewModelScope.launch {
        githubRepository.upsert(item)
    }

    fun getSavedUser() = githubRepository.getSavedUser()

    fun deleteUser(item: Item) = viewModelScope.launch {
        githubRepository.deleteUser(item)
    }

    fun getItemByUser(username: String) = githubRepository.getItemByUsername(username)

}