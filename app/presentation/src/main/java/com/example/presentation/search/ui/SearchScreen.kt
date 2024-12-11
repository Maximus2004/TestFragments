package com.example.presentation.search.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.presentation.main.ui.MainViewModel
import com.example.presentation.notes.ui.NoteItemContent
import org.koin.compose.koinInject

@Composable
fun SearchScreen(navController: NavController, mainViewModel: MainViewModel) {
    val searchScreenViewModel: SearchScreenViewModel = koinInject()
    val noteList = searchScreenViewModel.noteList.collectAsState()
    var searchText by remember { mutableStateOf("") }
    val authStatus = mainViewModel.authStatus.collectAsState()

    if (authStatus.value) {
        LazyColumn(
            Modifier
                .padding(horizontal = 10.dp)
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                SearchField(
                    value = searchText,
                    onValueChanged = { searchText = it },
                    trailingIcon = {
                        IconButton(
                            onClick = { searchScreenViewModel.findNoteWithWord(searchText) },
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                            )
                        }
                    },
                    placeholderText = "Поиск"
                )
            }
            items(noteList.value) {
                NoteItemContent(
                    onChangeFavouriteStatus = searchScreenViewModel::updateFavouriteStatus,
                    noteItem = it,
                    modifier = Modifier.fillMaxWidth(),
                    onClickNoteCard = { navController.navigate("detail/${it.id}") },
                    onClickShareButton = {}
                )
            }
        }
    } else {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Сначала авторизуйтесь", style = MaterialTheme.typography.displayLarge)
        }
    }
}

@Composable
fun SearchField(
    value: String,
    placeholderText: String,
    onValueChanged: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable() (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        textStyle = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 50.dp)
            .border(
                shape = RoundedCornerShape(10.dp),
                width = 2.dp,
                brush = SolidColor(MaterialTheme.colorScheme.secondaryContainer)
            ),
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.colors(
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            errorContainerColor = MaterialTheme.colorScheme.background,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(10.dp),
        placeholder = {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight()) {
                Text(
                    text = placeholderText,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                )
            }
        },
        singleLine = true
    )
}