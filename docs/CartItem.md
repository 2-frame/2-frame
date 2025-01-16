# [장바구니 상품] 요구사항 정의
1. 회원은 장바구니 상품에 판매상품을 담을 수 있다.
2. 동일한 판매상품을 장바구니에 여러 번 담으면, 해당 판매상품의 수량이 증가한다.
3. 사용자는 장바구니에 담긴 판매상품의 수량을 자유롭게 수정할 수 있다.
4. 수량은 1개 이상으로 설정되어야 하며, 수량을 0 이하로 변경하면 시도를 차단하고, 메세지를 리턴합니다. "1개 이상부터 구매하실 수 있습니다."
5. 장바구니에서 판매상품을 삭제할 수 있다.
6. 장바구니에 담은 판매상품의 가격은 실시간으로 반영된다.
7. 장바구니에 담긴 판매상품의 가격이 변동되면, 그에 맞게 장바구니에 표시되는 가격도 실시간으로 변경된다. 
8. 장바구니는 로그인한 상태에 무관하게 판매상품을 담을 수 있다.
9. 장바구니에 담은 상품의 총 금액을 계산할 수 있다.
10. 장바구니에 담긴 상품의 재고를 확인하여, 재고가 부족한 상품은 결제 진행을 제한하거나 안내 메시지를 표시한다.