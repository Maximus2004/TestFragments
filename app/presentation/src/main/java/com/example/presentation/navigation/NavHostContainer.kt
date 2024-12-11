package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.main.ui.MainScreenInterface
import com.example.presentation.main.ui.MainViewModel
import com.example.presentation.notedetails.ui.NoteDetails
import com.example.presentation.notes.models.NoteItem
import com.example.presentation.notes.ui.NotesScreen
import com.example.presentation.profile.ui.ProfileScreen
import com.example.presentation.search.ui.SearchScreen
import com.google.gson.Gson

@Composable
fun NavHostContainer(
    navController: NavHostController,
    callback: MainScreenInterface,
    viewModel: MainViewModel
) {
    NavHost(navController, "home") {
        composable("home") { NotesScreen(navController, callback, viewModel) }
        composable("search") { SearchScreen(navController, viewModel) }
        composable("profile") { ProfileScreen(viewModel) }
        composable("detail/{note}") { backStackEntry ->
            val note = backStackEntry.arguments?.getString("note") ?: ""
            NoteDetails(
                note = Gson().fromJson(note, NoteItem::class.java),
                onBackButtonClick = { navController.popBackStack() })
        }
    }
}