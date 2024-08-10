package primitive
import me.matsumo.caloriture.androidApplication
import me.matsumo.caloriture.libs
import me.matsumo.caloriture.setupAndroid
import me.matsumo.caloriture.version
import org.gradle.api.Plugin
import org.gradle.api.Project

class KmpAndroidApplication : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("kotlin-parcelize")
                apply("kotlinx-serialization")
                apply("project-report")
                apply("com.google.firebase.crashlytics")
                apply("com.google.devtools.ksp")
                apply("com.google.gms.google-services")
                apply("com.mikepenz.aboutlibraries.plugin")
                apply("com.codingfeline.buildkonfig")
            }

            androidApplication {
                setupAndroid()

                compileSdk = libs.version("compileSdk").toInt()
                defaultConfig.targetSdk = libs.version("targetSdk").toInt()
                buildFeatures.viewBinding = true

                defaultConfig {
                    applicationId = "caios.android.fanbox"

                    versionName = libs.version("versionName")
                    versionCode = libs.version("versionCode").toInt()
                }

                packaging {
                    resources.excludes.addAll(
                        listOf(
                            "LICENSE",
                            "LICENSE.txt",
                            "NOTICE",
                            "asm-license.txt",
                            "cglib-license.txt",
                            "mozilla/public-suffix-list.txt",
                        )
                    )
                }
            }
        }
    }
}
