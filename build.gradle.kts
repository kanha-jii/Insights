// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}

buildscript {
    dependencies {
        val navVersion = "2.7.5"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    }

}