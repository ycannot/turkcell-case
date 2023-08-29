import com.github.ycannot.plugins.LibCatalog

plugins {
    id("plugins.ycannot.library")
}

android {
    namespace = "com.github.ycannot.core"
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(LibCatalog.CoreKtx)
    testImplementation(LibCatalog.Test.Junit4)
    androidTestImplementation(LibCatalog.Test.TestExt)
    androidTestImplementation(LibCatalog.Test.Espresso)
    implementation(LibCatalog.DependencyInjection.HiltAndroid)
    kapt(LibCatalog.DependencyInjection.HiltCompiler)
    implementation(LibCatalog.Network.Gson)
    implementation(LibCatalog.Coroutines.CoroutinesCore)
    implementation(LibCatalog.Coroutines.CoroutinesAndroid)
    implementation(LibCatalog.Storage.SecurityPref)
}