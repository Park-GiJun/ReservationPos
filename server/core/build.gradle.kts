plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":infrastructure"))

    // Spring Boot Core
    implementation("org.springframework.boot:spring-boot-starter")

    // 코루틴
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.3")

    // Reactor Kotlin 확장
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.2.1")

    // 로깅
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")

    // 스케줄링 (선택: @Scheduled 쓸 거면)
    implementation("org.springframework.boot:spring-boot-starter-quartz")

    // 테스트
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
}

// jar 태스크 활성화 - 다른 모듈에서 의존할 수 있도록
tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
    archiveClassifier.set("") // plain.jar 서픽스 제거
}
