package feature.app

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

fun MainViewController(
    initialDepsHolder: InitialDepsHolder
): UIViewController = ComposeUIViewController {
    App(initialDepsHolder)
}