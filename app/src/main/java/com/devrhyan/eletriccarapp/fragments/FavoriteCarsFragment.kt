package com.devrhyan.eletriccarapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devrhyan.eletriccarapp.R
import com.devrhyan.eletriccarapp.models.adapters.CarAdapter
import com.devrhyan.eletriccarapp.repositorie.CarList
import com.devrhyan.eletriccarapp.repositorie.CarRepository

class FavoriteCarsFragment : Fragment() {

    val adapter = CarAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewer = inflater.inflate(R.layout.favorites_fragment_layout, container, false)

        val carListView: RecyclerView = viewer.findViewById(R.id.rv_cars_favorite)
        carListView.adapter = adapter
        carListView.layoutManager = LinearLayoutManager(context)


        updateCarList()

        adapter.carItemListener = { item ->
            val carRepository = CarRepository(requireContext())

            if (carRepository.findCarOnDb(item.id) == null) {
                carRepository.saveIfNotExists(item)
            } else {
                carRepository.deleteCar(item)
            }

            updateCarList()
        }

        return viewer
    }

    fun updateCarList() {
        val repository = CarRepository(requireContext())
        val updatedCarList = repository.getAllCars()
        adapter.setAllCars(updatedCarList)
    }
}