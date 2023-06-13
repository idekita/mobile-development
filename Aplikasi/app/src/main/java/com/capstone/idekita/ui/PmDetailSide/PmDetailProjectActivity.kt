package com.capstone.idekita.ui.PmDetailSide

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.Glide
import com.capstone.idekita.api.ApiConfig
import com.capstone.idekita.databinding.ActivityAddProjectBinding
import com.capstone.idekita.databinding.ActivityPmDetailProjectBinding
import com.capstone.idekita.response.ProjectsItem
import com.capstone.idekita.response.RegisContributorResponse
import com.capstone.idekita.response.RegisterResponse
import com.capstone.idekita.result.TheResult
import com.capstone.idekita.ui.PmDetailSide.contributorAcc.ContributorAccActivity
import com.capstone.idekita.ui.PmDetailSide.contributorAcc.ProjectContributorFragment
import com.capstone.idekita.ui.addProject.AddProjectFactory
import com.capstone.idekita.ui.addProject.AddProjectViewModel
import com.capstone.idekita.ui.detailProject.DetailProjectActivity
import com.capstone.idekita.ui.home.HomeFragment
import com.capstone.idekita.ui.login.LoginActivity
import com.capstone.idekita.ui.register.RegisterActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        val project = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DATA, ProjectsItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }

        binding.idProyek.text = project?.id.toString()

        getDetail()


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

            if (project != null){
                Toast.makeText(this,"button contributor",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,ContributorAccActivity::class.java)

                val IdProyek = project.id
                val bundle = Bundle()
                bundle.putInt("extra_id",IdProyek)
                intent.putExtras(bundle)
                //intent.putExtra(ProjectContributorFragment.EXTRA_DATA,IdProyek)

                startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this as Activity).toBundle())
            }
        }

        binding.btnJoin.setOnClickListener {
            viewModel.getToken().observe(this){user->
                if (project != null){
                    joinKontributor(user.token,project.id)
                }

            }
        }

    }


    private fun getDetail(){
        val project = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DATA, ProjectsItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }

        val name = project?.creator

//        viewModel.getToken().observe(this){user ->
//            if (user.name == name){
//                binding.btCont.visibility = View.VISIBLE
//                binding.btSetFinish.visibility = View.VISIBLE
//            }else{
//                binding.btCont.visibility = View.GONE
//                binding.btSetFinish.visibility = View.GONE
//            }
//        }

        binding.apply {
            Glide.with(this@PmDetailProjectActivity)
                .load(project?.gambar)
                .into(ivDetail)
            nameTV.text = project?.nmProyek
            creatorTV.text = project?.creator
            kategoriTV.text = project?.category?.nmKategori
            descTV.text = project?.deskripsi
            mulaiTV.text = project?.tanggalMulai
            endTV.text = project?.tanggalSelesai
            statusTV.text = project?.status

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

                        val project = if (Build.VERSION.SDK_INT >= 33) {
                            intent.getParcelableExtra(EXTRA_DATA, ProjectsItem::class.java)
                        } else {
                            @Suppress("DEPRECATION")
                            intent.getParcelableExtra(EXTRA_DATA)
                        }


                        binding.apply {
                            Glide.with(this@PmDetailProjectActivity)
                                .load(res.gambar)
                                .into(ivDetail)
                            nameTV.text = project?.nmProyek
                            creatorTV.text = project?.creator
                            kategoriTV.text = project?.category?.nmKategori
                            descTV.text = project?.deskripsi
                            mulaiTV.text = project?.tanggalMulai
                            endTV.text = project?.tanggalSelesai
                            statusTV.text = project?.status

                        }
                    }
                    is TheResult.Error -> {

                    }
                }
            }
        }
    }


    private fun joinKontributor(token: String,idProj: Int){

                viewModel.regisKon(token,idProj).observe(this){res->
                    if (res != null) {
                        when (res) {
                            is TheResult.Loading -> {

                            }
                            is TheResult.Success -> {
                                Toast.makeText(this, res.data.message, Toast.LENGTH_SHORT).show()
                            }
                            is TheResult.Error -> {
                                Toast.makeText(this, res.error, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }

    }

    companion object{
        const val GET_ID = "id"
        const val EXTRA_DATA = "EXTRA_DATA"
    }
}