package com.example.presentation.creation.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.data.model.CategoryEntity
import com.example.presentation.R

class CategoryAdapter(
    private val context: Context,
    private val categories: List<CategoryEntity>
) : ArrayAdapter<CategoryEntity>(context, 0, categories) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.spinner_item, parent, false)

        val category = getItem(position) ?: return view

        val colorSquare = view.findViewById<View>(R.id.color_square)
        val categoryName = view.findViewById<TextView>(R.id.tv_category_name)

        colorSquare.setBackgroundColor(category.color)
        categoryName.text = category.name

        return view
    }
}