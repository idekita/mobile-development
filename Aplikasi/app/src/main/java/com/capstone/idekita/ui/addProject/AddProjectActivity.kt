package com.capstone.idekita.ui.addProject

import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.capstone.idekita.R
import com.capstone.idekita.databinding.ActivityAddProjectBinding
import com.capstone.idekita.databinding.ActivityMainBinding

class AddProjectActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddProjectBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btGallery.setOnClickListener(){
            startGallery()
        }

        binding.btSubmit.setOnClickListener{
            val x = binding.dropdownItem.text.toString()

            Toast.makeText(this,x,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        val kategoriItem = resources.getStringArray(R.array.kategori)
        val dropDownItemAdapter = ArrayAdapter(this,R.layout.dropdown_add_project,kategoriItem)
        binding.dropdownItem.setAdapter(dropDownItemAdapter)
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data as Uri
            selectedImg.let { uri ->
                val myFile = uriToFile(uri, this@AddProjectActivity)
                binding.ivImg.setImageURI(uri)
            }
        }
    }
}