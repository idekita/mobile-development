package com.capstone.idekita.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.idekita.R
import com.capstone.idekita.response.Contributor
import com.capstone.idekita.response.ContributorsItem
import com.capstone.idekita.databinding.ItemContributorWaitingBinding
import com.capstone.idekita.response.ContributorsItemWait

class WaitingContributorProjectAdapter(private val listContributor: List<ContributorsItemWait>,val token:String,val Role:String):
    RecyclerView.Adapter<WaitingContributorProjectAdapter.ViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    class ViewHolder(binding: ItemContributorWaitingBinding): RecyclerView.ViewHolder(binding.root) {
        val nama: TextView = binding.nameContributor
        val role: TextView = binding.roleContributor
        val btnTerima:Button = binding.btnTerimaa
        val btnTolak:Button = binding.btnTolak
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :ViewHolder{
        val binding = ItemContributorWaitingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder:ViewHolder,
        position: Int
    ) {
        holder.nama.text = listContributor[position].username
        holder.role.text = listContributor[position].role
        holder.btnTerima.setOnClickListener {
            onItemClickCallback.onItemClicked(listContributor[position])
        }
        holder.btnTolak.setOnClickListener {
            onItemClickCallback.onItemTolakClicked(listContributor[position])
        }
    }

    override fun getItemCount() = listContributor.size

    interface OnItemClickCallback {
        fun onItemClicked(data: ContributorsItemWait)
        fun onItemTolakClicked(data: ContributorsItemWait)

    }



}