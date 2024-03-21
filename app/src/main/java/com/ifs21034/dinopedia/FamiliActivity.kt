package com.ifs21034.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21034.dinopedia.databinding.ActivityFamiliBinding

class FamiliActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFamiliBinding
    private val dataFamilis = ArrayList<Famili>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFamiliBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvFamili.setHasFixedSize(false)
        dataFamilis.addAll(getListFamilis())
        showRecyclerList()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    @SuppressLint("Recycle")
    private fun getListFamilis(): ArrayList<Famili> {
        val dataName =
            resources.getStringArray(R.array.famili_name)
        val dataIcon =
            resources.obtainTypedArray(R.array.famili_icon)
        val dataDescription =
            resources.getStringArray(R.array.famili_description)
        val dataReview =
            resources.getStringArray(R.array.famili_review)
        val dataPeriod =
            resources.getStringArray(R.array.famili_period)
        val dataCharacteristic =
            resources.getStringArray(R.array.famili_characteristic)
        val dataHabitat =
            resources.getStringArray(R.array.famili_habitat)
        val dataBehavior =
            resources.getStringArray(R.array.famili_behavior)
        val dataClassification =
            resources.getStringArray(R.array.famili_classification)

        val listFamili = ArrayList<Famili>()
        for (i in dataName.indices) {
            val famili = Famili(dataName[i],
                dataIcon.getResourceId(i, -1),dataReview[i], dataDescription[i],
                dataPeriod[i], dataCharacteristic[i], dataHabitat[i], dataBehavior[i], dataClassification[i])
            listFamili.add(famili)
        }
        return listFamili
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFamili.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvFamili.layoutManager =
                LinearLayoutManager(this)
        }

        val listFamiliAdapter = ListFamiliAdapter(dataFamilis)
        binding.rvFamili.adapter = listFamiliAdapter
        listFamiliAdapter.setOnItemClickCallback(object :
            ListFamiliAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Famili) {
                showSelectedFamili(data)
            }
        })
    }

    private fun showSelectedFamili(famili: Famili) {
        val intentWithData = Intent(this@FamiliActivity,
            DetailFamiliActivity::class.java)
        intentWithData.putExtra(DetailFamiliActivity.EXTRA_FAMILI, famili)
        startActivity(intentWithData)
    }

}