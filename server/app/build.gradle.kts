plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":infrastructure"))
    implementation(project(":api"))
    implementation(project(":core"))
    
    // Spring WebFlux
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    
    // Spring Data R2DBC
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("dev.miku:r2dbc-mysql:0.8.2.RELEASE") // MySQL R2DBC 드라이버
    
    // .env 파일 로드를 위한 의존성
    implementation("me.paulschwarz:spring-dotenv:2.5.4")
    
    // 로깅
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    
    // 테스트
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
}

// bootJar 태스크 활성화 - 이 모듈이 실행 가능한 jar를 생성합니다
tasks.bootJar {
    enabled = true
    archiveFileName.set("reservation-pos-server.jar")
    
    // 메인 클래스 명시적으로 지정
    mainClass.set("com.gijun.reservationpos.ReservationPosApplicationKt")
}

// 일반 jar는 비활성화 (bootJar를 대신 사용)
tasks.jar {
    enabled = false
}
