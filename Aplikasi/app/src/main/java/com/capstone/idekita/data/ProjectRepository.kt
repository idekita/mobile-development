package com.capstone.idekita.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.capstone.idekita.UserPreference
import com.capstone.idekita.api.ApiService
import com.capstone.idekita.model.UserModel
import com.capstone.idekita.response.*
import com.capstone.idekita.result.TheResult
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.HttpException

class ProjectRepository(private val apiService: ApiService, private val pref: UserPreference) {

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    suspend fun logout() {
        pref.logout()
    }

    fun getStoryPaging(token: String,kategori:String): LiveData<PagingData<ProjectsItem>> {

        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                ProjectPagingSource(apiService, "Bearer ${token}",kategori)
            }
        ).liveData
    }

    // Fungsi add project
    fun postAddProject(
        token: String,
        projName: String,
        categoryId: String,
        deskripsi: String,
        dateStart: String,
        dateFinish: String,
//        datePost: String,
        imgProj: MultipartBody.Part
    ): LiveData<TheResult<CreateProjectResponse>> = liveData {
        emit(TheResult.Loading)
        try {
            val projNameTo = projName.toRequestBody("text/plain".toMediaType())
            val catIdTo = categoryId.toRequestBody("text/plain".toMediaType())
            val deskripsiTo = deskripsi.toRequestBody("text/plain".toMediaType())
            val dateStartTo = dateStart.toRequestBody("text/plain".toMediaType())
            val dateFinishTo = dateFinish.toRequestBody("text/plain".toMediaType())
//            val datePostTo = datePost.toRequestBody("text/plain".toMediaType())
            val response = apiService.postAddProject(
                "Bearer $token",
                projNameTo,
                catIdTo,
                deskripsiTo,
                dateStartTo,
                dateFinishTo,
//                datePostTo,
                imgProj,
            )
            val successPost = TheResult.Success(response)
            emit(successPost)

        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorMassage = errorBody?.let { JSONObject(it).getString("message") }
            emit(TheResult.Error(errorMassage.toString()))
        }
    }

    fun profil(token: String, username: String): LiveData<TheResult<ProfilResponse>> = liveData {
        emit(TheResult.Loading)
        try {
            val response = apiService.profil("Bearer $token", username)
            val successPost = TheResult.Success(response)
            emit(successPost)

        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorMassage = errorBody?.let { JSONObject(it).getString("message") }
            emit(TheResult.Error(errorMassage.toString()))
        }
    }

    fun changeStatusProject(
        token: String,
        id: Int,
        status: String
    ): LiveData<TheResult<ChangeStatusResponse>> = liveData {
        emit(TheResult.Loading)
        try {
            val response = apiService.changeStatus("Bearer $token", id, status)
            val successPost = TheResult.Success(response)
            emit(successPost)

        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorMassage = errorBody?.let { JSONObject(it).getString("message") }
            emit(TheResult.Error(errorMassage.toString()))
        }
    }

    fun regisKon(token: String, id_proj: Int): LiveData<TheResult<RegisContributorResponse>> =
        liveData {
            emit(TheResult.Loading)
            try {
                val response = apiService.regisKontributor("Bearer $token", id_proj)
                val successPost = TheResult.Success(response)
                emit(successPost)

            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val errorMassage = errorBody?.let { JSONObject(it).getString("message") }
                emit(TheResult.Error(errorMassage.toString()))
            }
        }

    fun reqKon(
        token: String,
        id: Int,
        statLamaran: String,
        role: String
    ): LiveData<TheResult<UpdateContributorResponse>> = liveData {
        emit(TheResult.Loading)
        try {
            val response = apiService.reqKontributor("Bearer $token", id, statLamaran, role)
            val successPost = TheResult.Success(response)
            emit(successPost)

        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorMassage = errorBody?.let { JSONObject(it).getString("message") }
            emit(TheResult.Error(errorMassage.toString()))
        }
    }

    fun getMyProjectItem(token: String, status: String): LiveData<TheResult<List<DProjectsItem>>> =
        liveData {
            emit(TheResult.Loading)
            try {
                val response = apiService.myProject("Bearer $token", status)
                val successPost = TheResult.Success(response.projects)
                emit(successPost)

            } catch (e: HttpException) {
//            val errorBody = e.response()?.errorBody()?.string()
//            val errorMassage = errorBody?.let { JSONObject(it).getString("message") }
//            emit(TheResult.Error(errorMassage.toString()))
            }
        }


}