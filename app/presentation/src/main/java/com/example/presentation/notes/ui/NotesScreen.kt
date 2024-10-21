package com.example.presentation.notes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.models.NoteItem
import com.example.domain.models.mockNoteItems
import com.example.presentation.R
import com.example.presentation.theme.TestFragmentsTheme

@Composable
fun NotesScreen() {
    val scrollState = rememberScrollState()
    Column(Modifier.verticalScroll(scrollState)) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                .background(MaterialTheme.colorScheme.surface)
                .wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {
            Row {
                Icon(painter = painterResource(R.drawable.ic_last_notes), contentDescription = null)
                Text(text = "Последние", style = MaterialTheme.typography.labelMedium)
            }
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(mockNoteItems) {
                NoteItemContent(it)
            }
        }
    }
}

@Composable
fun NoteItemContent(noteItem: NoteItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
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
