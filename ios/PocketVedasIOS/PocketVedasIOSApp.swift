import SwiftUI

@main
struct PocketVedasIOSApp: App {
    @StateObject private var database: AppDatabase

    init() {
        UserDefaults.standard.register(defaults: [
            "pref_text": true,
            "pref_synonyms": true,
            "pref_translation": true,
            "pref_purport": true,
            "pref_zoom": 133.0,
            "pref_reverse": true,
            "pref_keep_awake": false
        ])
        _database = StateObject(wrappedValue: AppDatabase())
    }

    var body: some Scene {
        WindowGroup {
            RootView()
                .environmentObject(database)
        }
    }
}
