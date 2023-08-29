import com.github.ycannot.plugins.LibCatalog
import com.github.ycannot.plugins.Modules

plugins {
    id("plugins.ycannot.library")
}

android {
    namespace = "com.github.ycannot.data"
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(Modules.Core))
    implementation(LibCatalog.CoreKtx)
    testImplementation(LibCatalog.Test.Junit4)
    androidTestImplementation(LibCatalog.Test.TestExt)
    androidTestImplementation(LibCatalog.Test.Espresso)
    implementation(LibCatalog.DependencyInjection.HiltAndroid)
    kapt(LibCatalog.DependencyInjection.HiltCompiler)
    implementation(LibCatalog.Network.Gson)
    implementation(LibCatalog.Network.Retrofit)
    implementation(LibCatalog.Network.RetrofitGson)
    implementation(platform(LibCatalog.Network.OkHttpBom))
    implementation(LibCatalog.Network.OkHttp)
    implementation(LibCatalog.Network.OkHttpLoggingInterceptor)
}