package uk.co.dancingganesh.pocketvedas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.ui.platform.LocalContext
import android.content.SharedPreferences

val LocalAppDatabase = staticCompositionLocalOf<AppDatabase> {
    error("AppDatabase not provided")
}

val LocalBlackOnWhite = staticCompositionLocalOf<Boolean> {
    true
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val app = application as PocketVedasApp
        val isReady = mutableStateOf(app.appDatabase != null)

        if (!isReady.value) {
            app.initializeDatabase {
                runOnUiThread {
                    isReady.value = true
                }
            }
        }

        setContent {
            var dbReady by remember { isReady }
            val context = LocalContext.current
            val prefs = remember { androidx.preference.PreferenceManager.getDefaultSharedPreferences(context) }
            var blackOnWhite by remember { mutableStateOf(prefs.getBoolean("pref_reverse", true)) }

            DisposableEffect(prefs) {
                val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
                    if (key == "pref_reverse") {
                        blackOnWhite = prefs.getBoolean("pref_reverse", true)
                    }
                }
                prefs.registerOnSharedPreferenceChangeListener(listener)
                onDispose {
                    prefs.unregisterOnSharedPreferenceChangeListener(listener)
                }
            }

            PocketVedasTheme(darkTheme = !blackOnWhite) {
                if (dbReady) {
                    val db = app.appDatabase!!
                    val errorMsg = db.lastError

                    if (errorMsg != null) {
                        ErrorScreen(error = errorMsg) {
                            // Retry
                            dbReady = false
                            app.initializeDatabase {
                                runOnUiThread {
                                    dbReady = true
                                }
                            }
                        }
                    } else {
                        CompositionLocalProvider(
                            LocalAppDatabase provides db,
                            LocalBlackOnWhite provides blackOnWhite
                        ) {
                            val deepLinkPath = intent?.data?.let { uri ->
                                if (uri.scheme == "veda") {
                                    uri.toString().removePrefix("veda:").removePrefix("//")
                                } else null
                            }
                            RootScreen(initialDeepLink = deepLinkPath)
                        }
                    }
                } else {
                    SplashScreen()
                }
            }
        }
    }
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .parchmentBackground(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(32.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pv_icon),
                contentDescription = "PV App Icon",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(24.dp))
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "PV",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = AccentGreen
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Pocket Vedas",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(48.dp))
            CircularProgressIndicator(color = AccentGreen)
        }
    }
}

@Composable
fun ErrorScreen(error: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .parchmentBackground(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                "Oops! Something went wrong",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "We encountered an issue preparing the database files:\n\n$error",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(containerColor = AccentGreen)
            ) {
                Text("Retry")
            }
        }
    }
}
