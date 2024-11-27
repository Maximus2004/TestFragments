package com.example.presentation.creation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentNoteCreationBinding
import com.example.presentation.notes.models.NoteItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteCreationFragment : Fragment(R.layout.fragment_note_creation) {
    private lateinit var callback: NoteCreationInterface
    private lateinit var binding: FragmentNoteCreationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: NoteCreationViewModel by viewModel()
        binding = FragmentNoteCreationBinding.inflate(layoutInflater)
        callback = activity as NoteCreationInterface
        binding.deleteButton.setOnClickListener {
            callback.onClickAnyButton()
        }
        binding.readyButton.setOnClickListener {
            val noteItem = NoteItem(
                categoryText = binding.noteNameEditText.text.toString(),
                noteText = binding.noteDescriptionEditText.text.toString(),
                categoryColor = Color(0xFF84937F),
                title = binding.noteNameEditText.text.toString(),
                isFavourite = false
            )
            viewModel.insertNote(noteItem)
            callback.onClickAnyButton()
        }
        val categories = listOf("Жизнь", "Работа")
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            categories
        )
        binding.spinner.setAdapter(adapter)
        return binding.root
    }

    fun getNoteCreationFragment() = this
}

interface NoteCreationInterface {
    fun onClickAnyButton()
}