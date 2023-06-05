package com.capstone.idekita.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.capstone.idekita.MainActivity
import com.capstone.idekita.R
import com.capstone.idekita.api.ApiConfig
import com.capstone.idekita.databinding.ActivityLoginBinding
import com.capstone.idekita.response.LoginResponse
import com.capstone.idekita.ui.register.RegisterActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    companion object{
        const val TAG = "LoginActivity"
    }

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

//        binding.LinkKedaftar.setOnClickListener {
//            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
//            startActivity(intent)
//        }

//        binding.btnLogin.setOnClickListener {
//            val intent = Intent(this@LoginActivity,MainActivity::class.java)
//            startActivity(intent)
//        }
        setAction()

    }

    private fun setAction(){
        binding.btnLogin.setOnClickListener {
            val email = binding.UsernameEditText.text.toString()
            val password = binding.PassworEditText.text.toString()
            when {

                email.isEmpty() -> {
                    binding.UsernameEditText.error = "Masukkan email"
                }
                password.isEmpty() -> {
                    binding.passwordInputLayout.error = "Masukkan password"
                }
                else ->{
                    login()
                }
            }
        }

        binding.LinkKedaftar.setOnClickListener {
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(){
        //showLoading(true)
        val email = binding.UsernameEditText.text.toString()
        val pasword = binding.PassworEditText.text.toString()

        val client = ApiConfig.getApiService().postLogin(email,pasword)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null){
                   // showLoading(false)

                    responseBody.user.username
                    responseBody.user.password
//                    LoginViewModel.saveUser(
//                        UserModel(
//                            responseBody.loginResult.name,
//                            responseBody.loginResult.token,
//                            isLogin = true
//                        )
//                    )
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    //showLoading(false)
                    Log.e(RegisterActivity.TAG,"onFailure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                //showLoading(false)
                Log.e(TAG,"onFailure ${t.message}")
            }

        })
    }


}