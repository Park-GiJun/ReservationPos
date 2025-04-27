plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    // 도메인 모듈 의존성 추가
    implementation(project(":domain"))
    
    // 코루틴 (suspend, Flow 지원)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.3")
    
    // Spring Data R2DBC
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("dev.miku:r2dbc-mysql:0.8.2.RELEASE") // MySQL R2DBC 드라이버
    
    // 로깅 (선택)
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    
    // 테스트
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

// bootJar 태스크를 명시적으로 비활성화
tasks.bootJar {
    enabled = false
}

// jar 태스크는 활성화
tasks.jar {
    enabled = true
    archiveClassifier.set("")
}