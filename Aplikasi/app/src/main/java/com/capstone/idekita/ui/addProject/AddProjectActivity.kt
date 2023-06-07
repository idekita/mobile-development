package com.capstone.idekita.ui.addProject

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.idekita.R
import com.capstone.idekita.databinding.ActivityAddProjectBinding
import com.capstone.idekita.result.TheResult
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AddProjectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProjectBinding
    private val viewModel by viewModels<AddProjectViewModel> {
        AddProjectFactory.getInstance(this)
    }
    private var getFile: File? = null

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val edProjName = binding.edProjName.text
        val edDesc = binding.edDesc.text

        binding.btGallery.setOnClickListener { startGallery() }

        binding.edDate.setOnClickListener { setFinishDate() }

        binding.btSubmit.setOnClickListener {
            val dateToday = viewModel.getTodayDate()
            val dateFinish = binding.edDate.text
            val kategori = binding.dropdownItem.text
            val isKategori = viewModel.cekKategori(kategori.toString())
            viewModel.getToken().observe(this) {
                uploadProject(it.token, "$edProjName", isKategori, "$edDesc", dateToday, "$dateFinish")
            }

        }
    }
    // Set dropdown menu
    override fun onResume() {
        super.onResume()
        val kategoriItem = resources.getStringArray(R.array.kategori)
        val dropDownItemAdapter = ArrayAdapter(this, R.layout.dropdown_add_project, kategoriItem)
        binding.dropdownItem.setAdapter(dropDownItemAdapter)
    }



    // Upload function
    private fun uploadProject(
        token: String,
        projName: String,
        kategori: String,
        deskripsi: String,
        dateStart: String,
        dateFinish: String,
    ) {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file", file.name, requestImageFile
            )
            viewModel.addProject(
                token,
                projName,
                kategori,
                deskripsi,
                dateStart,
                dateFinish,
                imageMultipart
            ).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is TheResult.Loading -> {

                        }
                        is TheResult.Success -> {

                            Toast.makeText(this, "berhasil dikirim", Toast.LENGTH_SHORT).show()
                        }
                        is TheResult.Error -> {
                            Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        } else {
            Toast.makeText(
                this@AddProjectActivity,
                "Silakan masukkan berkas gambar terlebih dahulu.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    // set Tanggal proyek selesai
    private fun setFinishDate() {
        val cal = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        try {
            val checkCal = dateFormat.parse(binding.edDate.text.toString())
            if (checkCal != null) {
                cal.time = checkCal
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { _, myear, mmonth, mday ->
            val z = "$mday/${mmonth + 1}/$myear"
            binding.edDate.setText(z)
        }, year, month, day).show()
    }


    // Gallery dan printil2an nya
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
                myFile.let { file ->
                    getFile = file
                    binding.ivImg.setImageURI(uri)
                }

            }
        }
    }


}