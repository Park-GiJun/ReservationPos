-- 기존 Test 테이블 유지 (이미 존재하면 건너뜀)
CREATE TABLE IF NOT EXISTS test (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);

-- Create STORE table
CREATE TABLE IF NOT EXISTS STORE (
    ms_no BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '매장 고유 번호',
    ms_nm VARCHAR(100) NOT NULL COMMENT '매장 이름',
    open_date DATE COMMENT '오픈 날짜',
    close_date DATE COMMENT '폐점 날짜',
    is_waiting_on BOOLEAN DEFAULT TRUE COMMENT '대기 기능 사용 여부',
    is_table_store BOOLEAN DEFAULT TRUE COMMENT '테이블 매장 여부(테이블 예약 가능 여부)',
    last_entered_customer_number INT DEFAULT 0 COMMENT '마지막 입장 고객 대기 번호',
    max_waiting_number INT DEFAULT 0 COMMENT '오늘 등록된 최대 대기 번호'
);

-- Create WAITING_TICKET table
CREATE TABLE IF NOT EXISTS WAITING_TICKET (
    waiting_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '대기표 고유 ID',
    ms_no BIGINT NOT NULL COMMENT '매장 번호',
    table_id BIGINT COMMENT '예약된 테이블 ID (NULL 가능)',
    customer_name VARCHAR(50) NOT NULL COMMENT '고객 이름',
    customer_phone VARCHAR(20) COMMENT '고객 전화번호',
    waiting_cnt INT NOT NULL COMMENT '대기 인원 수',
    request_memo VARCHAR(255) COMMENT '요청사항',
    waiting_start TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '대기 시작 시각',
    cancel_dtime TIMESTAMP NULL COMMENT '취소 시간',
    enter_dtime TIMESTAMP NULL COMMENT '입장 시간',
    update_dtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정 시각',
    process_fg VARCHAR(20) NOT NULL DEFAULT 'WAITING' COMMENT '현재 상태 (WAITING, RESERVED, CANCELLED, ENTERED, NO_SHOW)',
    waiting_order INT NOT NULL COMMENT '대기 순번',
    is_deleted BOOLEAN DEFAULT FALSE COMMENT '삭제 여부',
    is_table_reserved BOOLEAN DEFAULT FALSE COMMENT '테이블 예약 여부',
    FOREIGN KEY (ms_no) REFERENCES STORE(ms_no) ON DELETE CASCADE
);

-- Create STORE_TABLE table
CREATE TABLE IF NOT EXISTS STORE_TABLE (
    table_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '테이블 고유 ID',
    ms_no BIGINT NOT NULL COMMENT '매장 번호',
    table_name VARCHAR(50) NOT NULL COMMENT '테이블 이름 또는 번호',
    capacity INT NOT NULL COMMENT '수용 가능 인원',
    table_status VARCHAR(50) NOT NULL DEFAULT 'AVAILABLE' COMMENT '테이블 상태 (AVAILABLE, OCCUPIED, RESERVED)',
    table_location VARCHAR(255) COMMENT '테이블 위치 정보(좌표 또는 설명)',
    is_active BOOLEAN DEFAULT TRUE COMMENT '활성화 여부',
    last_occupied_time TIMESTAMP NULL COMMENT '마지막 사용 시작 시간',
    last_released_time TIMESTAMP NULL COMMENT '마지막 사용 종료 시간',
    FOREIGN KEY (ms_no) REFERENCES STORE(ms_no) ON DELETE CASCADE
);

-- CREATE TABLE IF NOT EXISTS_RESERVATION table
CREATE TABLE IF NOT EXISTS TABLE_RESERVATION (
    reservation_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '테이블 예약 고유 ID',
    table_id BIGINT NOT NULL COMMENT '테이블 ID',
    waiting_id BIGINT NOT NULL COMMENT '대기표 ID',
    reservation_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '예약 시간',
    expected_entry_time TIMESTAMP NULL COMMENT '예상 입장 시간',
    actual_entry_time TIMESTAMP NULL COMMENT '실제 입장 시간',
    reservation_status VARCHAR(20) NOT NULL DEFAULT 'PENDING' COMMENT '예약 상태 (PENDING, CONFIRMED, CANCELLED, COMPLETED)',
    FOREIGN KEY (table_id) REFERENCES STORE_TABLE(table_id) ON DELETE CASCADE,
    FOREIGN KEY (waiting_id) REFERENCES WAITING_TICKET(waiting_id) ON DELETE CASCADE
);

-- Add index for common query patterns
# CREATE INDEX idx_waiting_ticket_ms_no ON WAITING_TICKET(ms_no);
# CREATE INDEX idx_waiting_ticket_process_fg ON WAITING_TICKET(process_fg);
# CREATE INDEX idx_store_table_ms_no ON STORE_TABLE(ms_no);
# CREATE INDEX idx_store_table_status ON STORE_TABLE(table_status);
# CREATE INDEX idx_table_reservation_table_id ON TABLE_RESERVATION(table_id);
# CREATE INDEX idx_table_reservation_waiting_id ON TABLE_RESERVATION(waiting_id);
# CREATE INDEX idx_table_reservation_status ON TABLE_RESERVATION(reservation_status);

# -- Modify WAITING_TICKET to add foreign key for table_id after STORE_TABLE is created
# ALTER TABLE WAITING_TICKET
# ADD CONSTRAINT fk_waiting_ticket_table_id
# FOREIGN KEY (table_id) REFERENCES STORE_TABLE(table_id) ON DELETE SET NULL;
