import net.fabricmc.loom.task.RemapJarTask;

plugins {
    id("dev.isxander.modstitch.base") version "0.8.0"
    id("me.modmuss50.mod-publish-plugin") version("1.1.0")
}

fun prop(name: String, consumer: (prop: String) -> Unit) {
    (findProperty(name) as? String?)
        ?.let(consumer)
}

val minecraft = if (property("deps.minecraft")?.equals("latest") == true) {
    property("latest_minecraft") as String
} else {
    property("deps.minecraft") as String
}

modstitch {
    minecraftVersion = minecraft

    parchment {
        prop("deps.parchment") { mappingsVersion = it }
    }

    metadata {
        modId = "cropsloverain"
        prop("mod.name") { modName = it }
        modDescription = "Makes crops grow faster when it's raining."
        prop("mod.version") { modVersion = it }
        modGroup = "io.github.thepoultryman"
        modAuthor = "ThePoultryMan"

        fun <K: Any, V: Any> MapProperty<K, V>.populate(block: MapProperty<K, V>.() -> Unit) {
            block()
        }

        replacementProperties.populate {
            put("mod_issue_tracker", "https://github.com/ThePoultryMan/Crops-Love-Rain/issues")
            prop("deps.forge_config_api_port_min") {
                put("forge_config_api_port_min_version", it)
            }
            prop("deps.min_fabric_api_version") {
                put("fabric_api_min_version", it)
            }
            put("min_minecraft_version", property("deps.minecraft_min") as String)
            put("minecraft_upper_bound", if (property("deps.minecraft")?.equals("latest") == true) {
                ""
            } else {
                if (modstitch.isLoom) {
                    " <=${minecraftVersion.get()}"
                } else {
                    minecraftVersion.get()
                }
            })
            put("loader_version", property("deps.loader_version_min") as String)
        }
    }

    // Fabric Loom (Fabric)
    loom {
        // It's not recommended to store the Fabric Loader version in properties.
        // Make sure it's up to date.
        fabricLoaderVersion = "0.18.4"

        // Configure loom like normal in this block.
        configureLoom {}
    }

    // ModDevGradle (NeoForge, Forge, Forgelike)
    moddevgradle {
        prop("deps.neoforge") { neoForgeVersion = it }

        // Configures client and server runs for MDG, it is not done by default
        defaultRuns()
    }

    mixin {
        // You do not need to specify mixins in any mods.json/toml file if this is set to
        // true, it will automatically be generated.
        addMixinsToModManifest = true

        configs.register("cropsloverain")
    }
}

var constraint: String = name.split("-")[1]
stonecutter {
    constants.match(constraint, "fabric", "neoforge")

    replacements {
        string {
            direction = eval(current.version, ">=1.21.11")
            replace("ResourceLocation", "Identifier")
        }
    }
}

repositories {
    maven {
        name = "Fuzs Mod Resources"
        url = uri("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/")
    }
    maven {
        name = "Terraformers"
        url = uri("https://maven.terraformersmc.com/")
    }
}

dependencies {
    modstitch.loom {
        modstitchModImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api_version")}")
        modstitchModApi("fuzs.forgeconfigapiport:forgeconfigapiport-fabric:${property("deps.forge_config_api_port")}")
        modstitchModLocalRuntime("com.terraformersmc:modmenu:${property("deps.mod_menu")}")
    }
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
            requires("forge-config-api-port")
        }
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
            requires("forge-config-api-port-fabric")
        }
    }
}
