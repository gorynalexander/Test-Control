package com.onaft.testme.ui.main.lesson

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.onaft.testme.R
import com.onaft.testme.extentions.Extentions.visibleOrGone
import com.onaft.testme.model.Task
import com.onaft.testme.ui.main.create_task.CreateTaskBottomSheetDialog
import com.onaft.testme.ui.main.lesson.adapter.TasksAdapter
import java.util.ArrayList

class LessonFragment : Fragment() {


    private lateinit var viewModel: LessonViewModel

    private lateinit var rvTasks: RecyclerView
    private lateinit var lJoin: ViewGroup
    private lateinit var fbCreate: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lesson, container, false)
        bindView(view)
        initView()
        return view
    }

    private fun bindView(view: View) {
        rvTasks = view.findViewById(R.id.rvTasks)
        lJoin = view.findViewById(R.id.lJoin)
        fbCreate = view.findViewById(R.id.fbCreate)
    }

    private fun initView() {
        //todo
        lJoin.visibleOrGone(false)
        rvTasks.visibleOrGone(true)

        val layoutManager = LinearLayoutManager(context)
        val adapter = TasksAdapter()
        rvTasks.apply {
            this.layoutManager = layoutManager
            this.adapter = adapter
        }
        adapter.updateTasks(getMockTaskList())

        fbCreate.setOnClickListener {
            activity?.let{
                CreateTaskBottomSheetDialog().run {
                    show(it.supportFragmentManager, tag)
                }
            }

        }
    }

    private fun getMockTaskList(): ArrayList<Task> {
        return arrayListOf(
            Task("Is val variable in Kotlin mutable?", false),
            Task("The best way to create model class is to use 'data class'", true),
            Task("For better memory performance you should use LiveData with viewLifeCycleOwner", true)
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LessonViewModel::class.java)
    }

}