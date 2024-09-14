package com.devrhyan.eletriccarapp.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devrhyan.eletriccarapp.R
import com.devrhyan.eletriccarapp.databinding.ActivityMethodBinding

class MethodActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMethodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMethodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.result.text = getSharedPref().toString()

        binding.calc.setOnClickListener {
            getAutonomia()
        }

        binding.ivClose.setOnClickListener {
            finish()
        }

    }

    fun getAutonomia() {
        val km = binding.kmCar.text.toString().toFloat()
        val value = binding.priceCar.text.toString().toFloat()
        val final = value / km

        final.toString()
        savedSharePreference(final)
    }

    fun getSharedPref() : Float {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return 0.0f
        val pref = sharedPref.getFloat(getString(R.string.savedCalc), 0.0f)
        return pref;
    }


    fun savedSharePreference(value : Float) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putFloat(getString(com.devrhyan.eletriccarapp.R.string.savedCalc), value)
            apply()
        }
    }
}