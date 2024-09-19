package com.example.presentation.blank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentBlankBinding

class BlankFragment : Fragment(R.layout.fragment_blank) {
    lateinit var callback: BlankFragmentInterface
    lateinit var binding: FragmentBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlankBinding.inflate(layoutInflater)
        callback = activity as BlankFragmentInterface
        binding.startfragmentButtonStart.setOnClickListener {
            callback.onClickedButton()
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment BlankFragment.
         */
        @JvmStatic
        fun newInstance() = BlankFragment()
    }
}

interface BlankFragmentInterface {
    fun onClickedButton()
}