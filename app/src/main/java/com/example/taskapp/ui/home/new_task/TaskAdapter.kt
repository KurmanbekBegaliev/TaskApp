package com.example.taskapp.ui.home.new_task

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskapp.databinding.ItemTaskBinding

class TaskAdapter(private var tasks: MutableList<TaskModel>) :
    Adapter<TaskAdapter.TaskViewHolder>() {

    fun add(task: TaskModel) {
        tasks.add(0, task)
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        ViewHolder(binding.root) {
        fun bind(task: TaskModel) {
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.description
            if (task.pictureUri != null) {
                binding.itemPicture.setImageURI(Uri.parse(task.pictureUri))
                binding.itemPicture.visibility = View.VISIBLE
            }
        }
    }

}

