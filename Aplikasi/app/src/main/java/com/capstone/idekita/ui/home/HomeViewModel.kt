package com.capstone.idekita.ui.home

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.*
import com.capstone.idekita.api.ApiConfig
import com.capstone.idekita.data.ProjectRepository
import com.capstone.idekita.model.wisataData
import com.capstone.idekita.model.wisataEntity
import com.capstone.idekita.response.GetAllProjectResponse
import com.capstone.idekita.response.ProjectsItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val projectRepository: ProjectRepository):ViewModel() {


    fun getUser() = projectRepository.getUser()

    fun logout(){
        viewModelScope.launch {
            projectRepository.logout()
        }
    }

    private val _listProject = MutableLiveData<List<ProjectsItem>>()
    val listProject: LiveData<List<ProjectsItem>> = _listProject

    private val result = MediatorLiveData<Result<List<ProjectsItem>>>()
    fun getAllProject(token:String):LiveData<Result<List<ProjectsItem>>>{

        val client = ApiConfig.getApiService().getProjectList(token)
        client.enqueue(object: Callback<GetAllProjectResponse> {
            override fun onResponse(call: Call<GetAllProjectResponse>, response: Response<GetAllProjectResponse>) {
                if (response.isSuccessful){
                    _listProject.value = response.body()?.projects
                }
                Log.e(ContentValues.TAG,response.message())

            }

            override fun onFailure(call: Call<GetAllProjectResponse>, t: Throwable) {

                Log.e(ContentValues.TAG,"onFailure: ${t.message}")
            }

        })
        return result
    }

}