plugins {
    kotlin("jvm") version "2.3.20"
    kotlin("plugin.serialization") version "2.3.20"
    application
}

group = "me.zsqw123"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap/")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("com.google.code.gson:gson:2.13.2")
    implementation("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.4.0")
    implementation("com.google.guava:guava:33.5.0-jre")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    val asmVersion = "9.9.1"
    implementation("org.ow2.asm:asm:$asmVersion")
    implementation("org.ow2.asm:asm-tree:$asmVersion")
    implementation(kotlin("reflect"))
}

application {
    mainClass.set("MainKt")
}

kotlin {
    jvmToolchain(17)
    compilerOptions {
        freeCompilerArgs.set(listOf("-Xcontext-receivers"))
    }
    sourceSets.main {
//        kotlin.srcDir("build/generated/ksp/main/kotlin")
        languageSettings {
//            languageVersion = "2.0"
        }
    }
}
