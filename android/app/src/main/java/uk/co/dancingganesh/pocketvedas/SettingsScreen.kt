package uk.co.dancingganesh.pocketvedas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.preference.PreferenceManager

/**
 * Settings bottom sheet — mirrors iOS SettingsScreen.
 * Reads/writes the same SharedPreferences keys used by the old Android app.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsSheet(onDismiss: () -> Unit) {
    val context = LocalContext.current
    val prefs = remember { PreferenceManager.getDefaultSharedPreferences(context) }

    var showText by remember { mutableStateOf(prefs.getBoolean("pref_text", true)) }
    var showSynonyms by remember { mutableStateOf(prefs.getBoolean("pref_synonyms", true)) }
    var showTranslation by remember { mutableStateOf(prefs.getBoolean("pref_translation", true)) }
    var showPurport by remember { mutableStateOf(prefs.getBoolean("pref_purport", true)) }
    var textSize by remember { mutableIntStateOf(prefs.getInt("pref_zoom", 133)) }
    var blackOnWhite by remember { mutableStateOf(prefs.getBoolean("pref_reverse", true)) }
    var keepAwake by remember { mutableStateOf(prefs.getBoolean("pref_keep_awake", false)) }

    fun save() {
        prefs.edit()
            .putBoolean("pref_text", showText)
            .putBoolean("pref_synonyms", showSynonyms)
            .putBoolean("pref_translation", showTranslation)
            .putBoolean("pref_purport", showPurport)
            .putInt("pref_zoom", textSize)
            .putBoolean("pref_reverse", blackOnWhite)
            .putBoolean("pref_keep_awake", keepAwake)
            .apply()
    }

    ModalBottomSheet(
        onDismissRequest = {
            save()
            onDismiss()
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
                .padding(bottom = 32.dp)
        ) {
            Text(
                "Settings",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Content section
            Text(
                "Content",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            SettingsToggle("Display Text", showText) {
                showText = it; save()
            }
            SettingsToggle("Display Synonyms", showSynonyms) {
                showSynonyms = it; save()
            }
            SettingsToggle("Display Translation", showTranslation) {
                showTranslation = it; save()
            }
            SettingsToggle("Display Purport", showPurport) {
                showPurport = it; save()
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Display section
            Text(
                "Display",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Text size slider
            Text("Text size ${textSize}%", style = MaterialTheme.typography.bodyMedium)
            Slider(
                value = textSize.toFloat(),
                onValueChange = { textSize = it.toInt() },
                onValueChangeFinished = { save() },
                valueRange = 50f..300f,
                steps = 49,
                colors = SliderDefaults.colors(
                    thumbColor = AccentGreen,
                    activeTrackColor = AccentGreen
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            SettingsToggle("Black on White", blackOnWhite) {
                blackOnWhite = it; save()
            }
            SettingsToggle("Keep awake", keepAwake) {
                keepAwake = it; save()
            }
        }
    }
}

@Composable
private fun SettingsToggle(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = AccentGreen,
                checkedTrackColor = AccentGreen.copy(alpha = 0.4f)
            )
        )
    }
}

/**
 * About bottom sheet — mirrors iOS StaticHTMLScreen for "About".
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutSheet(onDismiss: () -> Unit) {
    val database = LocalAppDatabase.current
    val html = remember { database.aboutHtml() }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 400.dp)
                .padding(bottom = 32.dp)
        ) {
            Text(
                "About",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )
            HtmlWebView(
                html = html,
                onOpenPath = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}
