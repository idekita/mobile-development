package com.capstone.idekita.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.idekita.R
import com.capstone.idekita.response.ContributorsItem

class ContributorProjectAdapter(private val listContributor: List<ContributorsItem>) :
    RecyclerView.Adapter<ContributorProjectAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //val imgPhoto: ImageView = itemView.findViewById(R.id.iv_contributor)
        val nama: TextView = itemView.findViewById(R.id.name_contributor)
        val role: TextView = itemView.findViewById(R.id.role_contributor)


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contributor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listContributor[position]

        holder.nama.text = item.name
        holder.role.text = item.role

    }

    override fun getItemCount(): Int = listContributor.size
}