package com.ifs21034.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnFamili = findViewById<Button>(R.id.btnFamili)
        val btnDino = findViewById<Button>(R.id.btnDino)

        btnFamili.setOnClickListener {
            val intent = Intent(this@MainActivity, FamiliActivity::class.java)
            startActivity(intent)
        }

        btnDino.setOnClickListener {
            val intent = Intent(this@MainActivity, DinosaurActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                Toast.makeText(this, "Memilih menu about",
                    Toast.LENGTH_SHORT).show()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}