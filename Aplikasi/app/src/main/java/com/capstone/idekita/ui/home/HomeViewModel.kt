package com.capstone.idekita.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.idekita.model.wisataData
import com.capstone.idekita.model.wisataEntity

class HomeViewModel():ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Kategori dari ViewModel"
    }

    val text:LiveData<String> = _text

    private val _wisata = MutableLiveData<List<wisataEntity>>().apply {
        //val wisata = wisataRepository.getWisata()

        value = wisataData.wisatas

    }

    val wisata:LiveData<List<wisataEntity>> = _wisata

    fun getWisata():List<wisataEntity>{
        return wisataData.wisatas
    }

//    val wisata:LiveData<List<wisataEntity>>
//        get() = _wisata
//
//    fun getWisata(){
//        val wisata = wisataRepository.getWisata()
//          _wisata.value = wisata
//    }

    //instance viewModel
//    class ViewModelFactory(private val repository: WisataRepository):ViewModelProvider.NewInstanceFactory(){
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
//                return  HomeViewModel(repository) as T
//            }
//            throw IllegalArgumentException("Unknown ViewModel Class" + modelClass.name)
//        }
//    }

}