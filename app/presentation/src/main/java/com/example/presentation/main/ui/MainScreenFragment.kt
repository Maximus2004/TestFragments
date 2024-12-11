package com.example.presentation.main.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.compose.rememberNavController
import com.example.presentation.navigation.NavHostContainer
import com.example.presentation.theme.TestFragmentsTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment() {
    private lateinit var callback: MainScreenInterface
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callback = activity as MainScreenInterface
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        val viewModel: MainViewModel by viewModel()

        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            TestFragmentsTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = {
                    BottomAppBar(
                        actions = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(horizontal = 20.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(onClick = { navController.navigate("home") }) {
                                    Icon(
                                        imageVector = Icons.Filled.Home,
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp)
                                    )
                                }
                                Spacer(Modifier.weight(1f))
                                IconButton(onClick = { navController.navigate("search") }) {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp)
                                    )
                                }
                                Spacer(Modifier.weight(1f))
                                IconButton(onClick = { navController.navigate("profile") }) {
                                    Icon(
                                        imageVector = Icons.Filled.Person,
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp)
                                    )
                                }
                            }
                        },
                        tonalElevation = 10.dp,
                        containerColor = Color.White
                    )
                }) {
                    NavHostContainer(
                        navController = navController,
                        callback = callback,
                        viewModel = viewModel
                    )
                }
            }
        }
    }

    fun getMainConverterFragment() = this
}

interface MainScreenInterface {
    fun toNoteCreation()
}