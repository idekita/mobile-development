package com.capstone.idekita.api

import com.capstone.idekita.response.CreateProjectResponse
import com.capstone.idekita.response.GetAllProjectResponse
import com.capstone.idekita.response.LoginResponse
import com.capstone.idekita.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun postLogin(
        @Field("username") username: String,
        @Field("password") password: String,
    ) : Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun postRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("username") username: String,
    ) : Call<RegisterResponse>

    @GET("proyek")
    fun getProjectList(
        @Header("Authorization") token: String,
    ):Call<GetAllProjectResponse>

    @Multipart
    @POST("proyek")
    suspend fun postAddProject(
        @Header("Authorization") token: String,
        @Part("nm_proyek") projName: RequestBody,
        @Part("id_kategori") categoryId: RequestBody,
        @Part("deskripsi") desc: RequestBody,
        @Part("tanggal_mulai") dateStart: RequestBody,
        @Part("tanggal_selesai") dateEnd: RequestBody,
        @Part imgProj: MultipartBody.Part,
    ) : CreateProjectResponse



}