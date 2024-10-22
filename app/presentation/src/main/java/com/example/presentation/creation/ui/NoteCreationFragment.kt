package com.example.presentation.creation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentNoteCreationBinding

class NoteCreationFragment : Fragment(R.layout.fragment_note_creation) {
    private lateinit var callback: NoteCreationInterface
    private lateinit var binding: FragmentNoteCreationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteCreationBinding.inflate(layoutInflater)
        callback = activity as NoteCreationInterface
        binding.deleteButton.setOnClickListener {
            callback.onClickAnyButton()
        }
        binding.readyButton.setOnClickListener {
            callback.onClickAnyButton()
        }
        return binding.root
    }

    fun getNoteCreationFragment() = this
}

interface NoteCreationInterface {
    fun onClickAnyButton()
}