# ReservationPos Server

## 프로젝트 소개
ReservationPos는 Spring Boot와 Kotlin을 사용한 예약 POS 시스템의 백엔드 서버입니다. 이 프로젝트는 반응형 프로그래밍 방식(WebFlux + R2DBC)을 사용하여 비동기적이고 효율적인 API를 제공합니다.

## 기술 스택
- Kotlin 2.1.20
- Spring Boot 3.2.0
- Spring WebFlux (함수형 웹 프레임워크)
- Spring Data R2DBC (비동기 데이터베이스 접근)
- Kotlin Coroutines & Flow (비동기 프로그래밍)
- Gradle (빌드 도구)

## 프로젝트 구조
```
src/main/kotlin/com/gijun/reservationpos/
├── ReservationPosApplication.kt  # 애플리케이션 진입점
├── adapter/                      # HTTP 응답 어댑터
├── config/                       # 설정 클래스
├── dto/                          # 데이터 전송 객체
├── entity/                       # 도메인 엔티티
├── handler/                      # HTTP 요청 핸들러
├── repository/                   # 데이터 접근 레포지토리
├── router/                       # 웹 라우팅 설정
└── service/                      # 비즈니스 로직 서비스
```

## 함수형 웹 프로그래밍 패턴
이 프로젝트는 Spring WebFlux의 함수형 엔드포인트 방식을 사용합니다:

- **Handler**: HTTP 요청을 처리하는 함수들을 포함
- **Router**: 요청 경로를 해당 핸들러 함수에 연결
- **HttpAdapter**: HTTP 응답 생성을 단순화하는 유틸리티

이 접근 방식은 전통적인 MVC 컨트롤러보다 함수형 프로그래밍에 더 적합하며, 코루틴과 함께 비동기 작업을 효율적으로 처리합니다.

## 환경 설정
프로젝트는 다음과 같은 환경 변수를 사용합니다:

- `DB_URL`: 데이터베이스 URL (기본값: `r2dbc:mysql://210.121.177.150:3306/ReservationPos`)
- `DB_USERNAME`: 데이터베이스 사용자명 (기본값: `gijunpark`)
- `DB_PASSWORD`: 데이터베이스 비밀번호 (기본값: `park9832`)
- `SERVER_PORT`: 서버 포트 (기본값: 52001)

## 애플리케이션 실행 방법

### 로컬 환경에서 실행하기
```bash
./gradlew bootRun
```

### 프로필 지정하여 실행하기
```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

### JAR 파일 생성하기
```bash
./gradlew bootJar
```

### JAR 파일 실행하기
```bash
java -jar build/libs/reservation-pos-1.0-SNAPSHOT.jar
```

## 개발 가이드

### 새로운 API 엔드포인트 추가 방법

1. **엔티티 정의**
```kotlin
@Table("your_table")
data class YourEntity(
    @Id
    val id: Long? = null,
    val property: String
)
```

2. **레포지토리 정의**
```kotlin
interface YourRepository : CoroutineCrudRepository<YourEntity, Long> {
    // 필요한 쿼리 메서드 추가
}
```

3. **서비스 정의**
```kotlin
@Service
class YourService(private val yourRepository: YourRepository) {
    // 비즈니스 로직 구현
}
```

4. **DTO 정의**
```kotlin
data class YourRequest(val property: String)
data class YourResponse(val id: Long?, val property: String)
```

5. **핸들러 정의**
```kotlin
@Component
class YourHandler(private val yourService: YourService) {
    suspend fun handleRequest(request: ServerRequest): ServerResponse {
        // 요청 처리 로직
    }
}
```

6. **라우터 정의**
```kotlin
@Configuration
class YourRouter(private val yourHandler: YourHandler) {
    @Bean
    fun yourRoutes() = coRouter {
        "/api/your-path".nest {
            // 경로 정의
        }
    }
}
```
