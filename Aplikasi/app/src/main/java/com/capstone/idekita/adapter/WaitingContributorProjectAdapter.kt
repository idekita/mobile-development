package com.capstone.idekita.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.idekita.R
import com.capstone.idekita.databinding.ItemContributorBinding
import com.capstone.idekita.response.Contributor
import com.capstone.idekita.response.ContributorsItem
import com.capstone.idekita.databinding.ItemContributorWaitingBinding
import com.capstone.idekita.response.ContributorsItemWait

class WaitingContributorProjectAdapter:
    ListAdapter<ContributorsItemWait,WaitingContributorProjectAdapter.ViewHolder>(DIFF_CALLBACK){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(private val binding: ItemContributorWaitingBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(data:ContributorsItemWait){
            binding.ivContributor.setImageResource(R.drawable.holder_person)
            binding.nameContributor.text = data.username
            binding.roleContributor.text = data.role
            binding.btnTolak.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContributorWaitingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null){
            holder.bind(data)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ContributorsItemWait)
        fun onItemTolakClicked(data: ContributorsItemWait)

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ContributorsItemWait>() {
            override fun areItemsTheSame(
                oldItem: ContributorsItemWait,
                newItem: ContributorsItemWait
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ContributorsItemWait,
                newItem: ContributorsItemWait
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }




}