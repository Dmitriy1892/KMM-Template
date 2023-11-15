//
//  RootHolder.swift
//  iosApp
//
//  Created by Дмитрий on 24.10.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import UIKit

class RootHolder : ObservableObject {
    
    let lifecycle: LifecycleRegistry
    
    init() {
        lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        LifecycleRegistryExtKt.create(lifecycle)
    }
    
    deinit {
        LifecycleRegistryExtKt.destroy(lifecycle)
    }
}

class AppDelegate : NSObject, UIApplicationDelegate {
    let rootHolder: RootHolder = RootHolder()
}
