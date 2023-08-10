import SwiftUI
import CoreTranslations

struct ContentView: View {
    let greet = MR.strings().app_name.localized()

	var body: some View {
		Text(greet)
	}
}
