plugins {
    kotlin("multiplatform")
    id("kotlinx-atomicfu")
}

val atomicfuVersion: String by rootProject.extra

kotlin {
    jvm ()
    //js()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":kmath-core"))
                api(project(":kmath-coroutines"))
                compileOnly("org.jetbrains.kotlinx:atomicfu-common:${Ver.atomicfuVersion}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                compileOnly("org.jetbrains.kotlinx:atomicfu:${Ver.atomicfuVersion}")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
//        val jsMain by getting {
//            dependencies {
//                compileOnly("org.jetbrains.kotlinx:atomicfu-js:$atomicfuVersion")
//            }
//        }
//        val jsTest by getting {
//            dependencies {
//                implementation(kotlin("test-js"))
//            }
//        }

    }
}

atomicfu {
    variant = "VH"
}
