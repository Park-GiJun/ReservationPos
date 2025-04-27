plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot") 
    id("io.spring.dependency-management")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core"))

    // Spring WebFlux (비동기 API 서버)
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // API 문서화 (Swagger/OpenAPI)
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.3.0")

    // 코루틴
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.3")

    // Reactor Kotlin 확장
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.2.1")

    // 로깅
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")

    // 테스트
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
}

// bootJar 태스크를 명시적으로 비활성화
tasks.bootJar {
    enabled = false
}

// jar 태스크는 활성화
tasks.jar {
    enabled = true
    // jar 파일이 생성될 때 클래스패스에 추가되도록 설정
    archiveClassifier.set("")
}
