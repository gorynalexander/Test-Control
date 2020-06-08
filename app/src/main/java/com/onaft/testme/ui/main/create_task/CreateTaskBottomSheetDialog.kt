package com.onaft.testme.ui.main.create_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.onaft.testme.R
import com.onaft.testme.model.Task

class CreateTaskBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var etQuestion: EditText
    private lateinit var btnYes: Button
    private lateinit var btnNo: Button
    private lateinit var btnSave: Button

    private lateinit var database: DatabaseReference

    private var correctAnswer: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_add_task, container, false)
        bindView(view)
        initUI()

        database = Firebase.database.reference
        return view
    }

    private fun bindView(view: View) {
        etQuestion = view.findViewById(R.id.etQuestion)
        btnYes = view.findViewById(R.id.btnYes)
        btnNo = view.findViewById(R.id.btnNo)
        btnSave = view.findViewById(R.id.btnSave)
    }

    private fun initUI() {
        btnYes.setOnClickListener {
            correctAnswer = true
        }
        btnNo.setOnClickListener {
            correctAnswer = false
        }
        btnSave.setOnClickListener {
            etQuestion.text.toString().run {
                if (isNotEmpty()) {
                    writeNewQuestion(this)
                }
            }
        }
    }

    private fun writeNewQuestion(s: String) {
        val question = Task(s, correctAnswer)
        database.child("tasks").push().run {
            setValue(question)
        }
        Toast.makeText(context, "Question successfully sent", Toast.LENGTH_SHORT).show()
        dismiss()
    }
}