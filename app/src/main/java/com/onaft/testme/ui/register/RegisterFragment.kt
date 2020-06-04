package com.onaft.testme.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.onaft.testme.R

class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var btnSignIn: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.register_fragment, container, false)
        bindViews(view)
        initUI()
        return view
    }

    private fun initUI() {
        btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.main_graph)
        }
    }

    private fun bindViews(view: View) {
        btnSignIn = view.findViewById(R.id.btnSignUp)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
    }

}