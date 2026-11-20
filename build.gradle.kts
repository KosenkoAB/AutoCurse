plugins {
    id("java")
    id("io.qameta.allure") version "3.0.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.27.7")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.slf4j:slf4j-api:2.0.18")
    testImplementation("io.rest-assured:rest-assured:5.5.2")
    testImplementation("io.rest-assured:json-path:5.5.2")
    testImplementation("org.junit.vintage:junit-vintage-engine")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.51.0")
    testImplementation("com.codeborne:selenide:7.19.0")
    testImplementation("org.aeonbits.owner:owner:1.0.12")

    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.18")

    implementation("org.seleniumhq.selenium:selenium-java:4.51.0")
    implementation("com.codeborne:selenide:7.19.0")

    compileOnly("org.projectlombok:lombok:1.18.46")

    implementation("org.aeonbits.owner:owner:1.0.12")
    implementation("org.assertj:assertj-core:3.27.7")

    testImplementation("io.qameta.allure:allure-junit5:2.35.5")
    testImplementation("io.qameta.allure:allure-selenide:2.35.5")
    testImplementation("io.qameta.allure:allure-rest-assured:2.35.5")

    implementation(platform("org.junit:junit-bom:6.0.0"))
    implementation("org.junit.jupiter:junit-jupiter")
}


tasks.test {
    useJUnitPlatform{ excludeTags("Assert") }
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

tasks.register<Test>("apiTest"){
    testTaskConfigurator() // Ключевое исправление: настройка путей к тестам
    useJUnitPlatform { includeTags("api") }
    outputs.upToDateWhen { false } // Отключение кэширования для гарантированного запуска
}

tasks.register<Test>("sel") {
    testTaskConfigurator()
    useJUnitPlatform { includeTags("sel") }
    outputs.upToDateWhen { false }
}

tasks.register<Test>("DnD") {
    testTaskConfigurator()
    useJUnitPlatform { includeTags("DnD") }
    outputs.upToDateWhen { false }
}

tasks.register<Test>("Page") {
    testTaskConfigurator()
    useJUnitPlatform { includeTags("Page") }
    outputs.upToDateWhen { false }
    systemProperty("allure.results.directory", "build/allure-results")
}