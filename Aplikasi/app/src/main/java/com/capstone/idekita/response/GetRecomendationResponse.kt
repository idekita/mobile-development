package com.capstone.idekita.response

import com.google.gson.annotations.SerializedName

data class GetRecomendationResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("recommendations")
	val recommendations: List<RecommendationsItem>,

	@field:SerializedName("status")
	val status: String
)

data class RecommendationsItem(

	@field:SerializedName("project")
	val project: ProjectRecomendation,

	@field:SerializedName("project_title")
	val projectTitle: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("id_user")
	val idUser: Int,

	@field:SerializedName("user")
	val user: UserRecomendation,

	@field:SerializedName("id_project")
	val idProject: Int
)

data class ProjectRecomendation(

	@field:SerializedName("mean_rate")
	val meanRate: Int,

	@field:SerializedName("creator")
	val creator: String,

	@field:SerializedName("nm_proyek")
	val nmProyek: String,

	@field:SerializedName("id_kategori")
	val idKategori: Int,

	@field:SerializedName("total_rate")
	val totalRate: Int,

	@field:SerializedName("gambar")
	val gambar: String,

	@field:SerializedName("tanggal_selesai")
	val tanggalSelesai: String,

	@field:SerializedName("jumlah_raters")
	val jumlahRaters: Int,

	@field:SerializedName("postedAt")
	val postedAt: String,

	@field:SerializedName("tanggal_mulai")
	val tanggalMulai: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("category")
	val category: CategoryRecomendation,

	@field:SerializedName("status")
	val status: String
)

data class CategoryRecomendation(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("nm_kategori")
	val nmKategori: String
)

data class UserRecomendation(

	@field:SerializedName("id_user")
	val idUser: Int,

	@field:SerializedName("username")
	val username: String
)
