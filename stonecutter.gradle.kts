plugins {
    id("dev.kikugie.stonecutter")
}
stonecutter active "1.21.6-neoforge"

stonecutter registerChiseled tasks.register("chiseledBuild", stonecutter.chiseled) { 
    group = "project"
    ofTask("build")
}

stonecutter registerChiseled tasks.register("chiseledPublishMod", stonecutter.chiseled) {
    group = "project"
    ofTask("publishMods")
}

tasks.register("runCurrentClient") {
    stonecutter.current.run {
        dependsOn(":$project:runClient")
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
