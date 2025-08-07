pluginManagement {
    repositories {
        maven {
            name = "FabricMC"
            url = uri("https://maven.fabricmc.net/")
        }
        maven {
            name = "NeoForged"
            url = uri("https://maven.neoforged.net/releases/")
        }
        gradlePluginPortal()
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.7+"
}

stonecutter {
    kotlinController = true
    centralScript = "build.gradle.kts"

    create(rootProject) {
        fun mc(mcVersion: String, name: String = mcVersion, loaders: Iterable<String>) =
            loaders.forEach { vers("$name-$it", mcVersion) }

        // Configure your targets here!
        listOf("1.21.6").forEach {
            mc(it, loaders = listOf("fabric", "neoforge"))
        }

        // This is the default target.
        // https://stonecutter.kikugie.dev/stonecutter/guide/setup#settings-settings-gradle-kts
        vcsVersion = "1.21.6-neoforge"
    }
}

rootProject.name = "Crops Love Rain"