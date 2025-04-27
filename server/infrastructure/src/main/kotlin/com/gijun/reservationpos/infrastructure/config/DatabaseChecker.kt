package com.gijun.reservationpos.infrastructure.config

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.r2dbc.core.DatabaseClient
import reactor.core.publisher.Mono

@Configuration
class DatabaseChecker {

    @Bean
    fun printTablesAndColumns(databaseClient: DatabaseClient): CommandLineRunner {
        return CommandLineRunner {
            println("데이터베이스 연결 시도 중...")
            
            // DatabaseClient를 사용하여 테이블 목록 조회
            val tablesMono = databaseClient.sql("SHOW TABLES")
                .map { row, _ -> row.get(0, String::class.java) }
                .all()
                .collectList()
            
            // 모노를 블록하여 결과 얻기 (비동기적 접근 대신 간단한 동기 접근 사용)
            try {
                val tables = tablesMono.block()
                
                if (tables.isNullOrEmpty()) {
                    println("데이터베이스에 테이블이 없습니다.")
                } else {
                    println("테이블 목록 (총 ${tables.size}개):")
                    
                    for (tableName in tables) {
                        println("Table: $tableName")
                        
                        try {
                            val columnsMono = databaseClient.sql("SHOW COLUMNS FROM $tableName")
                                .map { row, _ -> 
                                    val column = row.get("FIELD", String::class.java)
                                    val type = row.get("TYPE", String::class.java)
                                    "$column : $type"
                                }
                                .all()
                                .collectList()
                            
                            val columns = columnsMono.block()
                            columns?.forEach { columnInfo ->
                                println("   ↳ $columnInfo")
                            }
                        } catch (e: Exception) {
                            println("   ↳ 컬럼 정보 조회 실패: ${e.message}")
                        }
                    }
                }
            } catch (e: Exception) {
                println("테이블 목록 조회 실패: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}