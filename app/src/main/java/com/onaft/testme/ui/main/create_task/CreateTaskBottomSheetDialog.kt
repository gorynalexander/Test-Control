package com.onaft.testme.ui.main.create_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.onaft.testme.R

class CreateTaskBottomSheetDialog : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_add_task, container, false)
        bindView(view)
        initUI()
        return view
    }

    private fun bindView(view: View) {

    }

    private fun initUI() {

    }
}