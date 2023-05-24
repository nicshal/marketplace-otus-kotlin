plugins {
    kotlin("jvm")
}

kotlin {

    sourceSets {
        val datetimeVersion: String by project

        val main by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation(project(":tariff-management-common"))
                api("org.jetbrains.kotlinx:kotlinx-datetime:$datetimeVersion")
            }
        }

    }

}