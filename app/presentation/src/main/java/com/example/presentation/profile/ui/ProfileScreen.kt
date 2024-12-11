package com.example.presentation.profile.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.data.model.ProfileEntity
import com.example.presentation.R
import com.example.presentation.main.ui.MainViewModel
import com.example.presentation.notes.ui.NoteItemContent
import com.example.presentation.notes.ui.TitleBookmark
import com.example.presentation.search.ui.SearchField
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(mainViewModel: MainViewModel) {
    val profileScope = rememberCoroutineScope()
    val viewModel: ProfileViewModel = koinViewModel()
    val context = LocalContext.current
    val authStatus = mainViewModel.authStatus.collectAsState()
    val sharedNotes = viewModel.sharedNotes.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    if (authStatus.value) {
        Column {
            Spacer(Modifier.height(50.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "------> Вы вошли как $email <------",
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
            Spacer(Modifier.height(50.dp))
            TitleBookmark(
                icon = R.drawable.ic_share,
                title = R.string.share,
                iconTint = Color.Gray
            )
            Spacer(Modifier.height(10.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(start = 10.dp)
            ) {
                items(sharedNotes.value) {
                    NoteItemContent(
                        onClickShareButton = {},
                        onChangeFavouriteStatus = { _, _ -> },
                        noteItem = it,
                        onClickNoteCard = {}
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { mainViewModel.updateAuthStatus(false) },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "------> Выйти <------",
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
            Spacer(Modifier.height(180.dp))
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Авторизация",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(20.dp)
            )
            SearchField(
                value = email,
                onValueChanged = { email = it },
                modifier = Modifier.padding(bottom = 10.dp),
                placeholderText = "Почта"
            )
            SearchField(
                value = password,
                onValueChanged = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.padding(bottom = 20.dp),
                placeholderText = "Пароль"
            )
            OutlinedButton(
                onClick = {
                    viewModel.insertProfile(
                        ProfileEntity(
                            email = email,
                            password = password
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .height(60.dp)
            ) {
                Text(
                    text = "Зарегистрироваться",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Button(
                onClick = {
                    profileScope.launch {
                        viewModel.checkIfExist(email, password).collect {
                            if (it) {
                                mainViewModel.updateAuthStatus(true)
                                Toast.makeText(context, "Вы успешно вошли!", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Неверный логин или пароль",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text(
                    text = "Войти",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White
                )
            }
        }
    }
}