package com.github.ycannot.plugins

object LibCatalog {
    const val CoreKtx = "androidx.core:core-ktx:1.10.1"
    const val Lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"
    const val ActivityCompose = "androidx.activity:activity-compose:1.7.2"

    object Network {
        private const val gsonVersion = "2.10.1"
        private const val retrofitVersion = "2.9.0"
        private const val okHttpVersion = "4.10.0"
        const val Gson = "com.google.code.gson:gson:${gsonVersion}"
        const val Retrofit = "com.squareup.retrofit2:retrofit:${retrofitVersion}"
        const val RetrofitGson = "com.squareup.retrofit2:converter-gson:${retrofitVersion}"

        // platform
        const val OkHttpBom = "com.squareup.okhttp3:okhttp-bom:${okHttpVersion}"
        const val OkHttp = "com.squareup.okhttp3:okhttp"
        const val OkHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor"
    }

    object Storage {
        const val SecurityPref = "androidx.security:security-crypto-ktx:1.1.0-alpha04"
    }

    object DependencyInjection {
        private const val hiltVersion = "2.45"
        private const val hiltComposeVersion = "1.0.0"
        const val HiltAndroid = "com.google.dagger:hilt-android:${hiltVersion}"
        const val HiltCompiler = "com.google.dagger:hilt-compiler:${hiltVersion}"
        const val HiltComposeNavigation = "androidx.hilt:hilt-navigation-compose:${hiltComposeVersion}"
        const val HiltComposeNavigationFragment = "androidx.hilt:hilt-navigation-fragment:${hiltComposeVersion}"
    }

    object Compose {
        const val ComposeBom = "androidx.compose:compose-bom:2023.03.00"
        const val Ui = "androidx.compose.ui:ui"
        const val UiGraphics = "androidx.compose.ui:ui-graphics"
        const val UiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val UiTooling = "androidx.compose.ui:ui-tooling"
        const val Material3 = "androidx.compose.material3:material3"
        const val Material = "androidx.compose.material:material"
        const val Junit4 = "androidx.compose.ui:ui-test-junit4"
        const val TestManifest = "androidx.compose.ui:ui-test-manifest"
        const val Navigation = "androidx.navigation:navigation-compose:2.5.3"
        const val ConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.1"
    }

    object Coroutines {
        private const val coroutinesVersion = "1.6.4"
        const val CoroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}"
        const val CoroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutinesVersion}"
    }

    object Media {
        const val Coil = "io.coil-kt:coil-compose:2.4.0"
    }

    object Test {
        const val Junit4 = "junit:junit:4.12"
        const val TestExt = "androidx.test.ext:junit:1.1.5"
        const val Espresso = "androidx.test.espresso:espresso-core:3.5.1"
        const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1"
        const val Mockk = "io.mockk:mockk:1.13.5"
        const val Hilt = "com.google.dagger:hilt-android-testing:2.44"
        const val SlfjSimple = "org.slf4j:slf4j-simple:2.0.7"
        const val HiltCompiler = "com.google.dagger:hilt-android-compiler:2.44"
        const val TestRunner = "androidx.test:runner:1.5.0"
        const val Robolectric = "org.robolectric:robolectric:4.9"
    }
}