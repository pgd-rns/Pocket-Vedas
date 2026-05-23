package uk.co.dancingganesh.pocketvedas

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Reader screen — mirrors iOS ReaderScreen.swift.
 * Loads content via WebView; swipe navigation is handled by HtmlWebView's
 * native GestureDetector (mirrors iOS WKWebView gesture recogniser approach).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderScreen(
    initialPath: String,
    onNavigateBack: () -> Unit,
    onOpenPath: (String) -> Unit,
    onSearch: () -> Unit,
    onSettings: () -> Unit,
    onAbout: () -> Unit,
    onBookmarks: () -> Unit
) {
    val database = LocalAppDatabase.current
    var siblings by remember { mutableStateOf<List<String>>(emptyList()) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var currentPage by remember { mutableStateOf<ReaderPage?>(null) }
    var showAddBookmark by remember { mutableStateOf(false) }
    var transitionKey by remember { mutableIntStateOf(0) }

    val context = androidx.compose.ui.platform.LocalContext.current
    val prefs = remember { androidx.preference.PreferenceManager.getDefaultSharedPreferences(context) }
    var prefsTrigger by remember { mutableStateOf(0) }

    DisposableEffect(prefs) {
        val listener = android.content.SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key in listOf("pref_text", "pref_synonyms", "pref_translation", "pref_purport", "pref_zoom", "pref_reverse")) {
                prefsTrigger++
            }
        }
        prefs.registerOnSharedPreferenceChangeListener(listener)
        onDispose {
            prefs.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }

    // Load siblings once when initialPath changes
    LaunchedEffect(initialPath) {
        try {
            val result = database.siblingPaths(initialPath)
            if (result.paths.isEmpty()) {
                siblings = listOf(initialPath)
                currentIndex = 0
            } else {
                siblings = result.paths
                currentIndex = result.currentIndex
            }
        } catch (e: Exception) {
            database.lastError = e.message
            siblings = listOf(initialPath)
            currentIndex = 0
        }
    }

    // Load page content whenever currentIndex, siblings, or prefsTrigger changes
    LaunchedEffect(currentIndex, siblings, prefsTrigger) {
        if (siblings.isNotEmpty() && currentIndex in siblings.indices) {
            try {
                currentPage = database.readerPage(siblings[currentIndex])
            } catch (e: Exception) {
                database.lastError = e.message
            }
        } else if (siblings.isEmpty()) {
            try {
                currentPage = database.readerPage(initialPath)
            } catch (e: Exception) {
                database.lastError = e.message
            }
        }
    }

    fun navigateToSibling(offset: Int) {
        val newIndex = currentIndex + offset
        if (newIndex < 0 || newIndex >= siblings.size) return
        currentIndex = newIndex
        transitionKey++
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            PocketTopBar(
                title = currentPage?.title ?: "Reading",
                onSearch = onSearch,
                onSettings = onSettings,
                onAbout = onAbout,
                onBookmarks = onBookmarks,
                onAddBookmark = { showAddBookmark = true },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (currentPage == null) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = AccentGreen
                )
            } else {
                // Swipe navigation is delegated to HtmlWebView's GestureDetector
                // (the only reliable way to intercept touches on an AndroidView).
                AnimatedContent(
                    targetState = transitionKey,
                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                    label = "reader_transition",
                    modifier = Modifier.fillMaxSize()
                ) { key ->
                    @Suppress("UNUSED_EXPRESSION") key
                    currentPage?.let { page ->
                        HtmlWebView(
                            html = page.html,
                            onOpenPath = onOpenPath,
                            onSwipeLeft  = { navigateToSibling(1)  },  // finger left → next
                            onSwipeRight = { navigateToSibling(-1) },  // finger right → prev
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }

    if (showAddBookmark) {
        AddBookmarkDialog(
            path = currentPage?.path ?: initialPath,
            onDismiss = { showAddBookmark = false }
        )
    }
}

@Composable
fun AddBookmarkDialog(path: String, onDismiss: () -> Unit) {
    val database = LocalAppDatabase.current
    var description by remember { mutableStateOf("") }
    var dynamic by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add bookmark") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Bookmark description") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Checkbox(checked = dynamic, onCheckedChange = { dynamic = it })
                    Text("Dynamic")
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                try { database.addBookmark(path, description, dynamic) }
                catch (e: Exception) { database.lastError = e.message }
                onDismiss()
            }) { Text("OK") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
