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
    // Source: https://mvnrepository.com/artifact/io.rest-assured/rest-assured
    // ВАЖНО: testImplementation — тогда тесты видят библиотеку, а IDE не подчёркивает .get()
    // ВАЖНО: одна версия для всех артефактов rest-assured (иначе Groovy-конфликт -> NPE)
    testImplementation("io.rest-assured:rest-assured:5.5.2")
    // Source: https://mvnrepository.com/artifact/io.rest-assured/json-path
    testImplementation("io.rest-assured:json-path:5.5.2")
    // Для запуска JUnit 5 из IDEA через класс main() (JUnit 4 launcher)
    testImplementation("org.junit.vintage:junit-vintage-engine")
}

//создал таску до доработок таск конфигуратора
//tasks.test {
//    useJUnitPlatform{ excludeTags("Assert") }
//}

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

//создал таску до доработок таск конфигуратора
//tasks.register<Test>("testWithTag") {
//    useJUnitPlatform { includeTags("Assert") }
//}

tasks.register<Test>("apiTest"){
    testTaskConfigurator() // Ключевое исправление: настройка путей к тестам
    useJUnitPlatform { includeTags("api") }
    outputs.upToDateWhen { false } // Отключение кэширования для гарантированного запуска
}





