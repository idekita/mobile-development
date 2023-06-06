package com.capstone.idekita.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.idekita.data.ProjectRepository
import com.capstone.idekita.model.wisataData
import com.capstone.idekita.model.wisataEntity
import kotlinx.coroutines.launch

class HomeViewModel(private val projectRepository: ProjectRepository):ViewModel() {


    fun getUser() = projectRepository.getUser()

    fun logout(){
        viewModelScope.launch {
            projectRepository.logout()
        }
    }

}