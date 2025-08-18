plugins {
    id("dev.kikugie.stonecutter")
}
stonecutter active "1.21.6-neoforge"

tasks.register("runCurrentClient") {
    stonecutter.current.run {
        dependsOn(":${stonecutter.current?.project}:runClient")
    }
}
tasks.register("runCurrentServer") {
    stonecutter.current.run {
        dependsOn(":${stonecutter.current?.project}:runServer")
    }
}

repositories {
    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth"
                url = uri("https://api.modrinth.com/maven")
            }
        }
        filter {
            includeGroup("maven.modrinth")
        }
    }
}
