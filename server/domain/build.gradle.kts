plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    // 코루틴 (suspend, Flow 지원)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Spring Data R2DBC 어노테이션 지원 (ex: @Table, @Id)
    implementation("org.springframework.data:spring-data-relational")

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
    // jar 파일이 생성될 때 클래스패스에 추가되도록 설정
    archiveClassifier.set("")
}
