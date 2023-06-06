package com.capstone.idekita.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.idekita.R
import com.capstone.idekita.model.wisataEntity
import com.capstone.idekita.response.ProjectsItem

class ListAllProjectAdapter(private val listProject:List<ProjectsItem>):RecyclerView.Adapter<ListAllProjectAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback :OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ProjectsItem)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val imgPhoto: ImageView = itemView.findViewById(R.id.img_projectIV)
        val judul: TextView = itemView.findViewById(R.id.TitleProjectTV)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_latest_project,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val (id,name,photo,lokasi,desc) = listProject[position]
        holder.judul.text = name
//        Glide.with(holder.itemView.context)
//            .load(photo)
//            .into(holder.imgPhoto)
        holder.itemView.setOnClickListener {

            onItemClickCallback.onItemClicked(listProject[position])
        }

    }

    override fun getItemCount(): Int  = listProject.size

}