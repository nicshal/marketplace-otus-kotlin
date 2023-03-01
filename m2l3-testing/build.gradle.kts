plugins {
    kotlin("jvm")
}

kotlin {

    val kotestVersion: String by project
    val coroutinesVersion: String by project
    val jUnitJupiterVersion: String by project

    sourceSets {

        val main by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        val test by getting {
            dependencies {
                implementation(kotlin("test-junit5"))
                implementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
                implementation("org.junit.jupiter:junit-jupiter-params:$jUnitJupiterVersion")
            }
        }
    }
}

tasks {
    withType<Test>().configureEach {
        useJUnitPlatform {
            //includeTags.add("sampling")
        }
        filter {
            isFailOnNoMatchingTests = false
        }
        testLogging {
            showExceptions = true
            showStandardStreams = true
            events = setOf(org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED, org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED)
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }
}
