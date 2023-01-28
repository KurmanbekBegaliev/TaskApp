package com.example.taskapp.ui.home.new_task

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskapp.databinding.FragmentNewTaskBinding
import com.example.taskapp.ui.profile.ProfileFragment

class NewTaskFragment : Fragment() {

    private var _binding: FragmentNewTaskBinding? = null

    private val binding get() = _binding!!
    private val imagePicker: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent()) { imageUri: Uri? ->
        if (imageUri != null) {
            binding.ivPicture.setImageURI(imageUri)
            uriImage = imageUri
        }
    }

    private var uriImage: Uri? = null

    companion object {
        const val NEW_TASK_RESULT_KEY = "result_key"
        const val NEW_TASK_TITLE_KEY = "title_key"
        const val NEW_TASK_DESC_KEY = "desc_key"
        const val NEW_TASK_URI_IMAGE_KEY = "uri key"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {

        binding.ivPicture.setOnClickListener {
            imagePicker.launch(ProfileFragment.MIMETYPE_IMAGES)
        }

        binding.btnSave.setOnClickListener {
            val bundle = bundleOf(
                NEW_TASK_TITLE_KEY to binding.edtTitle.text.toString(),
                NEW_TASK_DESC_KEY to binding.edtDescription.text.toString()
            )
            if (uriImage != null) {
                bundle.putString(NEW_TASK_URI_IMAGE_KEY, uriImage.toString())
            }
            setFragmentResult(
                NEW_TASK_RESULT_KEY,
                bundle
            )
            findNavController().navigateUp()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}