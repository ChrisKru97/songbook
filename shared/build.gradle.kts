val koinVersion = "3.4.0"
val sqlDelightVersion = "1.5.5"
val firebaseVersion = "1.8.0"
val serializationVersion = "1.5.0"
val coroutinesVersion = "1.6.4"
val settingsVersion = "1.0.0"
val intlVersion = "0.21.2"
val dateTimeVersion = "0.4.0"
val normalizeVersion = "1.0.5"

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
    kotlin("plugin.serialization") version "1.8.20"
    id("dev.icerock.mobile.multiplatform-resources")
}

sqldelight {
    database ("Database") {
       packageName = "cz.krutsche.songbook"
   }
}

multiplatformResources {
    multiplatformResourcesPackage = "cz.krutsche.songbook"
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
                implementation("io.insert-koin:koin-core:$koinVersion")
                implementation("dev.gitlive:firebase-firestore:$firebaseVersion")
                implementation("dev.gitlive:firebase-auth:$firebaseVersion")
                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
                implementation("com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion")
                implementation("com.russhwolf:multiplatform-settings:$settingsVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")
                implementation("com.doist.x:normalize:$normalizeVersion")
                api("dev.icerock.moko:resources:$intlVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "cz.krutsche.songbook"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
    }
}