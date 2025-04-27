plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "server"

// 서버 모듈 설정
include(":core")
include(":api")
include(":domain")
include(":infrastructure")
include(":app")
