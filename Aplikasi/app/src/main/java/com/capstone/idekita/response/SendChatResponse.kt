package com.capstone.idekita.response

import com.google.gson.annotations.SerializedName

data class SendChatResponse(

	@field:SerializedName("chatId")
	val chatId: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
