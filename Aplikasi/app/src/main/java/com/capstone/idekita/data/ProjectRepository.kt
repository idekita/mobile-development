package com.capstone.idekita.data

import com.capstone.idekita.model.wisataData
import com.capstone.idekita.model.wisataEntity

class ProjectRepository {

    fun getWisata():List<wisataEntity>{
        return wisataData.wisatas
    }

}