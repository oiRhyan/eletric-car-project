package com.devrhyan.eletriccarapp.models.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.devrhyan.eletriccarapp.fragments.CarFragment
import com.devrhyan.eletriccarapp.fragments.FavoriteCarsFragment

class CarFragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CarFragment()
            1 -> FavoriteCarsFragment()
            else -> CarFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}