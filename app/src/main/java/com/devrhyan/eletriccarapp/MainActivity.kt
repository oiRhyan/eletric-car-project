package com.devrhyan.eletriccarapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.devrhyan.eletriccarapp.databinding.ActivityMainBinding
import com.devrhyan.eletriccarapp.fragments.CarFragment
import com.devrhyan.eletriccarapp.fragments.FavoriteCarsFragment
import com.devrhyan.eletriccarapp.models.Car
import com.devrhyan.eletriccarapp.models.adapters.CarAdapter
import com.devrhyan.eletriccarapp.models.adapters.CarFragmentAdapter
import com.devrhyan.eletriccarapp.ui.MethodActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val adapter = CarFragmentAdapter(this)

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

        binding.viewCarAdapter.adapter = adapter
        binding.viewCarAdapter.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.ivMainFragment.imageTintList = when (position) {
                    0 -> getColorStateList(R.color.greeenApp)
                    else -> getColorStateList(R.color.white)
                }
                binding.ivFavoriteFragment.imageTintList = when(position) {
                    1 -> getColorStateList(R.color.greeenApp)
                    else -> getColorStateList(R.color.white)
                }
            }
        })

        binding.ivCalculate.setOnClickListener {
            startActivity(Intent(this, MethodActivity::class.java))
        }

        binding.ivMainFragment.setOnClickListener {
            binding.viewCarAdapter.currentItem = 0;
        }

        binding.ivFavoriteFragment.setOnClickListener {
            binding.viewCarAdapter.currentItem = 1;
        }

    }

}