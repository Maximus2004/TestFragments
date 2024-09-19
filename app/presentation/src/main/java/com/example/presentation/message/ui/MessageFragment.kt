package com.example.presentation.message.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.presentation.R
import com.example.presentation.databinding.FragmentMessageBinding
import kotlinx.coroutines.flow.launchIn
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageFragment : Fragment(R.layout.fragment_message) {
    private lateinit var callback: MessageFragmentInterface
    private lateinit var binding: FragmentMessageBinding
    private val viewModel: MessageViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observeViewStateFlow()
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .launchIn(viewLifecycleOwner.lifecycleScope)

        callback = activity as MessageFragmentInterface
        binding.messagefragmentTranslateButton.setOnClickListener {
            callback.onClickTranslate(binding.messagefragmentEdit.text.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageBinding.inflate(layoutInflater)
        return binding.root
    }

    fun getMessageFragment() = this
}

interface MessageFragmentInterface {
    fun onClickTranslate(translate: String)
}