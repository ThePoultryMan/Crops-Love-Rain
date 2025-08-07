import net.fabricmc.loom.task.RemapJarTask

plugins {
    id("dev.isxander.modstitch.base") version "0.7.0-unstable"
    id("me.modmuss50.mod-publish-plugin") version("0.8.4")
}

fun prop(name: String, consumer: (prop: String) -> Unit) {
    (findProperty(name) as? String?)
        ?.let(consumer)
}

val minecraft = property("deps.minecraft") as String
val fabricLoaderVersionO = "0.16.14"

modstitch {
    minecraftVersion = minecraft

    parchment {
        prop("deps.parchment") { mappingsVersion = it }
    }

    metadata {
        modId = "cropsloverain"
        modName = property("mod.name") as String
        modDescription = "Makes crops grow faster when it's raining."
        modVersion = property("mod.version") as String
        modGroup = "io.github.thepoultryman"
        modAuthor = "ThePoultryMan"

        fun <K, V> MapProperty<K, V>.populate(block: MapProperty<K, V>.() -> Unit) {
            block()
        }

        replacementProperties.populate {
            put("mod_issue_tracker", "https://github.com/ThePoultryMan/Crops-Love-Rain/issues")
            var minimumMinecraftVersion = if (property("deps.minecraft_min") != null) {
                property("deps.minecraft_min") as String
            } else {
                minecraftVersion
            }
            put("minecraft_min_version", minimumMinecraftVersion as String)
            put("midnightlib_version", property("deps.midnightlib_version") as String)
            if (modstitch.isLoom) {
                put("loader_version", fabricLoaderVersionO)
                put("fabric_api_version", property("deps.req_fabric_api_version") as String)
            } else {
                put("loader_version", property("deps.neoforge") as String)
            }
        }
    }

    loom {
        fabricLoaderVersion = fabricLoaderVersionO

        configureLoom {}
    }

    moddevgradle {
        prop("deps.neoforge") { neoForgeVersion = it }

        defaultRuns()
    }

    mixin {
        addMixinsToModManifest = true

        configs.register("cropsloverain")
    }
}

var constraint: String = name.split("-")[1]
stonecutter {
    constants += arrayOf(
        "fabric" to (constraint == "fabric"),
        "neoforge" to (constraint == "neoforge"),
        "forge" to (constraint == "forge"),
        "vanilla" to (constraint == "vanilla")
    )
}

dependencies {
    modstitch.loom {
        modstitchModImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api_version") as String}")
    }
    var loader = if (modstitch.isLoom) {
        "fabric"
    } else {
        "neoforge"
    }
    modstitchModImplementation(
        "maven.modrinth:midnightlib:${property("deps.midnightlib_version")}+${property("deps.minecraft_min")}-${loader}"
    )
}

publishMods {
    if (modstitch.isLoom) {
        file.set(tasks.named<RemapJarTask>("remapJar").get().archiveFile)
    } else {
        file.set(tasks.jar.get().archiveFile)
    }

    var minMinecraftVersion = findProperty("deps.minecraft_min") as String?
    var versionRange = if (minMinecraftVersion != null) {
        "${minMinecraftVersion}-${minecraft}"
    } else {
        minecraft
    }
    var loader = if (modstitch.isLoom) {
        "fabric"
    } else {
        "neoforge"
    }
    displayName = "${property("mod.name")} ${property("mod.version")}-${loader} for $versionRange"
    version = "${property("mod.version")}+${minecraft}-${loader}"
    type = STABLE
    if (modstitch.isLoom) {
        modLoaders.addAll("fabric", "quilt")
    } else {
        modLoaders.add("neoforge")
    }
    changelog = rootProject.file("CHANGELOG.md").readText()

    modrinth {
        accessToken = providers.environmentVariable("MODRINTH_TOKEN")
        projectId = "cRci7UZp"

        projectDescription.set(providers.fileContents(layout.projectDirectory.file("README.md")).asText)

        if (minMinecraftVersion != null) {
            minecraftVersionRange {
                start = minMinecraftVersion
                end = minecraft
            }
        } else {
            minecraftVersions.add(minecraft)
        }

        if (modstitch.isLoom) {
            requires("fabric-api")
        }
        requires("midnightlib")
    }

    curseforge {
        accessToken = providers.environmentVariable("CURSEFORGE_API_KEY")
        projectId = "580294"

        if (minMinecraftVersion != null) {
            minecraftVersionRange {
                start = minMinecraftVersion
                end = minecraft
            }
        } else {
            minecraftVersions.add(minecraft)
        }

        if (modstitch.isLoom) {
            requires("fabric-api")
        }
        requires("midnightlib")
    }
}
