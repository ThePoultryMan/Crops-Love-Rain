plugins {
    id("dev.kikugie.stonecutter")
}
stonecutter active "1.21.6-fabric"

tasks.register("runCurrentClient") {
    stonecutter.current.run {
        dependsOn(":${stonecutter.current?.project}:runClient")
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
