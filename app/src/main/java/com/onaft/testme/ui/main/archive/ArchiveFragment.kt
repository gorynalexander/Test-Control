package com.onaft.testme.ui.main.archive

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onaft.testme.R
import com.onaft.testme.extentions.Extentions.visibleOrGone
import com.onaft.testme.model.Task
import com.onaft.testme.ui.main.archive.adapter.ArchiveAdapter
import java.util.ArrayList

class ArchiveFragment : Fragment() {


    private var archiveAdapter: ArchiveAdapter? = null

    private lateinit var viewModel: ArchiveViewModel

    // ui
    private lateinit var rvArchive: RecyclerView
    private lateinit var lEmpty: ViewGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_archive, container, false)
        bindView(view)
        initUI()
        return view
    }

    private fun initUI() {
        rvArchive.visibleOrGone(true)
        lEmpty.visibleOrGone(false)

        val linearLayoutManager = LinearLayoutManager(context)
        archiveAdapter = ArchiveAdapter()

        rvArchive.layoutManager = linearLayoutManager
        rvArchive.adapter = archiveAdapter

        archiveAdapter?.updateTasks(getOldTasks())

    }

    private fun getOldTasks(): ArrayList<Task> {
        return arrayListOf(Task("Is 1024 kilobytes equal to 1 Megabyte?", true))
    }

    private fun bindView(view: View) {
        rvArchive = view.findViewById(R.id.rvArchive)
        lEmpty = view.findViewById(R.id.lEmpty)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ArchiveViewModel::class.java)
        // TODO: Use the ViewModel

        //Is 1024 kilobytes equal to 1 Megabyte?
    }

}