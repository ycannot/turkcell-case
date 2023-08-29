plugins {
    `kotlin-dsl`
}

afterEvaluate {
    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_11.toString()
        targetCompatibility = JavaVersion.VERSION_11.toString()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions { jvmTarget = JavaVersion.VERSION_11.toString() }
    }
}


dependencies {
    compileOnly("com.android.tools.build:gradle:8.1.0-beta01")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
}

gradlePlugin{
    plugins{
        register("application-plugin") {
            id = "plugins.ycannot.application"
            implementationClass = "com.github.ycannot.plugins.ApplicationPlugin"
        }
        register("library-plugin") {
            id = "plugins.ycannot.library"
            implementationClass = "com.github.ycannot.plugins.LibraryPlugin"
        }
        register("unit-test-plugin") {
            id = "plugins.ycannot.unit-test"
            implementationClass = "com.github.ycannot.plugins.UnitTestPlugin"
        }
    }
}

