package com.capstone.idekita.api

import com.capstone.idekita.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
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


    @GET("proyek")
    suspend fun getProjectListPaging(
        @Header("Authorization") token: String,
        @Query("Page") page: Int?,
        @Query("size") size: Int?,
        @Query("category") nmKategori : String
    ):Response<GetAllProjectResponse>

    @GET("kontributor/:id_proyek")
    fun geContributor(
        @Header("Authorization") token: String,
        @Query("id_kontributor") id_kontributor:Int,
    ):Call<GetContributorProjectResponse>

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