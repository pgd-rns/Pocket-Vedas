package uk.co.dancingganesh.pocketvedas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Bookmarks screen — mirrors iOS BookmarksView.
 * Shows saved bookmarks with swipe-to-delete.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(
    onBookmarkClick: (String) -> Unit,
    onSettings: () -> Unit,
    onAbout: () -> Unit
) {
    val database = LocalAppDatabase.current
    // Refresh bookmarks each time the screen is shown
    var bookmarks by remember { mutableStateOf(database.bookmarks) }

    LaunchedEffect(Unit) {
        database.refreshBookmarks()
        bookmarks = database.bookmarks
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            PocketTopBar(
                title = "Bookmarks",
                onSettings = onSettings,
                onAbout = onAbout
            )
        },
        containerColor = Color.Transparent
    ) { padding ->
        if (bookmarks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "No bookmarks yet",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(bookmarks, key = { it.id }) { bookmark ->
                    val dismissState = rememberSwipeToDismissBoxState(
                        confirmValueChange = { value ->
                            if (value == SwipeToDismissBoxValue.EndToStart) {
                                try {
                                    database.removeBookmark(bookmark.id)
                                    bookmarks = database.bookmarks
                                } catch (e: Exception) {
                                    database.lastError = e.message
                                }
                                true
                            } else false
                        }
                    )

                    SwipeToDismissBox(
                        state = dismissState,
                        backgroundContent = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 20.dp),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Icon(
                                    Icons.Filled.Delete,
                                    contentDescription = "Delete",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        enableDismissFromStartToEnd = false,
                        enableDismissFromEndToStart = true
                    ) {
                        ListItem(
                            headlineContent = {
                                Text(
                                    bookmark.name,
                                    fontWeight = FontWeight.SemiBold
                                )
                            },
                            supportingContent = {
                                if (bookmark.description.isNotEmpty()) {
                                    Text(
                                        bookmark.description,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            },
                            modifier = Modifier.clickable {
                                onBookmarkClick(bookmark.path)
                            },
                            colors = ListItemDefaults.colors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        )
                    }

                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}
