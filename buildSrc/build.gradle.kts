plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    jcenter()
}

// I updated kotlin version,
// because Kotlin JS plugin publishing is incompatible with new gradle,
// see https://youtrack.jetbrains.com/issue/KT-29758
val kotlinVersion = "1.3.30"

// Add plugins used in buildSrc as dependencies, also we should specify version only here
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jfrog.buildinfo:build-info-extractor-gradle:4.9.5")
    implementation("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4")
}
