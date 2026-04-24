import SwiftUI

@main
struct PocketVedasIOSApp: App {
    @StateObject private var database = AppDatabase()

    var body: some Scene {
        WindowGroup {
            RootView()
                .environmentObject(database)
        }
    }
}
