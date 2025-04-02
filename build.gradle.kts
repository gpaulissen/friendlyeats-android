// Top-level build file where you can add configuration options common to all sub-projects/modules.
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    /*
    id("com.android.application") version "8.9.1" apply false
    id("com.android.library") version "8.9.1" apply false
    id("org.jetbrains.kotlin.android") version "2.1.20" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("androidx.navigation.safeargs") version "2.8.9" apply false
    id("com.github.ben-manes.versions") version "0.52.0" apply true
    id("com.google.firebase.appdistribution") version "5.1.1" apply false   
    */
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.androidx.navigation.safeargs) apply false
    alias(libs.plugins.github.ben.manes.versions) apply false
    alias(libs.plugins.google.firebase.appdistribution) apply false

}

allprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}

fun isNonStable(candidate: ModuleComponentIdentifier): Boolean {
    return listOf("alpha", "beta", "rc", "snapshot").any { keyword ->
        keyword in candidate.version.lowercase()
    }
}

fun isBlockListed(candidate: ModuleComponentIdentifier): Boolean {
    return listOf(
        "androidx.browser:browser",
        "com.google.android.gms:play-services-auth"
    ).any { keyword ->
        keyword in candidate.toString().lowercase()
    }
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate) || isBlockListed(candidate)
    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}
