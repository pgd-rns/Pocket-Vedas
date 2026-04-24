# Pocket Vedas iOS

This is a native iOS rebuild scaffold for the decompiled Android APK in this repository.

What is included:

- SwiftUI app target in `ios/PocketVedasIOS.xcodeproj`
- Books grid backed by `assets/raw/vedabase.db`
- HTML reader using `WKWebView`
- Full-text search backed by the bundled SQLite FTS table
- Bookmark list backed by `assets/raw/bookmarks.db`
- About screen backed by `assets/raw/info.html`

Important limits:

- This is not a binary APK-to-IPA conversion. The Android app was decompiled, so the iPhone app has to be rebuilt natively.
- Some Android behaviors are simplified in this first pass, especially page-to-page swipe behavior and bookmark scroll restoration.
- You will still need to set your Apple developer team and bundle identifier in Xcode before installing on an iPhone.

How to open:

1. Open `ios/PocketVedasIOS.xcodeproj` in Xcode.
2. Set a valid bundle identifier and signing team.
3. Choose an iPhone 13 or newer simulator/device.
4. Build and run.
