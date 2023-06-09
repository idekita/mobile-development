package com.capstone.idekita.ui.home

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.capstone.idekita.api.ApiConfig
import com.capstone.idekita.data.ProjectRepository
import com.capstone.idekita.response.ContributorsItem
import com.capstone.idekita.response.GetAllProjectResponse
import com.capstone.idekita.response.GetContributorProjectResponse
import com.capstone.idekita.response.ProjectsItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val projectRepository: ProjectRepository) : ViewModel() {


    fun getUser() = projectRepository.getUser()

    fun logout() {
        viewModelScope.launch {
            projectRepository.logout()
        }
    }

    fun getAllProjectPaging(token: String): LiveData<PagingData<ProjectsItem>> {
        return projectRepository.getStoryPaging(token).cachedIn(viewModelScope)
    }

    private val _listProject = MutableLiveData<List<ProjectsItem>>()
    val listProject: LiveData<List<ProjectsItem>> = _listProject

    private val result = MediatorLiveData<Result<List<ProjectsItem>>>()
    fun getAllProject(token: String): LiveData<Result<List<ProjectsItem>>> {

        val client = ApiConfig.getApiService().getProjectList(token)
        client.enqueue(object : Callback<GetAllProjectResponse> {
            override fun onResponse(
                call: Call<GetAllProjectResponse>,
                response: Response<GetAllProjectResponse>
            ) {
                if (response.isSuccessful) {
                    _listProject.value = response.body()?.projects
                }
                Log.e(ContentValues.TAG, response.message())

            }

            override fun onFailure(call: Call<GetAllProjectResponse>, t: Throwable) {

                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
        return result
    }

    //listContributor
    private val _listContributor = MutableLiveData<List<ContributorsItem>>()
    val listContributor: LiveData<List<ContributorsItem>> = _listContributor

    private val result2 = MediatorLiveData<Result<List<ContributorsItem>>>()
    fun getAllContributor(token: String,id: Int): LiveData<Result<List<ContributorsItem>>> {

        val client = ApiConfig.getApiService().getContributor(token,id)
        client.enqueue(object : Callback<GetContributorProjectResponse> {
            override fun onResponse(
                call: Call<GetContributorProjectResponse>,
                response: Response<GetContributorProjectResponse>
            ) {
                if (response.isSuccessful) {
                    _listContributor.value = response.body()?.contributors
                }
                Log.e(ContentValues.TAG, response.message())

            }

            override fun onFailure(call: Call<GetContributorProjectResponse>, t: Throwable) {

                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
        return result2
    }

}