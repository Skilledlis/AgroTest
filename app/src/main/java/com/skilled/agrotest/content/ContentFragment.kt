package com.skilled.agrotest.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.skilled.agrotest.R
import com.skilled.agrotest.databinding.FragmentContentBinding
import com.skilled.agrotest.viewBinding.ViewBindingHolder
import com.skilled.agrotest.viewBinding.ViewBindingHolderImpl

class ContentFragment : Fragment(),
    ViewBindingHolder<FragmentContentBinding> by ViewBindingHolderImpl() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = initBinding(FragmentContentBinding.inflate(layoutInflater), this) {

        val navHostFragment = childFragmentManager
            .findFragmentById(R.id.nav_content_fragment) as NavHostFragment

        bottomNavView.setupWithNavController(navHostFragment.navController)
    }

}
