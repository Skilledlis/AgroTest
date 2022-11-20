package com.skilled.agrotest.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.skilled.agrotest.R

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels(){
        MainViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.haveUser.observe(this) { haveUser ->
            val hostFragment = findNavController(R.id.nav_host_fragment)
            if (haveUser)
                hostFragment.setGraph(R.navigation.main_graph)
            else
                hostFragment.setGraph(R.navigation.registration_graph)
        }
    }
}