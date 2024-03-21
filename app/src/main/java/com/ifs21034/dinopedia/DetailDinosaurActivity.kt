package com.ifs21034.dinopedia

import android.R
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21034.dinopedia.databinding.ActivityDetailDinoBinding

class DetailDinosaurActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDinoBinding
    private var dinosaur: Dinosaur? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dinosaur = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DINOSAUR,
                Dinosaur::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINOSAUR)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dinosaur != null) {
            supportActionBar?.title = "Dinosaurus ${dinosaur!!.name}"
            loadData(dinosaur!!)
        } else {
            finish()
        }
    }

    private fun loadData(dinosaur: Dinosaur) {
        binding.ivDetailIcon.setImageResource(dinosaur.icon)
        binding.tvDetailName.text = dinosaur.name
        binding.tvDetailDescription.text = dinosaur.description
        binding.tvDetailCharacteristic.text = dinosaur.characteristic
        binding.tvDetailGroup.text = dinosaur.group
        binding.tvDetailHabitat.text = dinosaur.habitat
        binding.tvDetailFood.text = dinosaur.food
        binding.tvDetailLength.text = dinosaur.length
        binding.tvDetailHeight.text = dinosaur.height
        binding.tvDetailWeight.text = dinosaur.weight
        binding.tvDetailWeakness.text = dinosaur.weakness
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_DINOSAUR = "extra_dinosaur"
    }
}