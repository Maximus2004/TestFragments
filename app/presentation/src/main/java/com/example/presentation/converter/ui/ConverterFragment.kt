package com.example.presentation.converter.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.domain.actions.ConverterActions
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

        val notificationBuilder = NotificationCompat.Builder(requireContext(), "notification_service_channel")
        notificationBuilder
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC) /** Make the transport controls visible on the lockscreen **/
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Title")
            .setContentText("Notification text")

        val notification = notificationBuilder.build()

        val notificationManager = requireContext().getSystemService(NOTIFICATION_SERVICE) as NotificationManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager?.createNotificationChannel(createNotificationChannelQ())
        }
        notificationManager!!.notify(1, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannelQ(): NotificationChannel {
        val name = "Notification Service Channel"
        val descriptionText = "Channel for service notifications"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        return NotificationChannel("notification_service_channel", name, importance).apply {
            description = descriptionText
        }
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

    fun getConverterFragment(translate: String) = ConverterFragment().apply {
        arguments = bundleOf("translate" to translate)
    }
}