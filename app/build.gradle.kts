import com.github.ycannot.plugins.LibCatalog
import com.github.ycannot.plugins.Modules

plugins {
    id("plugins.ycannot.application")
    id("plugins.ycannot.unit-test")
}

apply(from = "${rootDir}/projectDependencyGraph.gradle")
//apply(from = "${rootDir}/sonar.gradle")

android {
    namespace = "com.github.ycannot.ttechcase"
    defaultConfig {
        applicationId = "com.github.ycannot.ttechcase"
    }
    testOptions.unitTests {
        this.isIncludeAndroidResources = true
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(Modules.FeatureHome))
    implementation(project(Modules.CommonComposable))
    implementation(project(Modules.Domain))
    implementation(LibCatalog.CoreKtx)
    implementation(LibCatalog.Lifecycle)
    implementation(LibCatalog.ActivityCompose)
    implementation(platform(LibCatalog.Compose.ComposeBom))
    implementation(LibCatalog.Compose.Ui)
    implementation(LibCatalog.Compose.UiGraphics)
    implementation(LibCatalog.Compose.UiToolingPreview)
    implementation(LibCatalog.Compose.Material3)
    implementation(LibCatalog.Compose.Material)
    implementation(LibCatalog.Compose.Navigation)
    androidTestImplementation(LibCatalog.Test.TestExt)
    androidTestImplementation(LibCatalog.Test.Espresso)
    androidTestImplementation(LibCatalog.Test.TestRunner)
    androidTestImplementation(LibCatalog.Test.Coroutines)
    androidTestImplementation(platform(LibCatalog.Compose.ComposeBom))
    androidTestImplementation(LibCatalog.Compose.Junit4)
    debugImplementation(LibCatalog.Compose.UiTooling)
    debugImplementation(LibCatalog.Compose.TestManifest)
    implementation(LibCatalog.DependencyInjection.HiltAndroid)
    kapt(LibCatalog.DependencyInjection.HiltCompiler)
    implementation(LibCatalog.DependencyInjection.HiltComposeNavigation)
    implementation(LibCatalog.DependencyInjection.HiltComposeNavigationFragment)
}