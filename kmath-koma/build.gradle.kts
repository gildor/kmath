plugins {
    `multiplatform-config`
}

repositories {
    maven("http://dl.bintray.com/kyonifer/maven")
}

dependencies {
    commonMainApi(project(":kmath-core"))
    commonMainApi("com.kyonifer:koma-core-api-common:0.12")
    jvmMainApi("com.kyonifer:koma-core-api-jvm:0.12")
    jvmTestImplementation("com.kyonifer:koma-core-ejml:0.12")
    jsMainApi("com.kyonifer:koma-core-api-js:0.12")
}
