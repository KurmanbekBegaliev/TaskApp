package com.example.taskapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.ui.home.new_task.NewTaskFragment
import com.example.taskapp.ui.home.new_task.TaskAdapter
import com.example.taskapp.ui.home.new_task.TaskModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val taskAdapter = TaskAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initListeners()
    }

    private fun initViews() {
        binding.rvNote.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }
    }

    private fun initListeners() {
        setFragmentResultListener(
            NewTaskFragment.NEW_TASK_RESULT_KEY,
        ) { _ , data ->
            val title = data.getString(NewTaskFragment.NEW_TASK_TITLE_KEY)
            val description = data.getString(NewTaskFragment.NEW_TASK_DESC_KEY)

            if (title != null) {
                val taskModel = TaskModel(title, description ?: "")
                taskAdapter.add(taskModel)
            }
        }


        binding.btnFab.setOnClickListener {
            findNavController().navigate(R.id.newTaskFragment2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}