plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

kotlin {
    sourceSets.all {
        languageSettings {
            languageVersion = "2.0"
        }
    }
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.secret.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
    implementation(libs.build.konfig.gradlePlugin)
    implementation(libs.gms.services)
    implementation(libs.gms.oss)
}

gradlePlugin {
    plugins {
        register("KmpPlugin") {
            id = "caloriture.primitive.kmp.common"
            implementationClass = "primitive.KmpCommonPlugin"
        }
        register("KmpAndroidPlugin") {
            id = "caloriture.primitive.kmp.android"
            implementationClass = "primitive.KmpAndroidPlugin"
        }
        register("KmpAndroidApplication") {
            id = "caloriture.primitive.kmp.android.application"
            implementationClass = "primitive.KmpAndroidApplication"
        }
        register("KmpAndroidLibrary") {
            id = "caloriture.primitive.kmp.android.library"
            implementationClass = "primitive.KmpAndroidLibrary"
        }
        register("KmpAndroidCompose") {
            id = "caloriture.primitive.kmp.android.compose"
            implementationClass = "primitive.KmpAndroidCompose"
        }
        register("KmpIosPlugin") {
            id = "caloriture.primitive.kmp.ios"
            implementationClass = "primitive.KmpIosPlugin"
        }
        register("KmpServerPlugin") {
            id = "caloriture.primitive.kmp.server"
            implementationClass = "primitive.KmpServerPlugin"
        }
        register("DetektPlugin") {
            id = "caloriture.primitive.detekt"
            implementationClass = "primitive.DetektPlugin"
        }
    }
}
