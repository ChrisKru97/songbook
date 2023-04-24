plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.0").apply(false)
    id("com.android.library").version("8.0.0").apply(false)
    kotlin("android").version("1.8.10").apply(false)
    kotlin("multiplatform").version("1.8.10").apply(false)
}

buildscript {
    val sqlDelightVersion = "1.5.5"
    val intlVersion = "0.21.2"
    val gmsVersion = "4.3.15"

    dependencies {
        classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
        classpath("com.google.gms:google-services:$gmsVersion")
        classpath("dev.icerock.moko:resources-generator:$intlVersion")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
