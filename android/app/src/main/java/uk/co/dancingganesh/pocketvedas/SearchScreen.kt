package uk.co.dancingganesh.pocketvedas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

/**
 * Search screen — mirrors iOS SearchScreen.
 * Uses Material3 SearchBar with full-text search over vedabase FTS table.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onResultClick: (String) -> Unit,
    onSettings: () -> Unit,
    onAbout: () -> Unit,
    onBookmarks: () -> Unit
) {
    val database = LocalAppDatabase.current
    var query by remember { mutableStateOf("") }
    var results by remember { mutableStateOf<List<SearchResult>>(emptyList()) }
    var hasSearched by remember { mutableStateOf(false) }

    fun performSearch() {
        if (query.isBlank()) return
        try {
            results = database.search(query)
            hasSearched = true
        } catch (e: Exception) {
            database.lastError = e.message
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            PocketTopBar(
                title = "Search",
                onSettings = onSettings,
                onAbout = onAbout,
                onBookmarks = onBookmarks
            )
        },
        containerColor = Color.Transparent
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Search input
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Search scripture") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AccentGreen,
                    focusedLabelColor = AccentGreen,
                    cursorColor = AccentGreen
                )
            )

            // Search button
            Button(
                onClick = { performSearch() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AccentGreen)
            ) {
                Text("Search")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Results
            if (hasSearched && results.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text(
                        "No matches found",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(results, key = { it.id }) { result ->
                        ListItem(
                            headlineContent = {
                                Text(
                                    result.title,
                                    fontWeight = FontWeight.SemiBold
                                )
                            },
                            supportingContent = {
                                Text(
                                    text = buildSnippetAnnotatedString(result.snippet),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 2
                                )
                            },
                            modifier = Modifier.clickable {
                                onResultClick(result.path)
                            },
                            colors = ListItemDefaults.colors(
                                containerColor = Color.Transparent
                            )
                        )
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.outlineVariant,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Parses a snippet string containing <mark>…</mark> tags and returns an
 * AnnotatedString where the matched keyword is highlighted with a yellow
 * background and bold weight — instead of showing plain text or "0" prefix.
 */
fun buildSnippetAnnotatedString(snippet: String): AnnotatedString = buildAnnotatedString {
    var remaining = snippet
    while (remaining.isNotEmpty()) {
        val start = remaining.indexOf("<mark>")
        if (start == -1) {
            // No more marks — append the rest as plain text
            append(remaining)
            break
        }
        // Append text before the mark
        if (start > 0) append(remaining.substring(0, start))
        val end = remaining.indexOf("</mark>", start)
        if (end == -1) {
            // Malformed: no closing tag, treat rest as plain
            append(remaining.substring(start + "<mark>".length))
            break
        }
        // Append the highlighted keyword
        val keyword = remaining.substring(start + "<mark>".length, end)
        withStyle(
            SpanStyle(
                background = Color(0xFFFFEF8A),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A1A)
            )
        ) {
            append(keyword)
        }
        remaining = remaining.substring(end + "</mark>".length)
    }
}
