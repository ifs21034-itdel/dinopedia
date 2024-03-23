package com.ifs21034.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21034.dinopedia.databinding.ActivityDinosaurBinding

class DinosaurActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDinosaurBinding
    private val dataDinosaurs = ArrayList<Dinosaur>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinosaurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvDinosaur.setHasFixedSize(false)
        dataDinosaurs.addAll(getListDinosaurs())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListDinosaurs(): ArrayList<Dinosaur> {
        val famili = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(
                EXTRA_FAMILI, Famili::class.java
            )
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILI)
        }

        val dataName =
            resources.getStringArray(R.array.dino_name)
        val dataIcon =
            resources.obtainTypedArray(R.array.dino_icon)
        val dataDescription =
            resources.getStringArray(R.array.dino_desc)
        val dataReview =
            resources.getStringArray(R.array.dino_review)
        val dataCharacteristic =
            resources.getStringArray(R.array.dino_characteristic)
        val dataGroup =
            resources.getStringArray(R.array.dino_group)
        val dataHabitat =
            resources.getStringArray(R.array.dino_habitat)
        val dataFood =
            resources.getStringArray(R.array.dino_food)
        val dataLength =
            resources.getStringArray(R.array.dino_length)
        val dataHeight =
            resources.getStringArray(R.array.dino_heigth)
        val dataWeight =
            resources.getStringArray(R.array.dino_weight)
        val dataWeakness =
            resources.getStringArray(R.array.dino_weakness)

        val startRange = famili?.start
        val endRange = famili?.end

        val listDinosaur = ArrayList<Dinosaur>()
        if(startRange == null || endRange == null){
            for (i in dataName.indices) {
                val dinosaur = Dinosaur(dataName[i],
                    dataIcon.getResourceId(i, -1),dataReview[i], dataDescription[i], dataCharacteristic[i], dataGroup[i], dataHabitat[i], dataFood[i], dataLength[i], dataHeight[i], dataWeight[i], dataWeakness[i])
                listDinosaur.add(dinosaur)
            }
        }else{
            for (i in startRange!!..endRange!!) {
                val filteredDinosaur = Dinosaur(dataName[i],
                    dataIcon.getResourceId(i, -1),dataReview[i], dataDescription[i], dataCharacteristic[i], dataGroup[i], dataHabitat[i], dataFood[i], dataLength[i], dataHeight[i], dataWeight[i], dataWeakness[i])
                listDinosaur.add(filteredDinosaur)
            }
        }

        return listDinosaur
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDinosaur.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvDinosaur.layoutManager =
                LinearLayoutManager(this)
        }

        val listDinosaurAdapter = ListDinosaurAdapter(dataDinosaurs)
        binding.rvDinosaur.adapter = listDinosaurAdapter
        listDinosaurAdapter.setOnItemClickCallback(object : ListDinosaurAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dinosaur) {
                showSelectedDinosaur(data)
            }
        })
    }

    private fun showSelectedDinosaur(dinosaur: Dinosaur) {
        val intentWithData = Intent(this@DinosaurActivity,
            DetailDinosaurActivity::class.java)
        intentWithData.putExtra(DetailDinosaurActivity.EXTRA_DINOSAUR, dinosaur)
        startActivity(intentWithData)
    }

    companion object {
        const val EXTRA_FAMILI = "extra_famili"
    }

}