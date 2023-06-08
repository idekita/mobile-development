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
import com.capstone.idekita.response.CreateProjectResponse
import com.capstone.idekita.response.ProjectsItem
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

    fun getStoryPaging(token: String):LiveData<PagingData<ProjectsItem>>{

        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                ProjectPagingSource(apiService,"Bearer ${token}")
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
        imgProj: MultipartBody.Part
    ): LiveData<TheResult<CreateProjectResponse>> = liveData {
        emit(TheResult.Loading)
        try {
            val projNameTo = projName.toRequestBody("text/plain".toMediaType())
            val catIdTo = categoryId.toRequestBody("text/plain".toMediaType())
            val deskripsiTo = deskripsi.toRequestBody("text/plain".toMediaType())
            val dateStartTo = dateStart.toRequestBody("text/plain".toMediaType())
            val dateFinishTo = dateFinish.toRequestBody("text/plain".toMediaType())
            val response = apiService.postAddProject(
                "Bearer $token",
                projNameTo,
                catIdTo,
                deskripsiTo,
                dateStartTo,
                dateFinishTo,
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


}