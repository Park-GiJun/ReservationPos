import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.spring") version "2.1.20"
}

group = "com.gijun"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {
    // Spring WebFlux (비동기 API 서버)
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    
    // Spring Data R2DBC
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("dev.miku:r2dbc-mysql:0.8.2.RELEASE") // MySQL R2DBC 드라이버
    
    // API 문서화 (Swagger/OpenAPI)
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.3.0")
    
    // 코루틴
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.3")
    
    // Reactor Kotlin 확장
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.2.1")
    
    // 로깅
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    
    // .env 파일 로드를 위한 의존성
    implementation("me.paulschwarz:spring-dotenv:2.5.4")

    // 테스트
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
