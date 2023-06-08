package com.capstone.idekita.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.idekita.databinding.RvMyProjectBinding
import com.capstone.idekita.response.ProjectsItem

class ProjectPagingAdapter : PagingDataAdapter<ProjectsItem,ProjectPagingAdapter.MyViewHolder>(
    DIFF_CALLBACK) {

    class MyViewHolder(private val binding: RvMyProjectBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ProjectsItem){
            binding.tvName.text = data.nmProyek
            Glide.with(itemView.context)
                .load(data.gambar)
                .into(binding.imgStory)

            itemView.setOnClickListener {

            }
        }

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null){
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = RvMyProjectBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProjectsItem>() {
            override fun areItemsTheSame(oldItem: ProjectsItem, newItem: ProjectsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ProjectsItem, newItem: ProjectsItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


}