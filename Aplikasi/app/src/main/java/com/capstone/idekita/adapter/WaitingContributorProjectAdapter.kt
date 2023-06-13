package com.capstone.idekita.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.idekita.R
import com.capstone.idekita.response.ContributorsItem

class WaitingContributorProjectAdapter(private val listContributor: List<ContributorsItem>):
    RecyclerView.Adapter<WaitingContributorProjectAdapter.ViewHolder>(){

    class ViewHolder(itemview:View): RecyclerView.ViewHolder(itemview) {
        val nama: TextView = itemView.findViewById(R.id.name_contributor)
        val role: TextView = itemView.findViewById(R.id.role_contributor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_contributor_waiting,parent,false))

    override fun onBindViewHolder(
        holder:ViewHolder,
        position: Int
    ) {
        holder.nama.text = listContributor[position].username
        holder.role.text = listContributor[position].role
    }

    override fun getItemCount() = listContributor.size


}