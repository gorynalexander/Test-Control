package com.onaft.testme.ui.main.lesson

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.onaft.testme.AppData
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
    private lateinit var database: DatabaseReference
    private var tasksAdapter: TasksAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lesson, container, false)
        bindView(view)
        initUI()
        database = Firebase.database.getReference("/tasks/")

        listenFirebaseUpdates()
        return view
    }

    private fun bindView(view: View) {
        rvTasks = view.findViewById(R.id.rvTasks)
        lJoin = view.findViewById(R.id.lJoin)
        fbCreate = view.findViewById(R.id.fbCreate)
    }

    private fun initUI() {
        //todo
        lJoin.visibleOrGone(false)
        rvTasks.visibleOrGone(true)
        fbCreate.visibleOrGone(!AppData.isStudent)

        val layoutManager = LinearLayoutManager(context)
        tasksAdapter = TasksAdapter()
        rvTasks.apply {
            this.layoutManager = layoutManager
            this.adapter = tasksAdapter
        }
//        tasksAdapter?.updateTasks(getMockTaskList())

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

    private fun listenFirebaseUpdates(){

        database.addChildEventListener(object: ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
                val task = snapshot.getValue(Task::class.java)
                Log.i("TEST_TASK", task.toString())
                task?.let{
                    tasksAdapter?.addTask(it)
                }
            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }
        })
    }

}