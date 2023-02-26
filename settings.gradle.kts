rootProject.name = "marketplace-otus-kotlin"

pluginManagement {
    val kotlinVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
    }
}

include("m1l1-homework")
include("m2l3-testing")