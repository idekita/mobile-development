package com.capstone.idekita.data

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.capstone.idekita.UserPreference
import com.capstone.idekita.api.ApiConfig
import com.capstone.idekita.api.ApiService
import com.capstone.idekita.model.UserModel
import com.capstone.idekita.model.wisataData
import com.capstone.idekita.model.wisataEntity
import com.capstone.idekita.response.GetAllProjectResponse
import com.capstone.idekita.response.ProjectsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectRepository(private val apiService: ApiService,private val pref:UserPreference) {

    fun getUser():LiveData<UserModel>{
        return pref.getUser().asLiveData()
    }

    suspend fun logout(){
        pref.logout()
    }



}