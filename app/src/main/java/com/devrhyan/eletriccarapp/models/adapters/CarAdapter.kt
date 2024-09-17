package com.devrhyan.eletriccarapp.models.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.devrhyan.eletriccarapp.R
import com.devrhyan.eletriccarapp.models.Car

class CarAdapter : Adapter<CarAdapter.CarViewHolder>() {

    private var carList: List<Car> = listOf()

    fun setAllCars(list: List<Car>) {
        carList = list
        notifyDataSetChanged() // Notifica toda a lista se você estiver trocando a lista inteira
    }

    var carItemListener: (Car) -> Unit = {}

    inner class CarViewHolder(view: View) : ViewHolder(view) {
        val priceCar: TextView = view.findViewById(R.id.tv_preco_value)
        val bateriaCar: TextView = view.findViewById(R.id.tv_bateria_value)
        val potenciaCar: TextView = view.findViewById(R.id.tv_potencia_value)
        val recargaCar: TextView = view.findViewById(R.id.tv_recarga_value)
        val favoriteButton: ImageView = view.findViewById(R.id.star)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recycler_car_view_item, parent, false)
        return CarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val item = carList[position]
        holder.priceCar.text = item.preco
        holder.bateriaCar.text = item.bateria
        holder.potenciaCar.text = item.potencia
        holder.recargaCar.text = item.recarga

        // Defina o estado inicial do botão de favorito
        holder.favoriteButton.setImageResource(
            if (item.isFavorite) R.drawable.baseline_star_24
            else R.drawable.baseline_star_outline_24
        )

        holder.favoriteButton.setOnClickListener {
            item.isFavorite = !item.isFavorite
            carItemListener(item)

            // Notifique apenas o item que foi alterado
            notifyItemChanged(position)
        }
    }
}