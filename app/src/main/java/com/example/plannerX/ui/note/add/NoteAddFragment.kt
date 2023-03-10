package com.example.plannerX.ui.note.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.plannerX.R
import com.example.plannerX.common.DateHelper
import com.example.plannerX.common.makeToast
import com.example.plannerX.data.entities.Note
import com.example.plannerX.databinding.FragmentNoteAddBinding
import com.example.plannerX.ui.viewmodels.NoteViewModel
import com.example.plannerX.ui.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteAddFragment : Fragment() {
    private var _binding: FragmentNoteAddBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel: NoteViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }

        setupMenu()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupMenu() {
        binding.apply {
            toolbarNoteAdd.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            toolbarNoteAdd.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.note_add_save -> {
                        insertNote()
                        true
                    }
                    else -> {
                        super.onOptionsItemSelected(item)
                    }
                }
            }
        }
    }

    private fun insertNote() {
        binding.apply {
            val mTitle = edtNoteUpdateTitle.text.toString()
            val mContent = edtNoteUpdateContent.text.toString()
            val validation = sharedViewModel.validationNoteForm(mTitle, mContent)

            if (validation) {
                val note = Note(
                    0,
                    mTitle,
                    mContent,
                    DateHelper.getCurrentDate(),
                    sharedViewModel.priority.value!!
                )
                noteViewModel.insertData(note)
                findNavController().popBackStack()
                makeToast(requireContext(), getString(R.string.text_success_added))
            } else {
                makeToast(requireContext(), getString(R.string.text_message_retry))
            }
        }
    }
}