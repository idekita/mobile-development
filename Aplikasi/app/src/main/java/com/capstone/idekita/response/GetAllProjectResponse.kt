package com.capstone.idekita.response

import com.google.gson.annotations.SerializedName

data class GetAllProjectResponse(

	@field:SerializedName("projects")
	val projects: List<ProjectsItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class ProjectsItem(

	@field:SerializedName("mean_rate")
	val meanRate: Int,

	@field:SerializedName("creator")
	val creator: String,

	@field:SerializedName("nm_proyek")
	val nmProyek: String,

	@field:SerializedName("id_kategori")
	val idKategori: Int,

	@field:SerializedName("tanggal_selesai")
	val tanggalSelesai: String,

	@field:SerializedName("jumlah_raters")
	val jumlahRaters: Int,

	@field:SerializedName("tanggal_mulai")
	val tanggalMulai: String,

	@field:SerializedName("total_rate")
	val totalRate: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("category")
	val category: Category,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("gambar")
	val gambar: String
)

data class Category(

	@field:SerializedName("nm_kategori")
	val nmKategori: String
)
