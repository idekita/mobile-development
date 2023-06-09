package com.capstone.idekita.ui.addProject

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.capstone.idekita.data.ProjectRepository
import okhttp3.MultipartBody
import java.text.SimpleDateFormat
import java.util.*

class AddProjectViewModel(private val projectRepository: ProjectRepository) : ViewModel() {

    fun addProject(
        token: String,
        projName: String,
        idCat: String,
        desc: String,
        dateStart: String,
        dateFinish: String,
//        datePost : String,
        img: MultipartBody.Part
    ) = projectRepository.postAddProject(
        token,
        projName,
        idCat,
        desc,
        dateStart,
        dateFinish,
//        datePost,
        img
    )

    // get today date
    @SuppressLint("SimpleDateFormat")
    fun getTodayDate(): String {
        val calendar = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy/MM/dd HH:mm")

        return formatter.format(calendar)
    }

    fun getProfil(token: String, username: String) = projectRepository.profil(token, username)
    fun regisKon(token: String, id_proj: Int) = projectRepository.regisKon(token, id_proj)
    fun reqKon(token: String, id: Int, statLamaran: String, role: String) =
        projectRepository.reqKon(token, id, statLamaran, role)

    fun changeStatus(token: String, id: Int, status: String) =
        projectRepository.changeStatusProject(token, id, status)

    fun getToken() = projectRepository.getUser()

    fun cekKategori(cat: String): String {
        var ret = ""
        when (cat) {
            "Sosial" -> ret = "1"
            "Pendidikan" -> ret = "2"
            "Kesehatan" -> ret = "3"
            "Budaya" -> ret = "4"
            "Politik" -> ret = "5"
        }
        return ret
    }

}