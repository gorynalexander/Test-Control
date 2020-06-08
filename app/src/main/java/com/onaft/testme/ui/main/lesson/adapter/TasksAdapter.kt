package com.onaft.testme.ui.main.lesson.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onaft.testme.AppData
import com.onaft.testme.R
import com.onaft.testme.extentions.Extentions.visibleOrGone
import com.onaft.testme.model.Task

class TasksAdapter : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {

    var tasks: ArrayList<Task> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }


    fun addTask(task: Task) {
        tasks.add(task)
        notifyDataSetChanged()
    }

    fun updateTask(task: Task) {
        tasks.forEachIndexed { index, it ->
            if (it.question == task.question) {
                tasks.removeAt(index)
                tasks.add(index, task)
                notifyItemChanged(index)
            }
        }
        notifyDataSetChanged()
    }

    fun removeTask(task: Task) {
        tasks.forEachIndexed { index, it ->
            if (it.question == task.question) {
                tasks.removeAt(index)
                notifyItemRemoved(index)
            }
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQuestion: TextView = itemView.findViewById(R.id.tvQuestion)
        val btnYes: Button = itemView.findViewById(R.id.btnYes)
        val btnNo: Button = itemView.findViewById(R.id.btnNo)
        val btnDontKnow: Button = itemView.findViewById(R.id.btnDontKnow)

        val lDetails: ViewGroup = itemView.findViewById(R.id.lDetails)
        val lAnswers: ViewGroup = itemView.findViewById(R.id.lAnswers)

        fun bind(task: Task) {
            tvQuestion.text = task.question

            lDetails.visibleOrGone(!AppData.isStudent)
            lAnswers.visibleOrGone(AppData.isStudent)
        }
    }

}