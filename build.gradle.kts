val clean by tasks.registering(Delete::class) {
    doLast { delete(rootProject.buildDir) }
}

tasks.wrapper {
    gradleVersion = "${project.extra["version.gradle"]}"
}