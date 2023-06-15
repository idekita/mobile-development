package com.capstone.idekita.ui.chatroom

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.idekita.R
import com.capstone.idekita.databinding.RvChatbubbleBinding
import com.capstone.idekita.databinding.RvMyProjectBinding
import com.capstone.idekita.response.DProjectsItem
import com.capstone.idekita.response.MessagesItem
import com.capstone.idekita.response.ProjectsItem
import com.capstone.idekita.ui.PmDetailSide.PmDetailProjectActivity


class ChatRoomAdapter(private val thisAccountUsername : String) :
    ListAdapter<MessagesItem, ChatRoomAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RvChatbubbleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding,thisAccountUsername)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    class MyViewHolder(private val binding: RvChatbubbleBinding,private val accountUsername: String) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MessagesItem) {
           if(data.username == accountUsername){
               binding.conChatSender.visibility = View.GONE
               binding.conChatMine.visibility = View.VISIBLE
               binding.tvNameMine.visibility = View.VISIBLE
               binding.tvNameMine.text = "${data.username} (PM)"
               binding.tvChatMine.text = data.message
           }else{
               binding.conChatMine.visibility = View.GONE
               binding.conChatSender.visibility = View.VISIBLE
               binding.tvNameSender.visibility = View.VISIBLE
               binding.tvNameSender.text = data.username
               binding.tvChatSender.text = data.message
           }


        }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MessagesItem>() {
            override fun areItemsTheSame(oldItem: MessagesItem, newItem: MessagesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MessagesItem,
                newItem: MessagesItem
            ): Boolean {
                return oldItem.message == newItem.message
            }
        }
    }
}