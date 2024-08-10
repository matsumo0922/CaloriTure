import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("caloriture.primitive.kmp.common")
    id("caloriture.primitive.kmp.android.library")
    id("caloriture.primitive.kmp.android")
    id("caloriture.primitive.kmp.ios")
}

kotlin {
    jvm()
}

android {
    namespace = "me.matsumo.caloriture.shared"
}
