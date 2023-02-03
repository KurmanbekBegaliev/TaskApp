package com.example.taskapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.ui.home.new_task.TaskAdapter
import com.example.taskapp.ui.home.new_task.TaskModel
import com.example.taskapp.utils.MainApplication
import kotlinx.coroutines.launch

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

        lifecycleScope.launch{
            val tasks = MainApplication.appDatabase?.taskDao?.getAll()
            tasks?.forEach{
                val taskModel = TaskModel(
                    title = it.title,
                    description = it.desc,
                    pictureUri = it.pictureUri
                )
                taskAdapter.add(taskModel)
            }
        }
    }

    private fun initListeners() {
        binding.btnFab.setOnClickListener {
            findNavController().navigate(R.id.newTaskFragment2)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }
}