package uk.co.dancingganesh.pocketvedas

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Books grid screen — mirrors iOS BooksView.
 * Shows book covers in a grid with glassmorphism-style cards.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(
    onBookClick: (String) -> Unit,
    onSearch: () -> Unit,
    onSettings: () -> Unit,
    onAbout: () -> Unit,
    onBookmarks: () -> Unit
) {
    val database = LocalAppDatabase.current
    val books = database.books

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            PocketTopBar(
                title = "PV",
                onSearch = onSearch,
                onSettings = onSettings,
                onAbout = onAbout,
                onBookmarks = onBookmarks
            )
        },
        containerColor = Color.Transparent
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 110.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(books, key = { it.id }) { book ->
                BookCard(book = book, onClick = { onBookClick(book.name) })
            }
        }
    }
}

@Composable
private fun BookCard(book: Book, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.85f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp)
        ) {
            // Book cover
            if (book.coverData != null) {
                val bitmap = remember(book.id) {
                    BitmapFactory.decodeByteArray(book.coverData, 0, book.coverData.size)
                }
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = book.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .aspectRatio(2f / 3f)
                            .clip(RoundedCornerShape(10.dp))
                            .shadow(5.dp, RoundedCornerShape(10.dp))
                    )
                } else {
                    BookPlaceholder(book.name)
                }
            } else {
                BookPlaceholder(book.name)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = book.name,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun BookPlaceholder(name: String) {
    Box(
        modifier = Modifier
            .aspectRatio(2f / 3f)
            .clip(RoundedCornerShape(10.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        WarmOrange.copy(alpha = 0.2f),
                        WarmOrange.copy(alpha = 0.08f)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Filled.MenuBook,
            contentDescription = name,
            tint = WarmOrange.copy(alpha = 0.6f),
            modifier = Modifier.size(40.dp)
        )
    }
}

// ── Reusable compact top bar matching iOS PocketToolbarModifier ─────────────────
// Uses CenterAlignedTopAppBar with zero window insets so the bar stays slim
// (matches iOS navigationBar height). Title is centred with icon on left.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PocketTopBar(
    title: String,
    onSearch: (() -> Unit)? = null,
    onSettings: (() -> Unit)? = null,
    onAbout: (() -> Unit)? = null,
    onBookmarks: (() -> Unit)? = null,
    onAddBookmark: (() -> Unit)? = null,
    navigationIcon: @Composable (() -> Unit)? = null
) {
    var menuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        // Suppress extra top padding — system status bar insets are already
        // consumed by the root Scaffold/enableEdgeToEdge setup.
        windowInsets = WindowInsets(0, 0, 0, 0),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pv_icon),
                    contentDescription = "PV Logo",
                    modifier = Modifier
                        .size(22.dp)
                        .clip(RoundedCornerShape(5.dp))
                )
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = {
            navigationIcon?.invoke()
        },
        actions = {
            if (onSearch != null) {
                IconButton(onClick = onSearch) {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                }
            }

            Box {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "More")
                }
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    if (onAddBookmark != null) {
                        DropdownMenuItem(
                            text = { Text("Add bookmark") },
                            onClick = {
                                menuExpanded = false
                                onAddBookmark()
                            },
                            leadingIcon = { Icon(Icons.Filled.BookmarkAdd, contentDescription = null) }
                        )
                    }
                    if (onSettings != null) {
                        DropdownMenuItem(
                            text = { Text("Settings") },
                            onClick = {
                                menuExpanded = false
                                onSettings()
                            },
                            leadingIcon = { Icon(Icons.Filled.Settings, contentDescription = null) }
                        )
                    }
                    if (onAbout != null) {
                        DropdownMenuItem(
                            text = { Text("About") },
                            onClick = {
                                menuExpanded = false
                                onAbout()
                            },
                            leadingIcon = { Icon(Icons.Filled.Info, contentDescription = null) }
                        )
                    }
                    if (onBookmarks != null) {
                        DropdownMenuItem(
                            text = { Text("Bookmarks") },
                            onClick = {
                                menuExpanded = false
                                onBookmarks()
                            },
                            leadingIcon = { Icon(Icons.Filled.Bookmark, contentDescription = null) }
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}
