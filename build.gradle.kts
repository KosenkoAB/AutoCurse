plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // Source: https://mvnrepository.com/artifact/org.assertj/assertj-core
    testImplementation("org.assertj:assertj-core:3.27.7")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // Source: https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    testImplementation("org.slf4j:slf4j-api:2.0.18")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Test>("Finish") {
    doLast  {println("Test run is over")}
}

tasks.named("Finish"){
    dependsOn("test")
}



val testTaskConfigurator: Test.() -> Unit = {
    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath
    useJUnitPlatform()
}

tasks.test { testTaskConfigurator() }

tasks.register<Test>("testWithTag") {
    testTaskConfigurator() // Ключевое исправление: настройка путей к тестам
    useJUnitPlatform { includeTags("Assert") }
    outputs.upToDateWhen { false } // Отключение кэширования для гарантированного запуска
}