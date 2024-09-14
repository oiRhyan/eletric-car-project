package com.devrhyan.eletriccarapp.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
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
import kotlin.system.exitProcess

class CarFragment : Fragment() {

    private val adapter = CarAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewer = inflater.inflate(R.layout.car_fragment_layout, container, false)

        val carListViewer: RecyclerView = viewer.findViewById(R.id.rv_cars)
        val progressBar: ProgressBar = viewer.findViewById(R.id.progressBar)

        lifecycleScope.launch {
            try {
                progressBar.visibility = View.VISIBLE
                getAllCars()
                progressBar.visibility = View.GONE
            } catch (e: Exception) {
                Log.e("API", "Erro na requisição: ${e.message}")
                if (isAdded) {
                    showAlertDialog()
                }
            }
        }

        adapter.carItemListener = { item ->
            Toast.makeText(context, "Favoritado: ${item.preco}", Toast.LENGTH_SHORT).show()
        }

        carListViewer.adapter = adapter
        carListViewer.layoutManager = LinearLayoutManager(context)




        return viewer
    }

    private suspend fun getAllCars() {
        val retrofitClient = RetrofitService.getInstance()
        val response = retrofitClient.getCars()

        if (response.isSuccessful) {
            val body = response.body()
            adapter.setAllCars(body ?: emptyList())
            Log.e("API", body.toString())
        } else {
            throw Exception("Falha ao obter os carros: ${response.code()}")
        }
    }

    fun showAlertDialog() {
        if (isAdded) {
            val alertBuilder = AlertDialog.Builder(requireContext())
            alertBuilder.setTitle("Erro de conexão")
            alertBuilder.setMessage("Erro ao conectar-se à internet, verifique sua conexão e tente novamente mais tarde.")
            alertBuilder.setPositiveButton("OK") { dialog, _ -> exitProcess(0) }
            alertBuilder.show()
        }
    }


}