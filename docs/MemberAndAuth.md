# [회원/인증] 요구사항 정의
- [ ] 로그인이 가능합니다.
  - [ ] 이메일과 비밀번호를 통해서 로그인을 진행합니다.
  - [ ] 이메일 혹은 비밀번호가 잘못된 경우 로그인에 실패합니다.
    - [ ] 또한 5번 실패하는 경우 계정 잠금 처리됩니다.
- [ ] 회원가입이 가능합니다.
  - [ ] 중복된 이메일 닉네임을 허용하지 않습니다.
  - [ ] 회원가입을 위해서는 이메일 인증을 요구합니다.
  - [ ] 인증 코드 혹은 URL 리다이렉션 방식을 사용합니다.
- [ ] 계정 잠금 기능
  - [ ] 계정 잠금 처리된 기능은 이메일 인증을 통해서 잠금 해지할 수 있습니다.
- [ ] 로그아웃 기능 구현
- [ ] 회원 탈퇴 기능
  - [ ] 회원 탈퇴의 경우 계정을 삭제하지 않고 비활성화 처리합니다. 
    - [ ] 단 민감 정보는 마스킹 처리
- [ ] 인가 기능 구현
  - [ ] 허용된 사용자만 API에 접근 가능하도록 구현합니다.



