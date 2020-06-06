package com.onaft.testme.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.onaft.testme.R
import com.onaft.testme.extentions.Extentions.visibleOrGone

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setupNavigation()
    }

    private fun initView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation)
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragment_register,
                R.id.fragment_welcome -> showBottomView(false)
                R.id.fragment_archive,
                R.id.fragment_lesson,
                R.id.fragment_profile -> showBottomView(true)
            }
        }

    }

    private fun showBottomView(visibleOrGone: Boolean) {
        bottomNavigationView.visibleOrGone(visibleOrGone)
    }
}