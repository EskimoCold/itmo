plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "web"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("libs/fastcgi-lib.jar"))
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("web.FastCGIServer")
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    archiveBaseName.set("server")
    archiveClassifier.set("")
    archiveVersion.set(version.toString())

    manifest {
        attributes(
                "Main-Class" to "web.FastCGIServer"
        )
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}
