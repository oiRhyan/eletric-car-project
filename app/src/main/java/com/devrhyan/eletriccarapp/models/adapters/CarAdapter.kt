package com.devrhyan.eletriccarapp.models.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.devrhyan.eletriccarapp.R
import com.devrhyan.eletriccarapp.models.Car

class CarAdapter : Adapter<CarAdapter.CarViewHolder>() {

    private var CarList : List<Car> = listOf();

    fun setAllCars(list : MutableList<Car>) {
        CarList = list
        notifyDataSetChanged()
    }

    inner class CarViewHolder(view : View) : ViewHolder(view) {
         val priceCar : TextView = view.findViewById(R.id.tv_preco_value)
         val bateriaCar : TextView = view.findViewById(R.id.tv_bateria_value)
         val potenciaCar : TextView = view.findViewById(R.id.tv_potencia_value)
         val recargaCar: TextView = view.findViewById(R.id.tv_recarga_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val View = layoutInflater.inflate(R.layout.recycler_car_view_item, parent, false)
        return CarViewHolder(View)
    }

    override fun getItemCount(): Int {
        return CarList.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val item = CarList[position]
        holder.priceCar.text = item.price
        holder.bateriaCar.text = item.bateria
        holder.potenciaCar.text = item.potencia
        holder.recargaCar.text = item.recarga
    }

}