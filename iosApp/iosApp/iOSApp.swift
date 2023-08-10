import SwiftUI
import SharedSDK
import CoreUiKit
import CoreTranslations

@main
struct iOSApp: App {
    
    private var koinDiHolder: KoinDiHolder
    
    init() {
        self.koinDiHolder = KoinDiHolder.companion.getInstance()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()            
		}
	}
}
