package com.onaft.testme.ui.main.lesson

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.onaft.testme.AppData
import com.onaft.testme.FragmentIntentIntegrator
import com.onaft.testme.R
import com.onaft.testme.extentions.Extentions.visibleOrGone
import com.onaft.testme.model.Task
import com.onaft.testme.ui.dialogs.create_task.CreateTaskBottomSheetDialog
import com.onaft.testme.ui.dialogs.qr.QRCodeDialog
import com.onaft.testme.ui.main.lesson.adapter.TasksAdapter


class LessonFragment : Fragment() {


    private lateinit var viewModel: LessonViewModel

    private lateinit var rvTasks: RecyclerView
    private lateinit var lJoin: ViewGroup
    private lateinit var fbCreate: FloatingActionButton
    private lateinit var btnJoin: Button
    private lateinit var btnCreateLesson: Button
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
        btnJoin = view.findViewById(R.id.btnJoin)
        btnCreateLesson = view.findViewById(R.id.btnCreateLesson)
    }

    private fun initUI() {
        //todo
        lJoin.visibleOrGone(true)
        rvTasks.visibleOrGone(false)
        fbCreate.visibleOrGone(!AppData.isStudent)

        val layoutManager = LinearLayoutManager(context)
        tasksAdapter = TasksAdapter()
        rvTasks.apply {
            this.layoutManager = layoutManager
            this.adapter = tasksAdapter
        }
//        tasksAdapter?.updateTasks(getMockTaskList())

        fbCreate.setOnClickListener {
            activity?.let {
                CreateTaskBottomSheetDialog().run {
                    show(it.supportFragmentManager, tag)
                }
            }

        }

        btnJoin.setOnClickListener {
            FragmentIntentIntegrator(this).apply {
                setBeepEnabled(false)
                setOrientationLocked(false)
                setBarcodeImageEnabled(true)
                initiateScan()
            }
        }

        btnCreateLesson.setOnClickListener {
            activity?.let {
                QRCodeDialog().run {
                    show(it.supportFragmentManager, tag)
                }
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LessonViewModel::class.java)
    }

    private fun listenFirebaseUpdates() {

        database.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(snapshot: DataSnapshot, p1: String?) {
                val task = snapshot.getValue(Task::class.java)
                task?.let {
                    tasksAdapter?.updateTask(it)
                }
            }

            override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
                val task = snapshot.getValue(Task::class.java)
                task?.let {
                    tasksAdapter?.addTask(it)
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                val task = snapshot.getValue(Task::class.java)
                task?.let {
                    tasksAdapter?.removeTask(it)
                }
            }
        })
    }

}