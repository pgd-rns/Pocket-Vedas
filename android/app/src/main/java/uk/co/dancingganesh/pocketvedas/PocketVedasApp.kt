package uk.co.dancingganesh.pocketvedas

import android.app.Application

class PocketVedasApp : Application() {
    var appDatabase: AppDatabase? = null
        private set

    override fun onCreate() {
        super.onCreate()
    }

    fun initializeDatabase(onReady: () -> Unit) {
        // Safe database loader in a background thread to prevent ANR crashes on launch
        Thread {
            val db = AppDatabase(this)
            appDatabase = db
            onReady()
        }.start()
    }
}
