import SwiftUI
import SharedSDK

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate
    
    @Environment(\.scenePhase)
    var scenePhase: ScenePhase
    
    var rootHolder: RootHolder { appDelegate.rootHolder }
    
    let koinDiHolder: KoinDiHolder
        
    init() {
        koinDiHolder = KoinDiHolder.companion.getInstance()
    }
    
	var body: some Scene {
		WindowGroup {
            let lifecycle = rootHolder.lifecycle
            ContentView(initialDepsHolder: koinDiHolder.getInitialDepsHolder(lifecycleRegistry: lifecycle))
                .onChange(of: scenePhase) { newPhase in
                    switch newPhase {
                    case .background: LifecycleRegistryExtKt.stop(lifecycle)
                    case .inactive: LifecycleRegistryExtKt.pause(lifecycle)
                    case .active: LifecycleRegistryExtKt.resume(lifecycle)
                    @unknown default: break
                    }
                }
		}
	}
}
