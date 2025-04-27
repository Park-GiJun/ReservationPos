# ReservationPos Server 개발 가이드

## 프로젝트 구조

ReservationPos 서버는 다중 모듈 구조로 설계되어 있으며, 클린 아키텍처 패턴을 따릅니다. 각 모듈은 고유한 책임을 가지고 있으며 의존성 방향은 내부(domain)에서 외부(api/infrastructure)로 흐릅니다.

```
server/
├── domain/         # 핵심 비즈니스 엔티티 및 인터페이스
├── infrastructure/ # 외부 시스템 연동 및 구현체
├── core/           # 비즈니스 로직 및 서비스
└── api/            # 외부 API 인터페이스
```

## 모듈별 코드 작성 가이드

### domain 모듈

**역할**: 핵심 비즈니스 모델과 인터페이스 정의

**작성할 코드**:
- 비즈니스 엔티티 (예: `Reservation.kt`, `Table.kt`, `Customer.kt`)
- 레포지토리 인터페이스 (예: `ReservationRepository.kt`)
- 도메인 서비스 인터페이스 (예: `TableAssignmentService.kt`)
- 값 객체 및 열거형

**경로 예시**:
```
domain/src/main/kotlin/com/gijun/reservationpos/domain/
├── entity/            # 비즈니스 엔티티
├── repository/        # 레포지토리 인터페이스
├── service/           # 도메인 서비스 인터페이스
└── valueobject/       # 값 객체
```

**주의사항**:
- 이 모듈은 다른 모듈에 의존하지 않아야 함
- R2DBC 관련 어노테이션만 사용 (`@Table`, `@Id` 등)
- 외부 시스템에 의존하는 코드를 작성하지 않음

### infrastructure 모듈

**역할**: domain 모듈에 정의된 인터페이스의 구현체 제공

**작성할 코드**:
- 레포지토리 구현체 (예: `ReservationRepositoryImpl.kt`)
- 외부 시스템 통합 (Redis, 외부 API 등)
- 영속성 어댑터

**경로 예시**:
```
infrastructure/src/main/kotlin/com/gijun/reservationpos/infrastructure/
├── adapter/         # 레포지토리 어댑터 (domain 인터페이스 구현체)
├── config/          # 인프라 관련 설정
├── client/          # 외부 API 클라이언트
└── repository/      # Spring Data 레포지토리
```

**주의사항**:
- domain 모듈에만 의존 가능
- 외부 시스템(Redis, DB 등) 연동 코드는 이 모듈에 작성
- 인프라 관련 설정 코드는 이 모듈에 위치

### core 모듈

**역할**: 비즈니스 로직과 서비스 구현, 애플리케이션 진입점

**작성할 코드**:
- 비즈니스 서비스 구현체 (예: `ReservationServiceImpl.kt`)
- 트랜잭션 관리
- 애플리케이션 설정
- 스케줄링 작업

**경로 예시**:
```
core/src/main/kotlin/com/gijun/reservationpos/
├── ReservationPosApplication.kt  # 애플리케이션 진입점
└── core/
    ├── config/         # 애플리케이션 설정
    ├── service/        # 서비스 인터페이스
    │   └── impl/       # 서비스 구현체
    └── scheduler/      # 스케줄링 작업
```

**주의사항**:
- domain과 infrastructure 모듈에 의존 가능
- 메인 애플리케이션 클래스가 위치
- 서비스 레이어의 트랜잭션 관리 코드 포함

### api 모듈

**역할**: 외부 API 인터페이스 제공

**작성할 코드**:
- 컨트롤러 (예: `ReservationController.kt`)
- DTO (Data Transfer Object)
- 요청/응답 모델
- API 관련 예외 처리

**경로 예시**:
```
api/src/main/kotlin/com/gijun/reservationpos/api/
├── controller/     # REST 컨트롤러
├── dto/            # 데이터 전송 객체
├── exception/      # API 예외 처리
└── config/         # API 관련 설정
```

**주의사항**:
- domain과 core 모듈에 의존 가능
- infrastructure 모듈에 직접 의존하지 않음
- 웹 관련 코드만 포함

## 의존성 관리 가이드

### 모듈별 의존성 추가 위치

#### 도메인 관련 라이브러리
- **domain 모듈**: R2DBC 관련 기본 의존성

#### 인프라 관련 라이브러리
- **infrastructure 모듈**:
  - 데이터베이스 드라이버 (H2, PostgreSQL 등)
  - 캐시 관련 라이브러리 (Redis 등)
  - 메시징 라이브러리 (Kafka, RabbitMQ 등)
  - 외부 API 클라이언트 라이브러리

#### 애플리케이션 관련 라이브러리
- **core 모듈**:
  - 스케줄링 라이브러리
  - 보안 관련 라이브러리
  - 유틸리티 라이브러리
  - 모니터링 라이브러리

#### API 관련 라이브러리
- **api 모듈**:
  - API 문서화 라이브러리 (SpringDoc, Swagger 등)
  - 유효성 검증 라이브러리
  - API 보안 라이브러리

### 의존성 추가 예시

#### Redis 추가 시
Redis는 인프라 관련 기술이므로 `infrastructure` 모듈에 추가해야 합니다:

```kotlin
// infrastructure/build.gradle.kts
dependencies {
    implementation(project(":domain"))
    
    // 기존 의존성
    // ...
    
    // Redis 의존성 추가
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
}
```

Redis 관련 설정 클래스는 infrastructure 모듈의 config 패키지에 작성합니다:
```
infrastructure/src/main/kotlin/com/gijun/reservationpos/infrastructure/config/RedisConfig.kt
```

#### 보안 라이브러리 추가 시
Spring Security와 같은 보안 라이브러리는 `core` 모듈에 추가합니다:

```kotlin
// core/build.gradle.kts
dependencies {
    implementation(project(":domain"))
    
    // 기존 의존성
    // ...
    
    // 보안 의존성 추가
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
}
```

#### API 문서화 라이브러리 추가 시
OpenAPI(Swagger) 같은 API 문서화 도구는 `api` 모듈에 추가합니다:

```kotlin
// api/build.gradle.kts
dependencies {
    implementation(project(":domain"))
    implementation(project(":core"))
    
    // 기존 의존성
    // ...
    
    // API 문서화 의존성 추가
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.3.0")
}
```

## 코딩 패턴 및 모범 사례

### 비동기 프로그래밍
- R2DBC와 WebFlux를 사용하여 비동기 및 논블로킹 스타일로 코드 작성
- 코루틴과 Flow를 활용하여 비동기 코드 간결하게 작성
- suspend 함수를 사용하여 비동기 작업 표현

### 의존성 주입
- 생성자 주입 방식 사용 (필드 주입 지양)
- 인터페이스에 의존하되 구현체에는 의존하지 않기

### 예외 처리
- domain 모듈에서는 도메인 예외 정의
- api 모듈에서 전역 예외 처리기 구현

### 테스트
- 각 모듈별로 테스트 작성
- domain 모듈은 단위 테스트 중심
- infrastructure 모듈은 통합 테스트 중심
- api 모듈은 엔드포인트 테스트 중심

## 환경 설정 가이드

프로젝트는 다음과 같은 환경별 설정 파일을 가지고 있습니다:

- `application.yml`: 기본 설정
- `application-local.yml`: 로컬 개발 환경 설정
- `application-dev.yml`: 개발 서버 환경 설정
- `application-production.yml`: 운영 환경 설정

환경별 실행 방법:
```bash
# 로컬 환경
./gradlew :core:bootRun --args='--spring.profiles.active=local'

# 개발 환경
./gradlew :core:bootRun --args='--spring.profiles.active=dev'

# 운영 환경
./gradlew :core:bootRun --args='--spring.profiles.active=production'
```

## 요약

1. **domain**: 핵심 비즈니스 모델과 인터페이스 (다른 모듈에 의존하지 않음)
2. **infrastructure**: 외부 시스템 연동 및 구현체 (domain에만 의존)
3. **core**: 비즈니스 로직 및 서비스 (domain과 infrastructure에 의존)
4. **api**: 외부 API 인터페이스 (domain과 core에 의존)

이 구조를 따르면 높은 응집도와 낮은 결합도를 가진 유지보수하기 좋은 코드를 작성할 수 있습니다.
