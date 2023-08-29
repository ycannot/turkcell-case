package com.github.ycannot.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.github.ycannot.plugins.extensions.kaptTest
import com.github.ycannot.plugins.extensions.testImplementation
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

class UnitTestPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.dependencies {
            testImplementation(LibCatalog.Test.Junit4)
            testImplementation(LibCatalog.Test.TestRunner)
            testImplementation(LibCatalog.Test.Coroutines)
            testImplementation(LibCatalog.Test.Mockk)
            testImplementation(LibCatalog.Test.Robolectric)
            testImplementation(LibCatalog.Test.Hilt)
            testImplementation(LibCatalog.Test.SlfjSimple)
            kaptTest(LibCatalog.Test.HiltCompiler)
        }
    }
}