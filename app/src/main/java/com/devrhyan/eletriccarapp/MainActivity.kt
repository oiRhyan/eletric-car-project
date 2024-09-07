package com.devrhyan.eletriccarapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.devrhyan.eletriccarapp.databinding.ActivityMainBinding
import com.devrhyan.eletriccarapp.models.Car
import com.devrhyan.eletriccarapp.models.adapters.CarAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val adapter = CarAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ListCars : MutableList<Car> = mutableListOf(
            Car(
                "R$ 300.000,00",
                "100 kWh",
                "200cv",
                "30 min"),
            Car(
                "R$ 10.000,00",
                "90 kWh",
                "200cv",
                "20 min"
            ),
            Car(
                "R$ 20.000,00",
                "50 kWh",
                "200cv",
                "35 min"
            ),
            Car(
                "R$ 20.000,00",
                "50 kWh",
                "200cv",
                "35 min"
            ),
            Car(
                "R$ 20.000,00",
                "50 kWh",
                "200cv",
                "35 min"
            ),
            Car(
                "R$ 20.000,00",
                "50 kWh",
                "200cv",
                "35 min"
            ),
            Car(
                "R$ 20.000,00",
                "50 kWh",
                "200cv",
                "35 min"
            )
        )

        adapter.setAllCars(ListCars)
        binding.rvCars.adapter = adapter
        binding.rvCars.layoutManager = LinearLayoutManager(this)

    }

}