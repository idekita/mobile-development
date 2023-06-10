package com.capstone.idekita.ui.myProject

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.idekita.R
import com.capstone.idekita.databinding.RvMyProjectBinding
import com.capstone.idekita.response.DProjectsItem
import com.capstone.idekita.ui.PmDetailSide.PmDetailProjectActivity


class MyProjectAdapter :
    ListAdapter<DProjectsItem, MyProjectAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RvMyProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    class MyViewHolder(private val binding: RvMyProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DProjectsItem) {
            binding.tvName.text = data.nmProyek
            binding.tvPmName.text = data.creator
            Glide.with(itemView.context)
                .load(data.gambar)
                .into(binding.imgStory)
            binding.imgPm.setImageResource(R.drawable.holder_person)
            binding.tvCategory.text = data.category.nmKategori
            binding.tvStatus.text = data.status

            itemView.setOnClickListener {
            val intent = Intent(itemView.context, PmDetailProjectActivity::class.java)
            intent.putExtra(PmDetailProjectActivity.GET_ID,data.id)
            itemView.context.startActivity(intent)
                Toast.makeText(itemView.context,data.id.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DProjectsItem>() {
            override fun areItemsTheSame(oldItem: DProjectsItem, newItem: DProjectsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DProjectsItem,
                newItem: DProjectsItem
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}