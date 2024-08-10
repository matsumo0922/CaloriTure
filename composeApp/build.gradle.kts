import com.android.build.api.variant.ResValue
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.*

plugins {
    id("caloriture.primitive.kmp.common")
    id("caloriture.primitive.kmp.android.application")
    id("caloriture.primitive.kmp.android.compose")
    id("caloriture.primitive.kmp.android")
    id("caloriture.primitive.kmp.ios")
}

android {
    namespace = "me.matsumo.caloriture"

    val localProperties = Properties().apply {
        load(project.rootDir.resolve("local.properties").inputStream())
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("${project.rootDir}/gradle/keystore/debug.keystore")
        }
        create("release") {
            storeFile = file("${project.rootDir}/gradle/keystore/release.keystore")
            storePassword = localProperties.getProperty("storePassword") ?: System.getenv("RELEASE_STORE_PASSWORD")
            keyPassword = localProperties.getProperty("keyPassword") ?: System.getenv("RELEASE_KEY_PASSWORD")
            keyAlias = localProperties.getProperty("keyAlias") ?: System.getenv("RELEASE_KEY_ALIAS")
        }
        create("billing") {
            storeFile = file("${project.rootDir}/gradle/keystore/release.keystore")
            storePassword = localProperties.getProperty("storePassword") ?: System.getenv("RELEASE_STORE_PASSWORD")
            keyPassword = localProperties.getProperty("keyPassword") ?: System.getenv("RELEASE_KEY_PASSWORD")
            keyAlias = localProperties.getProperty("keyAlias") ?: System.getenv("RELEASE_KEY_ALIAS")
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
            versionNameSuffix = ".D"
            applicationIdSuffix = ".debug"
        }
        create("billing") {
            signingConfig = signingConfigs.getByName("billing")
            isDebuggable = true
            matchingFallbacks.add("debug")
        }
    }

    androidComponents {
        onVariants {
            val appName = when (it.buildType) {
                "debug" -> "CaloriTure Debug"
                "billing" -> "CaloriTure Billing"
                else -> "CaloriTure"
            }

            it.resValues.apply {
                put(it.makeResValueKey("string", "app_name"), ResValue(appName, null))
            }

            if (it.buildType == "release") {
                it.packaging.resources.excludes.add("META-INF/**")
            }
        }
    }
}

composeCompiler {
    enableStrongSkippingMode = true
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.moko.permissions)
                implementation(libs.moko.permissions.compose)
                implementation(libs.moko.biometry)
                implementation(libs.moko.biometry.compose)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.androidx.core.splashscreen)
                implementation(libs.play.review)
                implementation(libs.play.update)
                implementation(libs.google.material)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

buildkonfig {
    packageName = "me.matsumo.caloriture"
    defaultConfigs {

    }
}