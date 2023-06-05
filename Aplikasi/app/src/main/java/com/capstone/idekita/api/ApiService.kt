package com.capstone.idekita.api

import com.capstone.idekita.response.LoginResponse
import com.capstone.idekita.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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



}