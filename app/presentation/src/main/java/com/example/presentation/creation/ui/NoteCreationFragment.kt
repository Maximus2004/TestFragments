package com.example.presentation.creation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.data.model.CategoryEntity
import com.example.presentation.R
import com.example.presentation.databinding.FragmentNoteCreationBinding
import com.example.presentation.notes.models.NoteItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteCreationFragment : Fragment(R.layout.fragment_note_creation) {
    private lateinit var callback: NoteCreationInterface
    private lateinit var binding: FragmentNoteCreationBinding
    private val viewModel: NoteCreationViewModel by viewModel()
    private var categories = emptyList<CategoryEntity>()
    private var selectedCategory: CategoryEntity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // init observer
        viewModel.categories
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                categories = it
                binding.spinner.setAdapter(CategoryAdapter(requireContext(), it))
            }
            .launchIn(lifecycleScope)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        callback = activity as NoteCreationInterface
        binding = FragmentNoteCreationBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        // init list of categories
        viewModel.getAllCategories()

        // init spinner
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedCategory = categories[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // init ready button
        binding.readyButton.setOnClickListener {
            val noteItem = NoteItem(
                categoryText = selectedCategory?.name ?: categories.first().name,
                noteText = binding.noteDescriptionEditText.text.toString(),
                categoryColor = Color(selectedCategory?.color ?: categories.first().color),
                title = binding.noteNameEditText.text.toString(),
                isFavourite = false
            )
            viewModel.insertNote(noteItem, selectedCategoryId = selectedCategory?.id ?: 0)
            callback.onClickReadyButton()
        }

        // init add new category button
        binding.addNewCategory.setOnClickListener {
            callback.onClickCategoryCreationButton()
        }
    }

    fun getNoteCreationFragment() = this
}

interface NoteCreationInterface {
    fun onClickReadyButton()
    fun onClickCategoryCreationButton()
}