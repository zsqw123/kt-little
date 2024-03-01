import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.22"
    application
}

group = "me.zsqw123"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap/")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.7")
    implementation("com.google.guava:guava:33.0.0-jre")
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}

application {
    mainClass.set("MainKt")
}

kotlin {
    sourceSets.main {
//        kotlin.srcDir("build/generated/ksp/main/kotlin")
        languageSettings {
            languageVersion = "2.0"
        }
    }
}
