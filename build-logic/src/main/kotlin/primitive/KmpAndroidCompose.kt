package primitive

import me.matsumo.caloriture.android
import me.matsumo.caloriture.androidTestImplementation
import me.matsumo.caloriture.debugImplementation
import me.matsumo.caloriture.implementation
import me.matsumo.caloriture.library
import me.matsumo.caloriture.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KmpAndroidCompose : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            android {
                buildFeatures.compose = true
            }

            // https://github.com/JetBrains/compose-multiplatform/issues/4711
            configurations.all {
                resolutionStrategy {
                    force("androidx.compose.material:material-ripple:1.7.0-alpha05")
                }
            }

            dependencies {
                val bom = libs.library("androidx-compose-bom")

                implementation(project.dependencies.platform(bom))
                implementation(libs.library("androidx-compose-ui-tooling-preview"))
                debugImplementation(libs.library("androidx-compose-ui-tooling"))
                androidTestImplementation(project.dependencies.platform(bom))
            }
        }
    }
}
