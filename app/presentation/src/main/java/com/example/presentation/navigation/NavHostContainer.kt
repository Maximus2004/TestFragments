package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.notes.ui.NotesScreen
import com.example.presentation.profile.ui.ProfileScreen
import com.example.presentation.search.ui.SearchScreen

@Composable
fun NavHostContainer(
    navController: NavHostController,
    onClickAddNewNote: () -> Unit
) {
    NavHost(navController, "home") {
        composable("home") { NotesScreen(onClickAddNewNote) }
        composable("search") { SearchScreen() }
        composable("profile") { ProfileScreen() }
    }
}