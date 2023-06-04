package com.capstone.idekita.dummy.data

import android.os.Parcelable
import com.capstone.idekita.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Response (
    val name : String,
    val photo : Int,
    val desc : String,
    val date : String
):Parcelable

object DummyList {
    fun getTheList() : List<Response>{
        val theList = ArrayList<Response>()
        for (i in 0..10) {
            val list= Response(
                "Adi suhardi",
                R.drawable.logo_ide_kita_ik,
                "ini hanyalah dummy data",
                "12/12/12"


            )
            theList.add(list)
        }
        return theList
    }
}

object DummyListHorizotal {
    fun getTheList() : List<Response>{
        val theList = ArrayList<Response>()
        for (i in 0..10) {
            val list= Response(
                "Projek saya",
                R.drawable.page_2_gabut_idekita,
                "ini hanyalah dummy data",
                "12/12/12"


            )
            theList.add(list)
        }
        return theList
    }
}