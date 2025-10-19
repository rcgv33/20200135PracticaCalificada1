// Top-level build file where you can add configuration options common to all sub-projects/modules.
// build.gradle.kts (root)

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    // ⬇️ Agrega el plugin de Google Services aquí (sin buildscript)
    id("com.google.gms.google-services") version "4.4.2" apply false
}

// No uses buildscript { ... } aquí
