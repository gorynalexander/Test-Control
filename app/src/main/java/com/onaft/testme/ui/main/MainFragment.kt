package com.onaft.testme.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.onaft.testme.R

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        bindViews(view)
        initUI()
        return view
    }

    private fun bindViews(view: View) {
        bottomNavigationView = view.findViewById(R.id.bottom_navigation)

    }

    private fun initUI() {
        activity?.let {

           // bottomNavigationView.setupWithNavController(Navigation.findNavController(it, R.id.nav_main_fragment))
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

}