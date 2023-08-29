import com.github.ycannot.plugins.LibCatalog
import com.github.ycannot.plugins.Modules

plugins {
    id("plugins.ycannot.library")
}

android {
    namespace = "com.github.ycannot.common.composable"
    buildFeatures{
        compose = true
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(Modules.Domain))
    implementation(project(Modules.Core))
    implementation(LibCatalog.CoreKtx)
    implementation(LibCatalog.Lifecycle)
    implementation(LibCatalog.ActivityCompose)
    implementation(platform(LibCatalog.Compose.ComposeBom))
    implementation(LibCatalog.Compose.Ui)
    implementation(LibCatalog.Compose.UiGraphics)
    implementation(LibCatalog.Compose.UiToolingPreview)
    implementation(LibCatalog.Compose.Material3)
    implementation(LibCatalog.Compose.Material)
    testImplementation(LibCatalog.Test.Junit4)
    androidTestImplementation(LibCatalog.Test.TestExt)
    androidTestImplementation(LibCatalog.Test.Espresso)
    testImplementation(LibCatalog.Test.Coroutines)
    androidTestImplementation(LibCatalog.Test.Coroutines)
    androidTestImplementation(platform(LibCatalog.Compose.ComposeBom))
    androidTestImplementation(LibCatalog.Compose.Junit4)
    debugImplementation(LibCatalog.Compose.UiTooling)
    debugImplementation(LibCatalog.Compose.TestManifest)
    implementation(LibCatalog.DependencyInjection.HiltComposeNavigation)
    implementation(LibCatalog.DependencyInjection.HiltComposeNavigationFragment)
}