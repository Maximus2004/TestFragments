package com.example.presentation.converter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.domain.models.ConverterActions
import com.example.presentation.R
import com.example.presentation.databinding.FragmentConverterBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ConverterFragment : Fragment(R.layout.fragment_converter) {

    private lateinit var binding: FragmentConverterBinding
    private val viewModel: ConverterViewModel by viewModel {
        parametersOf(arguments?.getString("translate").orEmpty())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConverterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeViewStateFlow()
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach(::onActionPerformed)
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateLoadingState()
    }

    fun getConverterFragment(translate: String) = ConverterFragment().apply {
        arguments = bundleOf("translate" to translate)
    }

    private fun onActionPerformed(action: ConverterActions) {
        when (action) {
            is ConverterActions.Loading -> binding.loadingText.visibility = VISIBLE
            is ConverterActions.Success ->  {
                binding.converterfragmentText.text = action.result
                binding.loadingText.visibility = GONE
            }
        }
    }
}