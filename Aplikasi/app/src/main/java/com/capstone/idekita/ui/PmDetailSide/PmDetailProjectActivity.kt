package com.capstone.idekita.ui.PmDetailSide

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.Glide
import com.capstone.idekita.databinding.ActivityAddProjectBinding
import com.capstone.idekita.databinding.ActivityPmDetailProjectBinding
import com.capstone.idekita.response.ProjectsItem
import com.capstone.idekita.result.TheResult
import com.capstone.idekita.ui.PmDetailSide.contributorAcc.ContributorAccActivity
import com.capstone.idekita.ui.addProject.AddProjectFactory
import com.capstone.idekita.ui.addProject.AddProjectViewModel

class PmDetailProjectActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPmDetailProjectBinding
    private val viewModel by viewModels<PmDetailViewModel> {
        PmDetailFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPmDetailProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getToken().observe(this){
            val idProj = intent.getIntExtra(GET_ID,0)
            if(idProj > 0){
                getDetail(it.token,idProj)
            }

        }

        binding.btSetFinish.setOnClickListener{
            val alertDialog = AlertDialog.Builder(this)
                .setTitle("Menyelesaikan Proyek")
                .setMessage("Anda tidak dapat mengubah lagi status proyek saat proyek itu selesai")
                .setPositiveButton("OK") { dialog, _ ->
                    finish()
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    // Aksi yang dilakukan saat tombol Batal ditekan
                    dialog.dismiss()
                }
                .create()

            alertDialog.show()
        }

        binding.btCont.setOnClickListener{
            Toast.makeText(this,"button contributor",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,ContributorAccActivity::class.java)
            startActivity(intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(this as Activity).toBundle())
        }

    }

    private fun getDetail(token : String, idProj : Int){
        viewModel.getProjById(token,idProj).observe(this){ data ->
            if(data != null){
                when(data){
                    is TheResult.Loading ->{

                    }
                    is TheResult.Success -> {
                        val res = data.data[0]

                        binding.apply {
                            Glide.with(this@PmDetailProjectActivity)
                                .load(res.gambar)
                                .into(ivDetail)
                            nameTV.text = res.nmProyek
                            creatorTV.text = res.creator
                            kategoriTV.text = res.category.nmKategori
                            descTV.text = res.deskripsi
                            mulaiTV.text = res.tanggalMulai
                            endTV.text = res.tanggalSelesai
                            statusTV.text = res.status

                        }
                    }
                    is TheResult.Error -> {

                    }
                }
            }
        }
    }

    companion object{
        const val GET_ID = "id"
    }
}