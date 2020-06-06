package com.onaft.testme.ui.main.archive.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onaft.testme.R
import com.onaft.testme.model.Task

class ArchiveAdapter : RecyclerView.Adapter<ArchiveAdapter.ViewHolder>() {
    var tasks: ArrayList<Task> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_archive, parent, false)
            .run {
                ViewHolder(this)
            }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    fun updateTasks(tasks: ArrayList<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQuestion: TextView = itemView.findViewById(R.id.tvQuestion)

        fun bind(task: Task) {
            tvQuestion.text = task.question
        }
    }
}