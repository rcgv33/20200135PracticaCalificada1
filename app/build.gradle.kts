// app/build.gradle.kts  — PC012025

plugins {
    id("com.android.application")
    kotlin("android")
    // Requerido desde Kotlin 2.x para Compose
    id("org.jetbrains.kotlin.plugin.compose")
    // Google Services para Firebase
    id("com.google.gms.google-services")
}

android {
    // Usa el mismo paquete que estás usando en tu código
    namespace = "com.example.pc012025"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.pc012025"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            // configura lo que necesites para debug
        }
    }

    // Habilita Compose
    buildFeatures { compose = true }

    // Con Kotlin 2.x + plugin de Compose, ya NO se usa kotlinCompilerExtensionVersion
    // composeOptions { kotlinCompilerExtensionVersion = "..." }  // ← NO necesario

    // Opcional: compatibilidad con Java 17 si tu proyecto lo usa
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // --- Jetpack Compose (usa BOM para alinear versiones) ---
    implementation(platform("androidx.compose:compose-bom:2024.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Navegación Compose
    implementation("androidx.navigation:navigation-compose:2.8.3")

    // ViewModel con Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

    // --- Firebase (usa BOM para alinear) ---
    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    // Core AndroidX
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.9.3")
}
