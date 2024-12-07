package com.example.presentation.notes.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.main.ui.MainScreenInterface
import com.example.presentation.notes.models.NoteItem
import com.example.presentation.notes.models.mockNoteItems
import com.example.presentation.theme.TestFragmentsTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotesScreen(callback: MainScreenInterface) {
    val notesScreenViewModel: NotesScreenViewModel = koinViewModel()
    val notesList by notesScreenViewModel.getNoteItems().collectAsState(emptyList())
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = stringResource(R.string.your_notes), style = MaterialTheme.typography.labelLarge)
        }
        Spacer(Modifier.height(20.dp))
        TitleBookmark(
            icon = R.drawable.ic_last_notes,
            title = R.string.last_notes,
            iconTint = MaterialTheme.colorScheme.surfaceTint
        )
        Spacer(Modifier.height(10.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(start = 10.dp)
        ) {
            items(notesList) {
                NoteItemContent(notesScreenViewModel::updateFavouriteStatus, it)
            }
            item {
                AddNewNoteItem(callback::toNoteCreation)
            }
        }
        Spacer(Modifier.height(10.dp))

        TitleBookmark(
            icon = R.drawable.ic_favourite,
            title = R.string.favourites,
            iconTint = Color.Red
        )
        Spacer(Modifier.height(10.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(start = 10.dp)
        ) {
            items(notesList) {
                NoteItemContent(notesScreenViewModel::updateFavouriteStatus, it)
            }
            item {
                AddNewNoteItem(callback::toNoteCreation)
            }
        }
        Spacer(Modifier.height(10.dp))

        TitleBookmark(
            icon = R.drawable.ic_all_notes,
            title = R.string.all_notes,
            iconTint = Color.Gray
        )
        Spacer(Modifier.height(10.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(start = 10.dp)
        ) {
            items(notesList) {
                NoteItemContent(notesScreenViewModel::updateFavouriteStatus, it)
            }
            item {
                AddNewNoteItem(callback::toNoteCreation)
            }
        }
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
fun TitleBookmark(
    @DrawableRes
    icon: Int,
    @StringRes
    title: Int,
    iconTint: Color
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
            .background(MaterialTheme.colorScheme.surface)
            .wrapContentSize()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = iconTint
        )
        Spacer(Modifier.width(15.dp))
        Text(text = stringResource(title), style = MaterialTheme.typography.labelMedium)
    }
}

@Composable
fun AddNewNoteItem(onClickAddNewNote: () -> Unit) {
    Box(
        modifier = Modifier
            .height(150.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = onClickAddNewNote) {
                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
fun NoteItemContent(
    onChangeFavouriteStatus: (Int, Boolean) -> Unit,
    noteItem: NoteItem,
    modifier: Modifier = Modifier
) {
    var isFavourite by remember { mutableStateOf(noteItem.isFavourite) }

    Box(
        modifier = modifier
            .height(150.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
        ) {
            Column(Modifier.padding(10.dp)) {
                Row {
                    Box(
                        Modifier
                            .size(24.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(noteItem.categoryColor)
                    )
                    Text(
                        text = noteItem.categoryText,
                        color = noteItem.categoryColor,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 5.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = noteItem.title,
                        style = MaterialTheme.typography.labelLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row() {
                    Icon(
                        painter = painterResource(R.drawable.ic_share),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Gray
                    )
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = {
                        isFavourite = !isFavourite
                        onChangeFavouriteStatus(noteItem.id ?: 0, !isFavourite)
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_favourite),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = if (isFavourite) Color.Red else Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun NotesScreenPreview() {
    TestFragmentsTheme {
        NoteItemContent(noteItem = mockNoteItems[0], onChangeFavouriteStatus = { _, _ -> })
    }
}
