import kotlin.script.experimental.jvm.util.classpathFromClass

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.serialization) apply false
    alias(libs.plugins.com.google.dagger.hilt.android) apply false
    alias(libs.plugins.ksp) apply false

}

buildscript{
    dependencies{
        classpath(libs.com.google.devtools.ksp.gradle.plugin)
    }
}