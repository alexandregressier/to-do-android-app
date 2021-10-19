pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id("com.android.application") version "7.2.0-alpha02"
        id("com.android.library") version "7.2.0-alpha02"
        kotlin("android") version "1.5.30"
        kotlin("kapt") version "1.5.30"
    }
    resolutionStrategy {
        eachPlugin {
            with (requested) {
                when (id.id) {
                    "dagger.hilt.android.plugin" ->
                        useModule("com.google.dagger:hilt-android-gradle-plugin:$version")
                }
            }
        }
    }
}

plugins {
    id("de.fayard.refreshVersions") version "0.23.0"
}

refreshVersions {
    rejectVersionIf {
        candidate.stabilityLevel.isLessStableThan(current.stabilityLevel)
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "to-do"
include(
    ":app",
)