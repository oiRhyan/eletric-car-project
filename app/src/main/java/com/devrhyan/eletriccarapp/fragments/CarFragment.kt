package com.devrhyan.eletriccarapp.fragments

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devrhyan.eletriccarapp.R
import com.devrhyan.eletriccarapp.models.Car
import com.devrhyan.eletriccarapp.models.adapters.CarAdapter
import com.devrhyan.eletriccarapp.repositorie.CarList
import com.devrhyan.eletriccarapp.services.RetrofitService
import kotlinx.coroutines.launch

class CarFragment : Fragment() {


    private val adapter = CarAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        lifecycleScope.launch {
            getAllCars()
        }

        val viewer = inflater.inflate(R.layout.car_fragment_layout, container, false)

        val carListViewer : RecyclerView = viewer.findViewById(R.id.rv_cars)

        carListViewer.adapter = adapter
        carListViewer.layoutManager = LinearLayoutManager(context)

        return viewer
    }

    private suspend fun getAllCars() {
        try {
            val retrofitClient = RetrofitService.getInstance()
            val response = retrofitClient.getCars()

            if(response.isSuccessful) {
                val body = response.body()
                adapter.setAllCars(body ?: emptyList())
                Log.e("API", body.toString())
            }
        } catch (e : Exception) {
            Log.e("API", e.message.toString())
        }
    }

}