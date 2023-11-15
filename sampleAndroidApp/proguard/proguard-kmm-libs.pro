-keep class io.github.dmitriy1892.kmm.mvi.kmm.mvvm.**{*;}
-keep class io.github.dmitriy1892.kmm.mvvm.**{*;}
-keepnames class io.github.dmitriy1892.kmm.mvi.kmm.mvvm.MviViewModel
-keepnames class * extends io.github.dmitriy1892.kmm.mvi.kmm.mvvm.MviViewModel
-keepclassmembers class * extends io.github.dmitriy1892.kmm.mvi.kmm.mvvm.MviViewModel {
    <init>(...);
}

-keepclassmembers class * extends io.github.dmitriy1892.kmm.mvvm.core.BaseViewModel {
    <init>(...);
}