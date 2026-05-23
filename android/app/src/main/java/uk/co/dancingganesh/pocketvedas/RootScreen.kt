package uk.co.dancingganesh.pocketvedas

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*

/**
 * Root screen with bottom navigation — mirrors iOS RootView's TabView.
 * Three tabs: Books, Search, Bookmarks.
 */

sealed class BottomTab(
    val route: String,
    val labelRes: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    data object Books : BottomTab("books", R.string.books, Icons.Filled.LibraryBooks, Icons.Outlined.LibraryBooks)
    data object Search : BottomTab("search", R.string.search, Icons.Filled.Search, Icons.Outlined.Search)
    data object Bookmarks : BottomTab("bookmarks", R.string.bookmarks, Icons.Filled.Bookmark, Icons.Outlined.BookmarkBorder)
}

private val tabs = listOf(BottomTab.Books, BottomTab.Search, BottomTab.Bookmarks)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(initialDeepLink: String? = null) {
    val navController = rememberNavController()
    var showSettings by remember { mutableStateOf(false) }
    var showAbout by remember { mutableStateOf(false) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Determine if we're at a top-level tab (show bottom bar) or a deeper screen
    val isTopLevel = tabs.any { it.route == currentRoute }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .parchmentBackground()
    ) {
        Scaffold(
            // Only show bottom bar at top-level; when hidden it takes zero space
            bottomBar = {
                if (isTopLevel) {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        // Consume system gesture insets so the bar sits flush at screen bottom
                        windowInsets = NavigationBarDefaults.windowInsets
                    ) {
                        tabs.forEach { tab ->
                            val selected = currentRoute == tab.route
                            NavigationBarItem(
                                selected = selected,
                                onClick = {
                                    navController.navigate(tab.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        if (selected) tab.selectedIcon else tab.unselectedIcon,
                                        contentDescription = stringResource(tab.labelRes)
                                    )
                                },
                                label = { Text(stringResource(tab.labelRes)) },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                                )
                            )
                        }
                    }
                }
            },
            // Always include status-bar top inset so the header is never hidden behind it.
            // The NavigationBar handles its own bottom inset when visible.
            contentWindowInsets = WindowInsets.statusBars,
            containerColor = Color.Transparent
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = BottomTab.Books.route,
                ) {
                    composable(BottomTab.Books.route) {
                        BooksScreen(
                            onBookClick = { bookName ->
                                val encoded = java.net.URLEncoder.encode("$bookName/index", "UTF-8")
                                navController.navigate("reader/$encoded")
                            },
                            onSearch = {
                                navController.navigate(BottomTab.Search.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            onSettings = { showSettings = true },
                            onAbout = { showAbout = true },
                            onBookmarks = {
                                navController.navigate(BottomTab.Bookmarks.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }

                    composable(BottomTab.Search.route) {
                        SearchScreen(
                            onResultClick = { path ->
                                val encoded = java.net.URLEncoder.encode(path, "UTF-8")
                                navController.navigate("reader/$encoded")
                            },
                            onSettings = { showSettings = true },
                            onAbout = { showAbout = true },
                            onBookmarks = {
                                navController.navigate(BottomTab.Bookmarks.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }

                    composable(BottomTab.Bookmarks.route) {
                        BookmarksScreen(
                            onBookmarkClick = { path ->
                                val encoded = java.net.URLEncoder.encode(path, "UTF-8")
                                navController.navigate("reader/$encoded")
                            },
                            onSettings = { showSettings = true },
                            onAbout = { showAbout = true }
                        )
                    }

                    composable("reader/{path}") { backStackEntry ->
                        val encodedPath = backStackEntry.arguments?.getString("path") ?: return@composable
                        val path = java.net.URLDecoder.decode(encodedPath, "UTF-8")
                        ReaderScreen(
                            initialPath = path,
                            onNavigateBack = { navController.popBackStack() },
                            onOpenPath = { newPath ->
                                val encoded = java.net.URLEncoder.encode(newPath, "UTF-8")
                                navController.navigate("reader/$encoded")
                            },
                            onSearch = {
                                navController.navigate(BottomTab.Search.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            onSettings = { showSettings = true },
                            onAbout = { showAbout = true },
                            onBookmarks = {
                                navController.navigate(BottomTab.Bookmarks.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    // Settings sheet
    if (showSettings) {
        SettingsSheet(onDismiss = { showSettings = false })
    }

    // About sheet
    if (showAbout) {
        AboutSheet(onDismiss = { showAbout = false })
    }

    // Handle deep links on first composition
    LaunchedEffect(initialDeepLink) {
        initialDeepLink?.let { path ->
            val encoded = java.net.URLEncoder.encode(path, "UTF-8")
            navController.navigate("reader/$encoded")
        }
    }
}

// ── Parchment gradient background modifier (matching iOS) ──────────────────────

@Composable
fun Modifier.parchmentBackground(): Modifier {
    val blackOnWhite = LocalBlackOnWhite.current
    return if (blackOnWhite) {
        this.background(
            Brush.linearGradient(
                colors = listOf(
                    Color(0xFFFCF2E6),
                    Color(0xFFF5E6D0)
                )
            )
        )
    } else {
        this.background(Color.Black)
    }
}
