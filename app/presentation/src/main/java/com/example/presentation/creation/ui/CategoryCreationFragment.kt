package com.example.presentation.creation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.fragment.app.Fragment
import com.example.data.model.CategoryEntity
import com.example.presentation.R
import com.example.presentation.databinding.FragmentAddCategoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import yuku.ambilwarna.AmbilWarnaDialog

class CategoryCreationFragment : Fragment(R.layout.fragment_add_category) {
    private val viewModel: NoteCreationViewModel by viewModel()
    private lateinit var binding: FragmentAddCategoryBinding
    private lateinit var callback: CategoryCreationInterface
    private var selectedColor = Color(0xFFFF0000).toArgb()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        callback = activity as CategoryCreationInterface
        binding = FragmentAddCategoryBinding.inflate(layoutInflater)

        // init color picker
        binding.btnColorPicker.setOnClickListener {
            val colorPicker = AmbilWarnaDialog(
                requireContext(),
                selectedColor,
                object : AmbilWarnaDialog.OnAmbilWarnaListener {
                    override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                        selectedColor = color
                        binding.colorPreview.setBackgroundColor(color)
                    }

                    override fun onCancel(dialog: AmbilWarnaDialog) {}
                }
            )
            colorPicker.show()
        }

        // init creation button and send category info
        binding.createCategoryButton.setOnClickListener {
            viewModel.insertNewCategory(
                CategoryEntity(
                    name = binding.nameOfCategory.text.toString(),
                    color = selectedColor
                )
            )
            callback.toBackFragment()
        }

        return binding.root
    }

    fun getCategoryCreationFragment() = this
}

interface CategoryCreationInterface {
    fun toBackFragment()
}