package com.capstone.idekita.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.capstone.idekita.UserPreference
import com.capstone.idekita.api.ApiService
import com.capstone.idekita.model.UserModel
import com.capstone.idekita.model.wisataData
import com.capstone.idekita.model.wisataEntity

class ProjectRepository(private val apiService: ApiService,private val pref:UserPreference) {

    fun getUser():LiveData<UserModel>{
        return pref.getUser().asLiveData()
    }

    suspend fun logout(){
        pref.logout()
    }

}