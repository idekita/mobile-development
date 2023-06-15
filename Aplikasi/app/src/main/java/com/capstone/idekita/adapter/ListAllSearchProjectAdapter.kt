package com.capstone.idekita.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.idekita.R
import com.capstone.idekita.databinding.ItemContributorWaitingBinding
import com.capstone.idekita.databinding.RvMyProjectBinding
import com.capstone.idekita.response.ContributorsItemWait
import com.capstone.idekita.response.ProjectsItem

class ListAllSearchProjectAdapter(private val listProject: List<ProjectsItem>):
    RecyclerView.Adapter<ListAllSearchProjectAdapter.ViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    class ViewHolder(binding: RvMyProjectBinding): RecyclerView.ViewHolder(binding.root) {
        val judulProyek: TextView = binding.tvName
        val imgProject:ImageView = binding.imgStory
        val namaCreator:TextView = binding.tvPmName
        val imgCreator:ImageView = binding.imgPm
        val status:TextView = binding.tvStatus
        val kategori:TextView = binding.isiCategory
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :ViewHolder{
        val binding = RvMyProjectBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder:ViewHolder,position: Int) {
        holder.judulProyek.text = listProject[position].nmProyek
        holder.namaCreator.text = listProject[position].creator
        holder.status.text = listProject[position].status
        holder.kategori.text = listProject[position].category.nmKategori
        holder.imgCreator.setImageResource(R.drawable.holder_person)
        Glide.with(holder.itemView.context)
            .load(listProject[position].gambar)
            .into(holder.imgProject)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listProject[position])
        }

    }

    override fun getItemCount() = listProject.size

    interface OnItemClickCallback {
        fun onItemClicked(data: ProjectsItem)

    }

}