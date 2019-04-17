group = "scientifik"
version = "0.1.0"

allprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        //maven("https://dl.bintray.com/kotlin/kotlin-eap")
        jcenter()
    }


    // apply bintray configuration plugin
    plugins.apply("bintray-config")

    //apply artifactory configuration
    apply(from = "${rootProject.rootDir}/gradle/artifactory.gradle")
}

// Configure all MPP projects
configureMppSubprojects()

