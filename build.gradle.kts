plugins {
    val kotlinVersion = "1.4.21"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.2.1" // mirai-console version
}

mirai {
    coreVersion = "2.2.1" // mirai-core version
}

group = "com.blrabbit"
version = "0.1.0"

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven { url = uri("https://dl.bintray.com/karlatemp/misc") }
}

dependencies {
}