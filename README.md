# 🕊️ 2-frame Convention

## 🌿 1. 프로젝트 구조
```plaintext
📂 server/
├── 📂 core/                         # 핵심 비지니스 로직 디렉토리
│   ├── 📂 example/
│   │   ├── 📂 api/                  # API 관련 코드
│   │   ├── 📂 application/          # 서비스 계층 로직
│   │   ├── 📂 domain/               # 비즈니스 도메인 정의
│   │   ├── 📂 infrastructure/       # 외부 시스템 연동 관련 코드
│   │   └── 📂 payload/              # 요청 및 응답 데이터 정의           
│   └── 📂 support/
│       ├── 📂 entity/               # BaseEntity 정의
│       ├── 📂 exception/            # 예외 처리 코드
│       └── 📂 response/             # 공통 응답 데이터 포맷 정의
└── 📂 global/
    ├── 📂 config/                   # 글로벌 설정 클래스
    └── 🌱 ServerApplication.java    # 애플리케이션 진입점
```

## 🌿 2. 코드 스타일 가이드
### (1) Setter 사용 지양
- Setter 메서드 사용을 지양하고, 생성자나 빌더 패턴을 활용합니다.
- 단, 매개변수 이름이 모호해 실수가 발생할 가능성이 있는 경우 사용합니다.

### (2) Record 사용 권장
- DTO 역할을 하는 클래스에서는 `record`를 필수적으로 사용합니다.

### (3) 애노테이션 순서
- 애노테이션은 피라미드 형식으로 정리합니다.
- 글자수가 적은 애노테이션이 위에 위치하며, 아래로 갈수록 길이가 길어집니다.
```java
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/2-frame")
```

### (4) 코드 라인 길이
- 코드 한 줄의 길이는 최대 120자를 초과하지 않습니다.

### (5) 클래스 선언부와 본문 사이 공백
- 클래스 선언부 아래는 한 줄의 공백을 추가해 가독성을 향상합니다.
```java
public class Two2Frame{
    
    private final String two2Frame;
    
}
```

## 🌿 3. 프로젝트 운영
### (1) 이슈 관리
- GitHub 이슈를 통해 작업과 문제를 관리합니다.
- 이슈와 라벨을 사용하여 작업 유형과 상태를 구분합니다.

### (2) 마일스톤 관리
- 프로젝트 주요 목표를 마일스톤(milestone)으로 정의합니다.
- 해당 마일스톤과 관련된 이슈(issue)를 연결합니다.

### (3) 브랜치 생성 규칙
- 작업은 이슈 번호를 기반으로 브랜치를 생성하여 진행합니다.