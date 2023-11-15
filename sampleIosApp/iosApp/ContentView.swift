import SwiftUI
import SharedSDK

struct ContentView: View {
    
    let initialDepsHolder: InitialDepsHolder
    
    init(initialDepsHolder: InitialDepsHolder) {
        self.initialDepsHolder = initialDepsHolder
    }
    
    var body: some View {
        ComposeInteropView(initialDepsHolder: initialDepsHolder).ignoresSafeArea(.all)
	}
}

struct ComposeInteropView: UIViewControllerRepresentable {
    
    let initialDepsHolder: InitialDepsHolder
    
    init(initialDepsHolder: InitialDepsHolder) {
        self.initialDepsHolder = initialDepsHolder
    }
    
    func makeUIViewController(context: Context) -> UIViewController {
        return Main_iosKt.MainViewController(initialDepsHolder: initialDepsHolder)
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
