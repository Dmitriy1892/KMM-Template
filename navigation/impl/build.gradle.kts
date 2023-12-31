import extensions.commonMainDependencies

plugins {
    id("kmm.feature.compose")
}

android {
    namespace = "navigation.impl"
}

commonMainDependencies {
    implementation(project(":common:decompose-navigation"))
    
    implementation(project(":feature:app"))
    implementation(project(":sample-feature:main"))
    implementation(project(":sample-feature:sample-one"))
    implementation(project(":sample-feature:tabview"))
}