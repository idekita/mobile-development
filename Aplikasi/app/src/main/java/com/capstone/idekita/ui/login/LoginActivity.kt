package com.capstone.idekita.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstone.idekita.MainActivity
import com.capstone.idekita.UserPreference
import com.capstone.idekita.api.ApiConfig
import com.capstone.idekita.databinding.ActivityLoginBinding
import com.capstone.idekita.model.UserModel
import com.capstone.idekita.response.LoginResponse
import com.capstone.idekita.ui.register.RegisterActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class LoginActivity : AppCompatActivity() {

    companion object {
        const val TAG = "LoginActivity"
    }

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setAction()
        setViewModel()

    }


    private fun setViewModel() {
        loginViewModel = ViewModelProvider(
            this,
            LoginViewModelFactory(UserPreference.getInstance(dataStore))
        )[LoginViewModel::class.java]
    }

    private fun setAction() {
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
                else -> {
                    login()
                }
            }
        }

        binding.LinkKedaftar.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login() {
        //showLoading(true)
        val username = binding.UsernameEditText.text.toString()
        val pasword = binding.PassworEditText.text.toString()

        val client = ApiConfig.getApiService().postLogin(username, pasword)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    // showLoading(false)

//                    responseBody.user.username
//                    responseBody.user.password
                    loginViewModel.saveUser(
                        UserModel(
                            responseBody.user.username,
                            responseBody.user.email,
                            responseBody.user.token,
                            //responseBody.user.,
                            isLogin = true
                        )
                    )
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    //showLoading(false)
                    Log.e(RegisterActivity.TAG, "onFailure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                //showLoading(false)
                Log.e(TAG, "onFailure ${t.message}")
            }

        })
    }


}