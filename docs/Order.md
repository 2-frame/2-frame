# [주문/주문상세] 요구사항 정의
## [주문]

- [ ] [주문]은 [회원] 정보를 참조한다.
  - [ ] [회원]의 상태가 휴면이거나 탈퇴한 경우에도 주문 내역은 유지한다.
- [ ] 회원은 장바구니 또는 바로 구매를 통해 주문을 생성한다.
  - [ ] 주문 생성 시 받는 사람 정보와 배송 정보를 입력받는다.
- [ ] [주문]은 [판매상품] 정보를 참조한다.
  - [ ] [판매상품]이 삭제되더라도 주문 내역에는 영향이 없어야 한다.
- [ ] '대표상품명'는 다수의 상품 중 첫 번째 상품명과 나머지 상품 개수 정보를 포함한다.
  - [ ] '멋쟁이 바지 외 3건'
- [ ] '주문금액'은 주문에 포함된 모든 상품의 가격 합계를 저장한다.
- [ ] '주문상태'는 `주문접수`, `주문완료`, `주문취소`, `구매확정`으로 제한한다.
  - [ ] '주문 상태' 기본값은 `주문접수`로 설정한다. 
  - [ ] `주문접수`가 되면 [재고]의 '수량'은 감소한다. 
- [ ] 회원의 주문이 완료되면 주문번호를 반환한다.
  - [ ] 회원은 주문 내역을 조회할 수 있다.

## [주문상세]
- [ ] [주문 상세]는 [주문] 정보를 참조한다.
  - [ ] 주문 생성 시 주문에 포함된 판매상품에 대해 [주문 상세]가 생성된다.
- [ ] [주문 상세]는 [판매상품] 정보를 참조한다.
  - [ ] 각 항목에 포함된 '상품명', '수량', '가격'을 저장한다.
- [ ] '상품 수량'은 1 이상이어야 한다. 
  - [ ] 주문 시 [재고]의 '수량'은 감소해야 한다.
- [ ] '배송 상태'는 `배송준비`, `배송중`, `배송완료`로 제한한다.