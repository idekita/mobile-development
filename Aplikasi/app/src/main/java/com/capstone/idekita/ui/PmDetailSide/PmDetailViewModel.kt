package com.capstone.idekita.ui.PmDetailSide

import androidx.lifecycle.ViewModel
import com.capstone.idekita.data.ProjectRepository

class PmDetailViewModel(private val projectRepository: ProjectRepository) : ViewModel() {
    fun getProjById(token : String, idProj : Int) = projectRepository.getProjById(token,idProj)
    fun getToken()=projectRepository.getUser()

    fun regisKon(token: String,idProj: Int) = projectRepository.regisKon(token,idProj)

}