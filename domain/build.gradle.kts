import com.github.ycannot.plugins.LibCatalog
import com.github.ycannot.plugins.Modules

plugins {
    id("plugins.ycannot.library")
    id("plugins.ycannot.unit-test")
}

android {
    namespace = "com.github.ycannot.domain"
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(Modules.Core))
    implementation(project(Modules.Data))
    implementation(LibCatalog.CoreKtx)
    implementation(LibCatalog.Coroutines.CoroutinesCore)
    implementation(LibCatalog.Coroutines.CoroutinesAndroid)
    androidTestImplementation(LibCatalog.Test.TestExt)
    androidTestImplementation(LibCatalog.Test.Espresso)
    implementation(LibCatalog.DependencyInjection.HiltAndroid)
    kapt(LibCatalog.DependencyInjection.HiltCompiler)
    implementation(LibCatalog.Network.Gson)
}