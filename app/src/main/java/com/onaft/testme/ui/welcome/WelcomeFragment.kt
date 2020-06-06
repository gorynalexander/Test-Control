package com.onaft.testme.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.onaft.testme.R

class WelcomeFragment : Fragment() {

    private lateinit var viewModel: WelcomeViewModel
    private lateinit var btnSignIn: MaterialButton
    private lateinit var btnSignUp: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        bindViews(view)
        initUI()
        return view
    }

    private fun initUI() {
        btnSignUp.setOnClickListener {
            // go to register
            findNavController().navigate(R.id.fragment_register)
        }

        btnSignIn.setOnClickListener {
            // go to main fragment
            findNavController().navigate(R.id.fragment_lesson)
        }
    }

    private fun bindViews(view: View) {
        btnSignIn = view.findViewById(R.id.btnSignIn)
        btnSignUp = view.findViewById(R.id.btnSignUp)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WelcomeViewModel::class.java)
    }
}