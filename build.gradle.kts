plugins {
    id("dev.isxander.modstitch.base") version "0.5.14-unstable"
}

fun prop(name: String, consumer: (prop: String) -> Unit) {
    (findProperty(name) as? String?)
        ?.let(consumer)
}

val minecraft = property("deps.minecraft") as String

modstitch {
    minecraftVersion = minecraft

    javaTarget = 21

    parchment {
        prop("deps.parchment") { mappingsVersion = it }
    }

    metadata {
        modId = "cropsloverain"
        modName = "Crops Love Rain"
        modDescription = "Makes crops grow faster when it's raining."
        modVersion = "3.0.0"
        modGroup = "io.github.thepoultryman"
        modAuthor = "ThePoultryMan"

        fun <K, V> MapProperty<K, V>.populate(block: MapProperty<K, V>.() -> Unit) {
            block()
        }

        replacementProperties.populate {
            put("mod_issue_tracker", "https://github.com/ThePoultryMan/Crops-Love-Rain/issues")
            put("midnightlib_version", property("deps.midnightlib_version") as String)
        }
    }

    loom {
        fabricLoaderVersion = "0.16.14"

        configureLoom {}
    }

    moddevgradle {
        enable {
            prop("deps.neoform") { neoFormVersion = it }
            prop("deps.neoforge") { neoForgeVersion = it }
            prop("deps.mcp") { mcpVersion = it }
        }

        defaultRuns()

        configureNeoforge {
            runs.all {
                disableIdeRun()
            }
        }
    }

    mixin {
        addMixinsToModManifest = true

        configs.register("cropsloverain")
    }
}

var constraint: String = name.split("-")[1]
stonecutter {
    consts(
        "fabric" to constraint.equals("fabric"),
        "neoforge" to constraint.equals("neoforge"),
        "forge" to constraint.equals("forge"),
        "vanilla" to constraint.equals("vanilla")
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
    modstitchModImplementation("maven.modrinth:midnightlib:${property("deps.midnightlib_version")}+${minecraft}-${loader}")
}