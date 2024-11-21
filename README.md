📌 내용 정리 노션 페이지 https://www.notion.so/kim602/Weather-API-HTTPS-13f461e237eb8077a25aff3e30e3e1cb
📌 PPT https://www.canva.com/design/DAGWFnyq2Mk/RyX2Zd3klmethhHVmlRwSw/edit?utm_content=DAGWFnyq2Mk&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton

# 날씨 관리 API (Weather Management API)

이 프로젝트는 **날씨 관리 시스템**으로, CRUD(Create, Read, Update, Delete) 기능을 제공하는 RESTful API와 간단한 사용자 인터페이스(UI)를 통해 날씨 데이터를 관리할 수 있습니다.

---

## 주요 기능

- **RESTful API 제공**:
    - 날짜, 지역, 날씨 상태, 온도를 포함한 날씨 데이터를 생성, 조회, 수정, 삭제할 수 있는 API 제공
- **프론트엔드 통합**:
    - API를 쉽게 사용할 수 있도록 HTML 기반의 간단한 사용자 인터페이스 제공
- **보안 HTTPS 통신**:
    - SSL/TLS 설정을 통해 안전한 API 통신 지원
- **파일 기반 로깅**:
    - API 요청 및 응답 로그를 파일 시스템에 기록하여 모니터링 및 디버깅에 활용 가능

---

## 사용 기술

### 백엔드

- **Java**: 주요 백엔드 언어
- **Spring Boot**: RESTful API 개발 및 통합
    - **Spring MVC**: REST 엔드포인트 구축
    - **Spring Data JPA**: 데이터베이스와의 상호작용
    - **Spring Validation**: 입력 데이터 검증
    - **내장 Tomcat 서버**: 애플리케이션 호스팅
- **H2 데이터베이스**: 메모리 기반 데이터베이스로 빠른 데이터 처리
- **SLF4J & Logback**: 로그 기록 및 관리

### 프론트엔드

- **HTML5**: 사용자 인터페이스 구성
- **Vanilla JavaScript**: API 통합 및 동적 상호작용

### 빌드 도구

- **Gradle**: 의존성 관리 및 빌드 자동화

---

## 설치 및 실행 방법

### 사전 준비

- Java Development Kit (JDK 17 이상)
- Gradle 설치
- Git 설치

### 실행 방법

1. 레포지토리 클론:
    
    ```bash
    bash
    git clone https://github.com/your-repo/weatherAPI.git
    
    ```
    
2. 프로젝트 디렉토리로 이동:
    
    ```bash
    bash
    cd weatherAPI
    
    ```
    
3. 애플리케이션 실행:
    
    ```bash
    bash
    ./gradlew bootRun
    
    ```
    
4. 애플리케이션 접속:
    - **API 엔드포인트**: `https://localhost:8443/api/weather`
    - **웹 인터페이스**: `https://localhost:8443/weather/view`

---

## API 엔드포인트

| 메서드 | 엔드포인트 | 설명 |
| --- | --- | --- |
| GET | `/api/weather` | 모든 날씨 데이터 조회 |
| GET | `/api/weather/{id}` | 특정 ID의 날씨 데이터 조회 |
| POST | `/api/weather` | 새로운 날씨 데이터 추가 |
| PUT | `/api/weather/{id}` | 기존 날씨 데이터 수정 |
| DELETE | `/api/weather/{id}` | 특정 ID의 날씨 데이터 삭제 |

---

## 개선 방향

- H2 데이터베이스를 MySQL 또는 PostgreSQL로 전환하여 실제 운영 환경에 적합하게 설정
- JWT 인증을 추가하여 API 보안 강화
- 날씨 예보와 같은 추가 기능을 제공할 수 있도록 API 확장
- 사용자 경험을 개선하기 위한 프론트엔드 향상
![image](https://github.com/user-attachments/assets/e7afb95d-775f-4198-9899-b29d5f196cda)

