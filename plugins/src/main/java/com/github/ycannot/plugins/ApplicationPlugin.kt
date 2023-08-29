package com.github.ycannot.plugins

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.github.ycannot.plugins.extensions.createSigningConfig
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

class ApplicationPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.pluginManager.apply {
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
            apply("org.jetbrains.kotlin.plugin.parcelize")
            apply("org.jetbrains.kotlin.kapt")
            apply("com.google.dagger.hilt.android")
        }

        target.extensions.getByType<BaseAppModuleExtension>().run {
            compileSdk = 33
            defaultConfig {
                minSdk = 24
                targetSdk = 33
                versionCode = 1
                versionName = "1.0.0"

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables {
                    useSupportLibrary = true
                }
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            //kotlinOptions
            (this as ExtensionAware).extensions.configure<KotlinJvmOptions>("kotlinOptions"){
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
            buildFeatures {
                compose = true
                viewBinding = true
                buildConfig = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = "1.4.3"
            }
            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    excludes += "/META-INF/gradle/*"
                }
            }
            createSigningConfig(target.rootProject)
            buildTypes {
                debug {
                    signingConfig = signingConfigs.getByName("ycannot-sign")
                    buildConfigField("boolean", "IS_MOCK", "false")
                }
                create("mock") {
                    signingConfig = signingConfigs.getByName("ycannot-sign")
                    buildConfigField("boolean", "IS_MOCK", "true")
                }
                release {
                    signingConfig = signingConfigs.getByName("ycannot-sign")
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                    buildConfigField("boolean", "IS_MOCK", "false")
                }
            }
        }
    }
}