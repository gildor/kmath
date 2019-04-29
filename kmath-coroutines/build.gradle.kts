plugins {
    `multiplatform-config`
    id("kotlinx-atomicfu") version Versions.atomicfuVersion
}

dependencies {
    commonMainApi(project(":kmath-core"))
    commonMainApi("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.coroutinesVersion}")
    commonMainCompileOnly("org.jetbrains.kotlinx:atomicfu-common:${Versions.atomicfuVersion}")

    jvmMainApi("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}")
    jvmMainCompileOnly("org.jetbrains.kotlinx:atomicfu:${Versions.atomicfuVersion}")

    jsMainApi("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.coroutinesVersion}")
    jsMainCompileOnly("org.jetbrains.kotlinx:atomicfu-js:${Versions.atomicfuVersion}")
}
