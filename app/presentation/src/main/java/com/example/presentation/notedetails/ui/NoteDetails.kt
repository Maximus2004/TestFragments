package com.example.presentation.notedetails.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.notes.models.NoteItem
import com.example.presentation.theme.TestFragmentsTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoteDetails(note: NoteItem?, onBackButtonClick: () -> Unit) {
    val viewModel: NoteDetailsViewModel = koinViewModel()
    LaunchedEffect(Unit) {
        viewModel.updateLastOpenTimestamp(note?.id ?: 0)
    }

    Scaffold(
        topBar = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { onBackButtonClick() }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        modifier = Modifier.size(60.dp),
                        contentDescription = null
                    )
                }
            }
        }
    ) { padding ->
        Column(
            Modifier
                .padding(horizontal = 10.dp)
                .padding(padding)
        ) {

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = note?.title ?: "Загрузка...",
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(note?.categoryColor ?: Color(0xFFFFFFFF))
                        .size(30.dp)
                )
                Text(
                    text = note?.categoryText ?: "Загрузка...",
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Text(
                text = note?.noteText ?: "Загрузка...",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteDetailsPreview() {
    TestFragmentsTheme {
        NoteDetails(
            NoteItem(
                categoryText = "",
                categoryColor = Color(0xFFFF0000),
                noteText = "",
                title = "",
                isFavourite = false,
                timestamp = 0
            ), {}
        )
    }
}
