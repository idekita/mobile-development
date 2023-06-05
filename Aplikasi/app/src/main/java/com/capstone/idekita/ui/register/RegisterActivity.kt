package com.capstone.idekita.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.capstone.idekita.api.ApiConfig
import com.capstone.idekita.databinding.ActivityRegisterBinding
import com.capstone.idekita.response.RegisterResponse
import com.capstone.idekita.ui.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {

    companion object {
        const val TAG = "registerActivity"
    }

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setAction()

    }

    private fun setAction() {
        binding.btnRegister.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.EmailEditText.text.toString()
            val password = binding.PassworEditText.text.toString()
            val username = binding.UsernameEditText.text.toString()
            when {
                name.isEmpty() -> {
                    binding.nameInputLayout.error = "Masukkan nama"
                }
                email.isEmpty() -> {
                    binding.emailInputLayout.error = "Masukkan email"
                }
                password.isEmpty() -> {
                    binding.passwordInputLayout.error = "Masukkan password"
                }
                username.isEmpty() ->{
                    binding.UsernameInputLayout.error = "Masukkan Username"
                }
                else -> {
                    register()
                }
            }
        }
    }

    private fun register() {
        //showLoading(true)
        val name = binding.nameEditText.text.toString()
        val email = binding.EmailEditText.text.toString()
        val password = binding.PassworEditText.text.toString()
        val username = binding.UsernameEditText.text.toString()

        val client = ApiConfig.getApiService().postRegister(name,email,password,username)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                val responseBody = response.body()
                //showLoading(false)
                if (response.isSuccessful && responseBody != null){
                    AlertDialog.Builder(this@RegisterActivity).apply {
                        setTitle("Anda Telah Terdaftar")
                        setMessage("Silahkan klik lanjut untuk menuju ke halaman login")
                        setPositiveButton("Lanjut") {_,_ ->
                            val intent = Intent(context, LoginActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()
                        }
                        create()
                        show()
                    }
                }
                else{
                    //showLoading(false)
                    Log.e(TAG,"onFailure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                //showLoading(false)
                Log.e(TAG,"onFailure ${t.message}")
            }

        })


    }


}