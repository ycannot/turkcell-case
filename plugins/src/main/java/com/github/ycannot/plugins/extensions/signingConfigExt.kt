package com.github.ycannot.plugins.extensions

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import java.io.FileInputStream
import java.util.Properties

fun ApplicationExtension.createSigningConfig(rootProject: Project){
    signingConfigs.create("ycannot-sign") {
        val keystorePropertiesFile = rootProject.file("signing/ycannot-signing.properties")
        if (!keystorePropertiesFile.exists()) {
            throw Exception("Missing signing/signing.properties file!")
        } else {
            val keystoreProperties = Properties().apply {
                load(FileInputStream(keystorePropertiesFile))
            }
            try {
                storeFile = rootProject.file(keystoreProperties["STORE_FILE"] as String)
                storePassword = keystoreProperties["STORE_PASSWORD"] as String
                keyAlias = keystoreProperties["KEY_ALIAS"] as String
                keyPassword = keystoreProperties["KEY_PASSWORD"] as String
            } catch (e: Exception) {
                throw Exception("Check Keystore Credentials! ${e.message}", e.cause)
            }
        }
    }
}