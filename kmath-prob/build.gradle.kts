plugins {
    `multiplatform-config`
    id("kotlinx-atomicfu") version Versions.atomicfuVersion
}

dependencies {
    commonMainApi(project(":kmath-core"))
    commonMainApi(project(":kmath-coroutines"))
    commonMainCompileOnly("org.jetbrains.kotlinx:atomicfu-common:${Versions.atomicfuVersion}")

    jvmMainCompileOnly("org.jetbrains.kotlinx:atomicfu:${Versions.atomicfuVersion}")

    jsMainCompileOnly("org.jetbrains.kotlinx:atomicfu-js:${Versions.atomicfuVersion}")
}

atomicfu {
    variant = "VH"
}
