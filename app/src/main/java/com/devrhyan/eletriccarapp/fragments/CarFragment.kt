package com.devrhyan.eletriccarapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devrhyan.eletriccarapp.R
import com.devrhyan.eletriccarapp.models.adapters.CarAdapter
import com.devrhyan.eletriccarapp.repositorie.CarList

class CarFragment : Fragment() {

    private val adapter = CarAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewer = inflater.inflate(R.layout.car_fragment_layout, container, false)

        val carListViewer : RecyclerView = viewer.findViewById(R.id.rv_cars)

        adapter.setAllCars(CarList().ListCars)
        carListViewer.adapter = adapter
        carListViewer.layoutManager = LinearLayoutManager(context)

        return viewer
    }
}