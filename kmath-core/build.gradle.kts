plugins {
    `multiplatform-config`
    jacoco
}

kotlin.sourceSets {
    commonMain {
        dependencies {
            api(project(":kmath-memory"))
        }
    }
}


tasks {
    val coverage = register<JacocoReport>("jacocoJVMTestReport") {
        //dependsOn = test
        group = "Reporting"
        description = "Generate Jacoco coverage report."
        classDirectories.setFrom(fileTree("$buildDir/classes/kotlin/jvm/main"))
        val coverageSourceDirs = listOf("src/commonMain/kotlin", "src/jvmMain/kotlin")
        additionalSourceDirs.setFrom(files(coverageSourceDirs))
        sourceDirectories.setFrom(files(coverageSourceDirs))
        executionData.setFrom(files("$buildDir/jacoco/jvmTest.exec"))
        reports {
            html.isEnabled = true
            xml.isEnabled = true
            csv.isEnabled = false
        }
    }
    jvmTest {
        finalizedBy(coverage)
    }
}
