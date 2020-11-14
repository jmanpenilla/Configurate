import net.ltgt.gradle.errorprone.errorprone
import org.spongepowered.configurate.build.core
import org.spongepowered.configurate.build.useAutoValue

plugins {
    id("org.spongepowered.configurate.build.component")
    groovy // For writing tests
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

description = "YAML format loader for Configurate"

useAutoValue()
dependencies {
    api(core())
    // When updating snakeyaml, check ConfigurateScanner for changes against upstream
    implementation("org.yaml:snakeyaml:1.+")

    testImplementation("org.codehaus.groovy:groovy:3.+:indy")
    testImplementation("org.codehaus.groovy:groovy-nio:3.+:indy")
    testImplementation("org.codehaus.groovy:groovy-test-junit5:3.+:indy")
    testImplementation("org.codehaus.groovy:groovy-templates:3.+:indy")
}

tasks.compileJava {
    options.errorprone.excludedPaths.set(".*org[\\\\/]spongepowered[\\\\/]configurate[\\\\/]yaml[\\\\/]ConfigurateScanner.*")
    // our vendored version of ScannerImpl has invalid JD, so we have to suppress some warnings
    options.compilerArgs.add("-Xdoclint:-html")
}
