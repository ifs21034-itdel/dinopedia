package com.ifs21034.dinopedia

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21034.dinopedia.databinding.ActivityDetailFamiliBinding

class DetailFamiliActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFamiliBinding
    private var famili: Famili? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFamiliBinding.inflate(layoutInflater)
        setContentView(binding.root)

        famili = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILI,
                Famili::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILI)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (famili != null) {
            supportActionBar?.title = "Buah ${famili!!.name}"
            loadData(famili!!)
        } else {
            finish()
        }
    }

    private fun loadData(famili: Famili) {
        binding.ivDetailIcon.setImageResource(famili.icon)
        binding.tvDetailName.text = famili.name
        binding.tvDetailDescription.text = famili.description
        binding.tvDetailPeriode.text = famili.period
        binding.tvDetailCharacteristic.text = famili.characteristic
        binding.tvDetailHabitat.text = famili.habitat
        binding.tvDetailBehavior.text = famili.behavior
        binding.tvDetailClassification.text = famili.classification
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_FAMILI = "extra_famili"
    }

}