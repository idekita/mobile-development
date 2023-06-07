package com.capstone.idekita.ui.addProject

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.capstone.idekita.data.ProjectRepository
import okhttp3.MultipartBody
import java.text.SimpleDateFormat
import java.util.*

class AddProjectViewModel(private val projectRepository: ProjectRepository): ViewModel() {

    fun addProject(
        token : String,
        projName : String,
        idCat : String,
        desc : String,
        dateStart : String,
        dateFinish : String,
        img : MultipartBody.Part
    )=projectRepository.postAddProject(
        token,
        projName,
        idCat,
        desc,
        dateStart,
        dateFinish,
        img
    )

    // get today date
    @SuppressLint("SimpleDateFormat")
    fun getTodayDate(): String {
        val calendar = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy/MM/dd HH:mm")

        return formatter.format(calendar)
    }

    fun getToken()=projectRepository.getUser()

    fun cekKategori(cat : String) : String{
        var ret = ""
        when(cat){
            "Sosial" -> ret = "1"
            "Pendidikan" -> ret = "2"
            "Kesehatan" -> ret = "3"
            "Budaya" -> ret = "4"
            "Politik" -> ret = "5"
        }
        return ret
    }

}