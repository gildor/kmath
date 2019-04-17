import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

// This is a bit different approach than precompiled script plugin,
// we just extract config logic to function and call it where we need this
// We use buildSrc classpath dependency to resolve classpath of plugins
// Of course this function maybe separated,
// I just want to show how to extract big part of config to buildSrc
fun Project.configureMppSubprojects() {
    subprojects {
        if (!name.startsWith("kmath")) return@subprojects


        extensions.findByType<KotlinMultiplatformExtension>()?.apply {
            jvm {
                compilations.all {
                    kotlinOptions {
                        jvmTarget = "1.8"
                    }
                }
            }
            targets.all {
                sourceSets.all {
                    languageSettings.progressiveMode = true
                }
            }


            extensions.findByType<PublishingExtension>()?.apply {
                publications.filterIsInstance<MavenPublication>().forEach { publication ->
                    if (publication.name == "kotlinMultiplatform") {
                        // for our root metadata publication, set artifactId with a package and project name
                        publication.artifactId = project.name
                    } else {
                        // for targets, set artifactId with a package, project name and target name (e.g. iosX64)
                        publication.artifactId = "${project.name}-${publication.name}"
                    }
                }

                // Create empty jar for sources classifier to satisfy maven requirements
                tasks.register<Jar>("stubSources") {
                    archiveClassifier.set("sources")
                    //from(sourceSets.main.get().allSource)
                }

                // Create empty jar for javadoc classifier to satisfy maven requirements
                val stubJavadoc = tasks.register<Jar>("stubJavadoc") {
                    archiveClassifier.set("javadoc")
                }

                extensions.findByType<KotlinMultiplatformExtension>()?.apply {

                    targets.forEach { target ->
                        val publication = publications.findByName(target.name) as MavenPublication

                        // Patch publications with fake javadoc
                        publication.artifact(stubJavadoc)

                        // Remove gradle metadata publishing from all targets which are not native
//                if (target.platformType.name != "native") {
//                    publication.gradleModuleMetadataFile = null
//                    tasks.matching { it.name == "generateMetadataFileFor${name.capitalize()}Publication" }.all {
//                        onlyIf { false }
//                    }
//                }
                    }
                }
            }
        }
    }
}
