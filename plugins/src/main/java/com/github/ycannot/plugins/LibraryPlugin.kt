package com.github.ycannot.plugins

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

class LibraryPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.pluginManager.apply {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
            apply("org.jetbrains.kotlin.plugin.parcelize")
            apply("org.jetbrains.kotlin.kapt")
        }

        target.extensions.getByType<LibraryExtension>().run {
            compileSdk = 33
            defaultConfig {
                minSdk = 24
                targetSdk = 33

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
            buildTypes {
                debug {
                    buildConfigField("boolean", "IS_MOCK", "false")
                }
                create("mock"){
                    buildConfigField("boolean", "IS_MOCK", "true")
                }
                release {
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