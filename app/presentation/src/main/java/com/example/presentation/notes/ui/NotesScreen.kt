package com.example.presentation.notes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.models.NoteItem
import com.example.domain.models.mockNoteItems
import com.example.testapplication.ui.theme.TestFragmentsTheme

@Composable
fun NotesScreen() {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(mockNoteItems) {
            NoteItemContent(it)
        }
    }
}

@Composable
fun NoteItemContent(noteItem: NoteItem) {
    Box(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
        Text(text = "Записка номер ${noteItem.number}")
    }
}

@Preview(showSystemUi = true)
@Composable
fun NotesScreenPreview() {
    TestFragmentsTheme {
        NotesScreen()
    }
}
