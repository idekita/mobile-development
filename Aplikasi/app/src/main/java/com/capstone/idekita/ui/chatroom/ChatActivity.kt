package com.capstone.idekita.ui.chatroom

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.capstone.idekita.databinding.ActivityAddProjectBinding
import com.capstone.idekita.databinding.ActivityChatBinding
import com.capstone.idekita.response.ProjectsItem
import com.capstone.idekita.result.TheResult
import com.capstone.idekita.ui.PmDetailSide.PmDetailProjectActivity
import com.capstone.idekita.ui.addProject.AddProjectFactory
import com.capstone.idekita.ui.addProject.AddProjectViewModel
import com.capstone.idekita.ui.myProject.MyProjectAdapter
import okhttp3.internal.notify

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel by viewModels<ChatRoomViewModel> {
        ChatRoomFactory.getInstance(this)
    }

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private val adapter = ChatRoomAdapter("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)



        handler = Handler()
        runnable = object : Runnable {
            override fun run() {
                tes()
                handler.postDelayed(this, 3000)
            }
        }
        handler.post(runnable)

        binding.btSend.setOnClickListener{
            if(binding.edChatBox.text.isNotEmpty()){
                viewModel.getToken().observe(this){
                    val chatBox = binding.edChatBox.text
                    val projId = intent.getIntExtra(PROJ_ID,1)
                    sendChat(it.token,projId,"$chatBox")
                }
            }
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    fun tes(){


        viewModel.getToken().observe(this){
            val adapter = ChatRoomAdapter(it.name)
            val projId = intent.getIntExtra(PROJ_ID,1)
            getChatRoom(it.token,projId,it.name)

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getChatRoom(token : String, projId : Int, userName : String){
        viewModel.getChatRoom(token,projId).observe(this){ result ->
            if(result != null){
                when(result){
                    is TheResult.Loading ->{

                    }
                    is TheResult.Success -> {

                        val myProjData = result.data
                        val theAdapter = ChatRoomAdapter(userName)

                        theAdapter.submitList(myProjData)
                            binding.rvChat.apply {
                                layoutManager = LinearLayoutManager(context)
                                (layoutManager as LinearLayoutManager).stackFromEnd = true
                                (layoutManager as LinearLayoutManager).reverseLayout = true
                                adapter = theAdapter
                            }
                    }
                    is TheResult.Error -> {

                    }
                }
            }
        }
    }

    private fun sendChat(token : String, projId : Int, pesan : String){
        viewModel.sendChat(token,projId,pesan).observe(this){ result ->
            if(result != null){
                when(result){
                    is TheResult.Loading ->{

                    }
                    is TheResult.Success -> {

                        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        val view = this.currentFocus
                        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)

                        binding.edChatBox.text.clear()
                        val myProjData = result.data
                        Log.i("send_chat","berhasil dikirim")
                    }
                    is TheResult.Error -> {

                    }
                }
            }
        }
    }

    companion object{
        const val PROJ_ID = "proyek_id"
    }
}