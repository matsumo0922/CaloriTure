package primitive

import org.gradle.api.Plugin
import org.gradle.api.Project

class KmpServerPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("io.ktor.plugin")
            }
        }
    }
}